package com.ftTeknoloji.finalcase.controller;


import com.ftTeknoloji.finalcase.entity.Hesap;
import com.ftTeknoloji.finalcase.service.HesapServisi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/hesap")
@Transactional
@CrossOrigin(origins = {"http://localhost:8085"})
public class HesapController {
    @Autowired
    private HesapServisi hesapServisi;

    @GetMapping("/add")
    public ResponseEntity addHesap(@RequestBody Hesap hesap) {
        return ResponseEntity.ok(hesapServisi.add(hesap));
    }

    @GetMapping("/get-all/{id}")
    public ResponseEntity getAllHesap(@PathVariable Long id) {
        return ResponseEntity.ok(hesapServisi.getAll(id));
    }


}
