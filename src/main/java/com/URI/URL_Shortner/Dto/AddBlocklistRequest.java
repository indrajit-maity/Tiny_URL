package com.URI.URL_Shortner.Dto;


import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBlocklistRequest {

    @NotBlank(message = "Domain cannot be blank")
    private String Domain;
    @NotBlank(message = "Reason cannot be null")
    private String reason;
    private BlocklistSource blocklistSource;
}
