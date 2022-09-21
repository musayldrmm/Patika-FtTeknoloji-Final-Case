package com.ftTeknoloji.finalcase.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Hesaplar")
@Getter
@Setter
public class Hesap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float miktar;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "Rel_HesapTip__Hesap", joinColumns = @JoinColumn(name = "Hesap_id"), inverseJoinColumns = @JoinColumn(name = "Hesap_Tip_id"))
    @JsonIgnoreProperties(value = "hesapid")
    private List<HesapTipi> tipid;

    @ManyToOne
    private Customer customerid;
}
