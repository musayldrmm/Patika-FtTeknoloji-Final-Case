package com.ftTeknoloji.finalcase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HesapTipi")
@Getter
@Setter
public class HesapTipi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String hesapAdi;

    @ManyToMany(mappedBy = "tipid",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Hesap> hesapid;
}
