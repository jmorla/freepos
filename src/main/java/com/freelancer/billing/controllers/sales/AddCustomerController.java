package com.freelancer.billing.controllers.sales;

import com.freelancer.billing.domain.Country;
import com.freelancer.billing.domain.Province;
import com.freelancer.billing.exception.ApplicationException;
import com.freelancer.billing.model.CustomerDTO;
import com.freelancer.billing.repository.CountryRepository;
import com.freelancer.billing.repository.ProvinceRepository;
import com.freelancer.billing.service.CustomerService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Controller
@ManagedBean
@SessionScoped
@URLMapping(id = "addCustomers", pattern = "/customers/add/", viewId = "/sales/add-customer.xhtml")
public class AddCustomerController {

    /**
     *
     * The Logger instance
     * */
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCustomerController.class);


    private CustomerDTO customer;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CountryRepository countryRepository;

    private List<Country> countries;

    private List<Province> provinces = new ArrayList<>();

    private void init() {
        customer = new CustomerDTO();
        countries = countryRepository.findAll();
    }

    public void initNew() {
        init();
    }

    public void onChangeCountry() {
        LOGGER.info("country has been changed");
        if(customer.getCountryId()>0){
            this.provinces = provinceRepository
                    .findProvinceByCountryId(customer.getCountryId());
        }else{
            this.provinces = new ArrayList<>();
        }
    }

    public String saveCustomer() {
        LOGGER.info("executing save Customer method");
        FacesMessage message;

        try {
            customerService.saveCustomer(customer);
            //redirecting to add customer view
            return "pretty:listCustomers";
        } catch (ApplicationException ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null);
            LOGGER.warn("Error while saving user");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);

        return "";
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}
