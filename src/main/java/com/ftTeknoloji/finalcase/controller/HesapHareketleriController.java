package com.ftTeknoloji.finalcase.controller;

import com.ftTeknoloji.finalcase.repository.HesapHareketleriRepository;
import com.ftTeknoloji.finalcase.service.HesapServisi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hesap-hareketleri")
@CrossOrigin(origins = {"http://localhost:8085"})
public class HesapHareketleriController {
    @Autowired
    private HesapServisi hesapServisi;

    @GetMapping("/get-all-transaction/{id}")
    public ResponseEntity getAllTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(hesapServisi.getAllTransaction(id));
    }

}
