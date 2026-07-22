package com.URI.URL_Shortner.Dto;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreatFeedResponse {
    private List<String> maliciousUrls;
    private  String generatedAt;
}
