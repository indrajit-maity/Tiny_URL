package com.URI.URL_Shortner.Exception;

public class DomainAlreadyExistsException extends RuntimeException{
    public DomainAlreadyExistsException(String message){
        super(message);
    }
}
