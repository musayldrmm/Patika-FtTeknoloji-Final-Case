package com.ftTeknoloji.finalcase.service;

import com.ftTeknoloji.finalcase.entity.ExchangeRequest;
import com.ftTeknoloji.finalcase.entity.Hesap;
import com.ftTeknoloji.finalcase.entity.HesapHareketleri;
import com.ftTeknoloji.finalcase.repository.HesapHareketleriRepository;
import com.ftTeknoloji.finalcase.repository.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ExchangeServisi {

    @Autowired
    private HesapRepository hesaprepository;
    @Autowired
    private HesapHareketleriRepository hesapHareketleriRepository;

@Transactional
    public void ExchangeRequest(ExchangeRequest exchangeRequest) {

        Optional<Hesap> alimHesap = hesaprepository.findById(exchangeRequest.getAlimhesabi().getId());
        Optional<Hesap> satimHesap = hesaprepository.findById(exchangeRequest.getSatimhesabi().getId());

        alimHesap.get().setMiktar(alimHesap.get().getMiktar() + exchangeRequest.getAlimmiktari());
        satimHesap.get().setMiktar(satimHesap.get().getMiktar() + (exchangeRequest.getSatimmiktari()));
        HesapHareketleri alimHesapHareketleri = new HesapHareketleri();
        HesapHareketleri satimHesapHareketleri = new HesapHareketleri();

        alimHesapHareketleri.setAciklama(exchangeRequest.getAlimaciklamasi());
        alimHesapHareketleri.setMiktar(exchangeRequest.getAlimmiktari());
        alimHesapHareketleri.setIslemtarihi(exchangeRequest.getIslemtarihi());
        alimHesapHareketleri.setHesapid(exchangeRequest.getAlimhesabi());

        satimHesapHareketleri.setAciklama((exchangeRequest.getSatimaciklamasi()));
        satimHesapHareketleri.setMiktar(exchangeRequest.getSatimmiktari());
        satimHesapHareketleri.setHesapid(exchangeRequest.getSatimhesabi());
        satimHesapHareketleri.setIslemtarihi(exchangeRequest.getIslemtarihi());


        hesapHareketleriRepository.save(alimHesapHareketleri);
        hesapHareketleriRepository.save(satimHesapHareketleri);

    }
}
