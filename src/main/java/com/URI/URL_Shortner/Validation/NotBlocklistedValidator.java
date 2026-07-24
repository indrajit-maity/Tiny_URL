package com.URI.URL_Shortner.Validation;

import com.URI.URL_Shortner.Service.Blocklist.BlocklistService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class NotBlocklistedValidator implements ConstraintValidator<NotBlocklisted, String> {

    private  final BlocklistService blocklistService;


    public  boolean isValid(String url, ConstraintValidatorContext context) {
        if(url==null){
            return true;
        }
        String host=extractHost(url);
        return  !blocklistService.isBlocked(host);
    }

    private String extractHost(String url) {
        try{
            URI uri=URI.create(url);
            String host=uri.getHost();
            return host;
        }
        catch(IllegalArgumentException e){
            return "";
        }
    }
}
