package com.ftTeknoloji.finalcase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "HesapHareketi")
@Getter
@Setter
public class HesapHareketleri {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date islemtarihi;

    private Float miktar;

    private String aciklama;

    @OneToOne
   @JsonIgnore
    private Hesap hesapid;
}
