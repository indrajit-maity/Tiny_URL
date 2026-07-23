package com.URI.URL_Shortner.Service;


import com.URI.URL_Shortner.Dto.AddBlocklistRequest;
import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import com.URI.URL_Shortner.Repository.BlockListRepository;
import com.URI.URL_Shortner.Service.Blocklist.BlocklistCacheService;
import com.URI.URL_Shortner.Service.Blocklist.BlocklistService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import  static  org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@ExtendWith(MockitoExtension.class)
//@RequiredArgsConstructor
public class BlocklistServicetest {

    @Mock
    private BlockListRepository blockListRepository;

    @Mock
    private BlocklistCacheService blocklistCacheService;

    @Mock
    private BlocklistSource blocklistSource;

    @InjectMocks
    private BlocklistService  blocklistService;

    @Test
    void blocklistServicetest(){
//        AddBlocklistRequest addBlocklistRequest=new AddBlocklistRequest("https://www.dropbox.com/scl/fi/oknv4pn19fgybla5mqapr/xwcha.ps1?rlkey=2a71ypt7mm7ypocciv63lpoj5&st=vau4oec2&dl=1","Synced from external threat feed",blocklistSource.THIRD_PARTY);
//        boolean result=blockListRepository.existsByDomain(addBlocklistRequest.getDomain())?true:false;
//        List<String> domain=blockListRepository.findAll();
        assertTrue(true);
        boolean result=blockListRepository.existsByDomain("http://110.37.73.3:54669/bin.sh");
        assertTrue(result);
    }

}
