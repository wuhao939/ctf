package com.dao;

import com.pojo.Trecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrecordRepository extends JpaRepository<Trecord,Integer>{

    List<Trecord> getTrecordByUsername(String username);

    List<Trecord> getTrecordByUsernameAndQId(String username,int q_id);

    @Query(nativeQuery = true,value = "SELECT sum(score)" +
            " FROM trecord JOIN question ON trecord.q_id=question.id " +
            " WHERE username=?1 " +
            "GROUP BY username ")
    int getScore(String username);


    @Query(nativeQuery = true,value = "SELECT username,sum(score) as score " +
            "FROM trecord JOIN question ON trecord.q_id=question.id " +
            "GROUP BY username " +
            "ORDER BY score DESC ")
    List<Object> getAllScore();



}
