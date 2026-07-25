package com.URI.URL_Shortner.Repository;

import com.URI.URL_Shortner.Entity.BlockList.BlockListEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BlockListRepository extends JpaRepository<BlockListEntity,Long> {
   boolean existsByDomain(String domain);
   void deleteByDomain(String domain);
   Optional<BlockListEntity> findByDomain(String domain);
}
