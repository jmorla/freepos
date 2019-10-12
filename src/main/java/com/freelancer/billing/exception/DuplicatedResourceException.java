package com.freelancer.billing.exception;


public class DuplicatedResourceException extends ApplicationException{

    private final FieldType type;

    public DuplicatedResourceException(FieldType type, String value) {
        super("The "+type.toString().toLowerCase()+" "+value+" already exist");

        this.type = type;
    }

    public FieldType getType() {
        return type;
    }
}
