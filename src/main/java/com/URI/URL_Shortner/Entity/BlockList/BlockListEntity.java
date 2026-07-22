package com.URI.URL_Shortner.Entity.BlockList;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


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
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "DOMAIN",unique = true,length = 255)
    private String domain;
    @Column(name = "REASON",length = 255)
    private  String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private  BlocklistSource source;

    @Column(name = "ADDED_AT",updatable = false,nullable = false)
    private LocalDate addAt;

}
