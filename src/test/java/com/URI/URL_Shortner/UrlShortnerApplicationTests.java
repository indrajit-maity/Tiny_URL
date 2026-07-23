package com.URI.URL_Shortner;

import com.URI.URL_Shortner.Dto.ThreatFeedResponse;
import com.URI.URL_Shortner.Service.Blocklist.BlocklistService;
import com.URI.URL_Shortner.Service.Blocklist.BlocklistSyncService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static javax.management.Query.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UrlShortnerApplicationTests {

	@Test
	void contextLoads() {
	}




}
