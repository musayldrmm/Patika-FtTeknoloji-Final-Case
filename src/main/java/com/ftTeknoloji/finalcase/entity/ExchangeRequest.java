package com.ftTeknoloji.finalcase.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;
import java.sql.Date;


@Getter
@Setter
public class ExchangeRequest {
    private String alimaciklamasi;
    private String satimaciklamasi;
    private Float alimmiktari;
    private Float satimmiktari;

    private Date islemtarihi;
    @OneToOne
    private Hesap alimhesabi;
    @OneToOne
    private Hesap satimhesabi;
}
