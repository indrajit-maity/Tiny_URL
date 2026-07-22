package com.URI.URL_Shortner.Service.Blocklist;


import com.URI.URL_Shortner.Dto.AddBlocklistRequest;
import com.URI.URL_Shortner.Dto.ThreatFeedResponse;
import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlocklistSyncService {

    private final BlocklistService blocklistService;
    private final RestTemplate restTemplate;

    @Scheduled(cron = "0 0 * * * *") //Runs every hour
    public void syncFromThreatFeed(){
        List<String> maliciousDomains=fetchFromExternalFeed();
        for(String Domain:maliciousDomains){
            blocklistService.addDomain(new AddBlocklistRequest(Domain,"Synced from external threat feed", BlocklistSource.THIRD_PARTY));
        }
    }

    private List<String> fetchFromExternalFeed(){
        try{
            ThreatFeedResponse response=restTemplate.getForObject("https://external-threat-feed.com/api/malicious-domains", ThreatFeedResponse.class);
            if(response==null){
                return List.of();
            }
            System.out.println(response.getGeneratedAt());
            return (response.getMaliciousUrls()!=null)?response.getMaliciousUrls():List.of();
        } catch (RestClientException e) {
            return List.of();
        }
    }
}
