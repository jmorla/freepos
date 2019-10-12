package com.freelancer.billing.converter;

import com.freelancer.billing.domain.Country;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("countryConverter")
public class CountryConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if(s!=null && s.trim().isEmpty()) {
            try{
                Country country = new Country();
                country.setId(Integer.parseInt(s));
            }catch (NumberFormatException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "invalid country", null));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if(o!=null && o instanceof Country){
            return String.valueOf(((Country) o ).getId());
        }

        return "1";
    }
}

