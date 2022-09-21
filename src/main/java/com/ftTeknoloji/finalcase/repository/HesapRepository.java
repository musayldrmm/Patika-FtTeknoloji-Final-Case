package com.ftTeknoloji.finalcase.repository;

import com.ftTeknoloji.finalcase.entity.Hesap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HesapRepository extends JpaRepository<Hesap, Long> {
    List<Hesap> findAllByCustomerid_Id(Long id);
}
