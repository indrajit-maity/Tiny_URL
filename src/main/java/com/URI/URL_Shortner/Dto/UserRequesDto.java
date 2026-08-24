package com.URI.URL_Shortner.Dto;


import com.URI.URL_Shortner.Validation.NotBlocklisted;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequesDto {

    @NotBlank(message = "Original URL cannot be blank")
    @URL(message = "Invalid URL format")
    @Size(max = 2048, message = "Original URL cannot exceed 2048 characters")
    @NotBlocklisted(message = "URL belongs to a blocked domain")
    private String originalUrl;

    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$", message = "Short code must be 4-10 characters long and can only contain letters, numbers, underscores, and hyphens")
    private  String shortCode;

    private LocalDate expiryDate;
}
