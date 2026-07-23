package com.URI.URL_Shortner.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreatFeedResponse {
        @JsonProperty("dateadded")
        public String dateadded;
        @JsonProperty("url")
        public String url;
}
