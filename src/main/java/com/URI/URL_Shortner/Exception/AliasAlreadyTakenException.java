package com.URI.URL_Shortner.Exception;

import org.springframework.dao.DataIntegrityViolationException;

public class AliasAlreadyTakenException extends DataIntegrityViolationException {
    public AliasAlreadyTakenException(String message){
        super(message);
    }
}
