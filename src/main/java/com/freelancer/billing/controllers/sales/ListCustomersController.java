package com.freelancer.billing.controllers.sales;

import com.freelancer.billing.domain.Customer;
import com.freelancer.billing.service.CustomerService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Controller
@ManagedBean
@SessionScoped
@URLMapping(id = "listCustomers", pattern = "/customers/view/", viewId = "/sales/list-customers.xhtml")
public class ListCustomersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListCustomersController.class);

    @Autowired
    private CustomerService customerService;

    private List<Customer> customers;

    /**
     * Customer id to remove
     * */
    private String customerId;

    public void initNew() {
        init();
    }

    private void init() {
        customers = customerService.findAll();
    }

    public void onRemove(String id){
        LOGGER.info("setting customer to remove {}", id);
        this.customerId = id;
    }

    public void removeCustomer(){
        if(customerId!=null) {
            LOGGER.info("Removing customer with id {}", customerId);
            customerService.removeCustomer(customerId);

            init();
        }
    }



    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
