package com.URI.URL_Shortner.Service;


import com.URI.URL_Shortner.Dto.AddBlocklistRequest;
import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import com.URI.URL_Shortner.Service.Blocklist.BlocklistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlocklistServicetest {


    @Autowired
    private BlocklistService blocklistService;


    @Test
    public void testBlocklistServicetest(){
        var result=blocklistService.isBlocked(
                "http://107.175.91.154/pty4");
        Assertions.assertTrue(result, "blocklist service test");
    }

}