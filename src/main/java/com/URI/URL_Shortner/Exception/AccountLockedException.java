package com.URI.URL_Shortner.Exception;

public class AccountLockedException extends RuntimeException{
    public AccountLockedException(String message) {
        super(message);
    }
}
