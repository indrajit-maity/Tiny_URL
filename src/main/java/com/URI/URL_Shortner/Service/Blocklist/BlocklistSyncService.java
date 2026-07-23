package com.URI.URL_Shortner.Service.Blocklist;


import com.URI.URL_Shortner.Dto.AddBlocklistRequest;
import com.URI.URL_Shortner.Dto.ThreatFeedResponse;
import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlocklistSyncService {

    private final BlocklistService blocklistService;
    private final RestTemplate restTemplate;
    @Value("${thread.feed.api.key}")
    private String APIKEY;
    @Scheduled(cron = "0 0 * * * *") //Runs every 1 hour
    public void syncFromThreatFeed(){
        System.out.println("Syncing from external threat feed...");
        List<String> maliciousDomains=fetchFromExternalFeed();
        for(String Domain:maliciousDomains){
            System.out.println("Adding domain to blocklist: "+Domain);
            blocklistService.addDomain(new AddBlocklistRequest(Domain,"Synced from external threat feed", BlocklistSource.THIRD_PARTY));
        }
    }

    private List<String> fetchFromExternalFeed(){
        System.out.println("Fetching blocklist from external threat feed..."+ APIKEY);
        log.info(APIKEY);
        try{
            ResponseEntity<Map<String,List<ThreatFeedResponse>>> responseEntity=restTemplate.exchange(APIKEY,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, List<ThreatFeedResponse>>>() {});
            Map<String,List<ThreatFeedResponse>> map=responseEntity.getBody();
            List<String> domains=map.values()
                    .stream()
                    .flatMap(List::stream)
                    .map(ThreatFeedResponse::getUrl)
                    .limit(10)
                    .toList();

//            for (List<ThreatFeedResponse> list : data.values()) {
//                for (ThreatFeedResponse threat : list) {
//                    urls.add(threat.getUrl());
//                }
//            }

            if(domains.isEmpty()){return List.of();}
            System.out.println("Fetched malicious domains at: ");
            return domains;
        } catch (RestClientException e) {
            System.out.println("Error fetching from external threat feed: " + e.getMessage());
            return List.of();
        }
    }
}
