package com.URI.URL_Shortner.Service.Blocklist;


import com.URI.URL_Shortner.Dto.AddBlockListResponce;
import com.URI.URL_Shortner.Dto.AddBlocklistRequest;
import com.URI.URL_Shortner.Entity.BlockList.BlockListEntity;
import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import com.URI.URL_Shortner.Exception.DomainAlreadyExistsException;
import com.URI.URL_Shortner.Repository.BlockListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlocklistService {

    private final ModelMapper modelMapper;
    private final BlockListRepository blockListRepository;
    private final BlocklistCacheService blocklistCacheService;


    public boolean isBlocked(AddBlocklistRequest addBlocklistRequest){
        log.debug("isBlocked()");
        System.out.println("isBlocked....");
        if(addBlocklistRequest.getDomain()==null || addBlocklistRequest.getDomain().isBlank()){
            return false;
        }
        Boolean cached=blocklistCacheService.isBlockedCached(addBlocklistRequest.getDomain());
        if(cached!=null){
            return cached;
        }
        boolean blocked=blockListRepository.existsByDomain(addBlocklistRequest.getDomain());
        blocklistCacheService.cacheResult(addBlocklistRequest.getDomain(),blocked);
        return blocked;
    }

    public AddBlockListResponce addDomain(AddBlocklistRequest addBlocklistRequest){
        System.out.println("indra_service");
        if(blockListRepository.existsByDomain(addBlocklistRequest.getDomain())){
            throw new DomainAlreadyExistsException("Domain already exists");
        }
        BlockListEntity blockListEntity=blockListRepository.save(BlockListEntity.builder()
                        .domain(addBlocklistRequest.getDomain())
                        .reason(addBlocklistRequest.getReason())
                        .addAt(LocalDate.now())
                        .source(addBlocklistRequest.getBlocklistSource() != null ? addBlocklistRequest.getBlocklistSource() : BlocklistSource.MANUAL)
                .build());
        blocklistCacheService.evict(addBlocklistRequest.getDomain());
        return modelMapper.map(blockListEntity, AddBlockListResponce.class);
    }
}
