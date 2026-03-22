package com.ecommerce.amazio.exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message){
        super(message);
    }
}
