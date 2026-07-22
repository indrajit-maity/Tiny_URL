package com.URI.URL_Shortner.Dto;


import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBlockListResponce {

    private long id;
    private String domain;
    private String reason;
    private BlocklistSource blocklistSource;
}
