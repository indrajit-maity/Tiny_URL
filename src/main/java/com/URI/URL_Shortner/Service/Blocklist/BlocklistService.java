package com.URI.URL_Shortner.Service.Blocklist;


import com.URI.URL_Shortner.Dto.AddBlockListResponce;
import com.URI.URL_Shortner.Dto.AddBlocklistRequest;
import com.URI.URL_Shortner.Dto.DeleteDomainDto;
import com.URI.URL_Shortner.Entity.BlockList.BlockListEntity;
import com.URI.URL_Shortner.Entity.BlockList.BlocklistSource;
import com.URI.URL_Shortner.Exception.DomainAlreadyExistsException;
import com.URI.URL_Shortner.Repository.BlockListRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlocklistService {

    private final ModelMapper modelMapper;
    private final BlockListRepository blockListRepository;
    private final BlocklistCacheService blocklistCacheService;


    public boolean isBlocked(String domain){
        log.debug("isBlocked()");
        System.out.println("isBlocked....");
        if(domain==null || domain.isBlank()){
            return false;
        }
        Boolean cached=blocklistCacheService.isBlockedCached(domain);
        if(cached!=null){
            System.out.println("Hit only redis...");

            return cached;
        }
        boolean blocked=blockListRepository.existsByDomain(domain);
        blocklistCacheService.cacheResult(domain,blocked);
        System.out.println("Hit database...");
        return blocked;
    }

    public AddBlockListResponce addDomain(AddBlocklistRequest addBlocklistRequest){
        log.info("addDomain()");
        String domain=addBlocklistRequest.getDomain();
        URI uri=URI.create(domain);
        String host=uri.getHost();
        if(blockListRepository.existsByDomain(host)){
            throw new DomainAlreadyExistsException("Domain already exists");
        }
        System.out.println(host);
        BlockListEntity blockListEntity=blockListRepository.save(BlockListEntity.builder()
                        .domain(host)
                        .reason(addBlocklistRequest.getReason())
                        .addAt(LocalDate.now())
                        .source(addBlocklistRequest.getBlocklistSource() != null ? addBlocklistRequest.getBlocklistSource() : BlocklistSource.MANUAL)
                .build());
        blocklistCacheService.evict(addBlocklistRequest.getDomain());
        return modelMapper.map(blockListEntity, AddBlockListResponce.class);
    }

    @Transactional
    public BlockListEntity removedomain( DeleteDomainDto deleteDomainDto) {
        if(!blockListRepository.existsByDomain(deleteDomainDto.getDomain())){
            throw new IllegalArgumentException("Domain doesn't exist"+deleteDomainDto.getDomain());
        }
        BlockListEntity blockListEntity=blockListRepository
                .findByDomain(deleteDomainDto.getDomain()).orElseThrow(()
                        ->new IllegalArgumentException("Domain doesn't exist"));
        blockListRepository.deleteByDomain(deleteDomainDto.getDomain());
        blocklistCacheService.evict(deleteDomainDto.getDomain());
        return blockListEntity;
    }
}
