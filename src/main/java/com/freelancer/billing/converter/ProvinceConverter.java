package com.freelancer.billing.converter;

import com.freelancer.billing.domain.Province;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("provinceConverter")
public class ProvinceConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if(s!=null && s.trim().isEmpty()) {
            try{
                Province province = new Province();
                province.setId(Integer.parseInt(s));
            }catch (NumberFormatException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "invalid province", null));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if(o != null && o instanceof Province)
            return String.valueOf(((Province) o).getId());

        return null;
    }
}
