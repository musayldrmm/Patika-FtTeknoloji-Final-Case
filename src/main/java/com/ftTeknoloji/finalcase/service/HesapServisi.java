package com.ftTeknoloji.finalcase.service;

import com.ftTeknoloji.finalcase.entity.Customer;
import com.ftTeknoloji.finalcase.entity.Hesap;
import com.ftTeknoloji.finalcase.entity.HesapHareketleri;
import com.ftTeknoloji.finalcase.entity.HesapTipi;
import com.ftTeknoloji.finalcase.repository.CustomerRepository;
import com.ftTeknoloji.finalcase.repository.HesapHareketleriRepository;
import com.ftTeknoloji.finalcase.repository.HesapRepository;
import com.ftTeknoloji.finalcase.repository.HesapTipiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HesapServisi {
    @Autowired
    private HesapRepository hesaprepository;
    @Autowired
    private HesapHareketleriRepository hesapHareketleriRepository;

    @Autowired
    private HesapTipiRepository hesapTipiRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addcustomer(Customer customer){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(customer.getPassword());
        System.out.println("password: " + password);
        customer.setPassword(password);
        return customerRepository.save(customer);
    }
    public Hesap add(Hesap hesap) {
        return hesaprepository.save(hesap);
    }
    public List<Hesap> getAll(Long id) {
        List<Hesap> denemehesabi=hesaprepository.findAllByCustomerid_Id(id);
        return denemehesabi;
    }

    public List<HesapHareketleri> getAllTransaction(Long id) {
        return hesapHareketleriRepository.findFirst5ByHesapid_IdOrderByIslemtarihiDesc(id);
    }


    public void reloadData(){
    }
    public void testdata(){

        HesapTipi dolar = new HesapTipi();
        HesapTipi altin = new HesapTipi();
        HesapTipi TL = new HesapTipi();
        HesapTipi euro = new HesapTipi();

        Hesap dolarmevduat = new Hesap();
        Hesap altinmevduat = new Hesap();
        Hesap TLmevduat = new Hesap();
        Hesap euromevduat = new Hesap();



        dolar.setId(1L);
        dolar.setHesapAdi("dolar");

        altin.setId(2L);
        altin.setHesapAdi("altin");

        TL.setId(3L);
        TL.setHesapAdi("tl");

        euro.setId(4L);
        euro.setHesapAdi("euro");




        hesapTipiRepository.save(dolar);
        hesapTipiRepository.save(altin);
        hesapTipiRepository.save(TL);
        hesapTipiRepository.save(euro);


        return;
    }
}
