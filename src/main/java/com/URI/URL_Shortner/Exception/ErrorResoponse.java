package com.URI.URL_Shortner.Exception;

import lombok.*;

import java.time.LocalDate;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResoponse {
    private int status;
    private String message;
    private LocalDate timestamp;
}
