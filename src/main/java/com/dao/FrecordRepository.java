package com.dao;

import com.pojo.Frecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrecordRepository extends JpaRepository<Frecord,Integer>{
    List<Frecord> getFrecordByUsername(String username);
}
