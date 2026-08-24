package com.URI.URL_Shortner.Entity.BlockList;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "blocklist")
public class BlockListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "domain",length = 255,nullable = false)
    private String domain;
    @Column(name = "reason",length = 255)
    private  String reason;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private  BlocklistSource source;

    @Column(name = "added_at",updatable = false,nullable = false)
    private LocalDate addAt;
    
}
