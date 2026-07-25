package com.URI.URL_Shortner.Controller;


import com.URI.URL_Shortner.Dto.AddBlockListResponce;
import com.URI.URL_Shortner.Dto.AddBlocklistRequest;
import com.URI.URL_Shortner.Dto.DeleteDomainDto;
import com.URI.URL_Shortner.Entity.BlockList.BlockListEntity;
import com.URI.URL_Shortner.Service.Blocklist.BlocklistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/blocklist")
@RequiredArgsConstructor
@Tag(name = "Admin Blocklist", description = "Endpoints for managing the blocklist")
public class AdminController {

    private  final BlocklistService blocklistService;


    @Operation(summary = "Add domain to blocklist", description = "Adds a domain to the blocklist")
    @PostMapping("/add")
    ResponseEntity<AddBlockListResponce> addDomain(@Valid @RequestBody AddBlocklistRequest addBlocklistRequest){
        System.out.println("Indrajit maity");
        System.out.println(addBlocklistRequest.getDomain());
        AddBlockListResponce addBlockListResponce=blocklistService.addDomain(addBlocklistRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addBlockListResponce);
    }


    @Operation(summary = "Delete Domain from blocklist")
    @DeleteMapping("/remove")
    ResponseEntity<BlockListEntity> removeDomain(@Valid @RequestBody DeleteDomainDto deleteDomainDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(blocklistService.removedomain(deleteDomainDto));
    }

}
