package com.freelancer.billing.converter;

import com.freelancer.billing.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("customerConverter")
public class CustomerConverter implements Converter{

    private final Logger logger = LoggerFactory.getLogger(CustomerConverter.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        logger.info("executing converter with values {}", s);

        if(s !=null && !s.trim().isEmpty()) {

            Customer customer = new Customer();
            customer.setId(s);
            return customer;
        }else {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        return ((Customer) o).getId();
    }
}
