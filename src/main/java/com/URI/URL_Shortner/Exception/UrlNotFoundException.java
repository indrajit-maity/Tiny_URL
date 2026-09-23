package com.URI.URL_Shortner.Exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String shortCode) {
        super(shortCode);
    }
}
