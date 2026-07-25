package com.URI.URL_Shortner.Dto;

import com.URI.URL_Shortner.Validation.NotBlocklisted;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDomainDto {

    @NotBlank
    private  String domain;
}
