package com.URI.URL_Shortner.Exception;

public class DomainNotAllowedException extends IllegalArgumentException{
    public DomainNotAllowedException(String message){
        super(message);
    }
}
