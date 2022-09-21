package com.ftTeknoloji.finalcase.controller;

import com.ftTeknoloji.finalcase.entity.ExchangeRequest;
import com.ftTeknoloji.finalcase.entity.Hesap;
import com.ftTeknoloji.finalcase.excetions.PostErrorMessage;
import com.ftTeknoloji.finalcase.repository.HesapHareketleriRepository;
import com.ftTeknoloji.finalcase.repository.HesapRepository;
import com.ftTeknoloji.finalcase.service.ExchangeServisi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exchange")
@CrossOrigin(origins = {"http://localhost:8085"})
public class ExchangeController {
    @Autowired
    private ExchangeServisi exchangeServisi;

    @Autowired
    private HesapRepository hesaprepository;
    @Autowired
    private HesapHareketleriRepository hesapHareketleriRepository;


    @PostMapping("/buy-sold")
    public ResponseEntity buyCurrency(@RequestBody ExchangeRequest exchangeRequest) {

        Optional<Hesap> satimhesabi=hesaprepository.findById(exchangeRequest.getSatimhesabi().getId());
        if (satimhesabi.get().getMiktar()>=exchangeRequest.getSatimmiktari()){
            exchangeServisi.ExchangeRequest(exchangeRequest);
            return ResponseEntity.ok().build();
        }
else {
    throw new PostErrorMessage("Alim-Satim yapmak istediğiniz miktar kadar hesap bakiyeniz yok");
        }
    }
}
