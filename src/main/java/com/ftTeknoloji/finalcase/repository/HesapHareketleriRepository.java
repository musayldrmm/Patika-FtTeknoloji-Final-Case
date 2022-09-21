package com.ftTeknoloji.finalcase.repository;

import com.ftTeknoloji.finalcase.entity.HesapHareketleri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HesapHareketleriRepository extends JpaRepository<HesapHareketleri, Long> {
    List<HesapHareketleri> findFirst5ByHesapid_IdOrderByIslemtarihiDesc(Long id);
}
