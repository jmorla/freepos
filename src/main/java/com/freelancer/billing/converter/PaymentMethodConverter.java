package com.freelancer.billing.converter;

import com.freelancer.billing.model.PaymentMethod;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("paymentMethodConverter")
public class PaymentMethodConverter implements Converter{


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        switch (s.toUpperCase()) {

            case "CASH":
                return PaymentMethod.CASH;
            case "CREDIT CARD":
                return PaymentMethod.CREDIT_CARD;
            case "CHEQUE":
                return PaymentMethod.CHEQUE;
            default:
                return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {return null;}
}
