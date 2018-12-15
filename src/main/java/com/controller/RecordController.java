package com.controller;

import com.dao.FrecordRepository;
import com.dao.TrecordRepository;
import com.pojo.Frecord;
import com.pojo.Trecord;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@ResponseBody
public class RecordController {
    @Autowired
    private FrecordRepository frecordRepository;
    @Autowired
    private TrecordRepository trecordRepository;

    //获取所有错误的答题记录
    @RequestMapping(value = "/frecord")
    public List<Frecord> getFrecord(String username){
        List<Frecord> frecordList = frecordRepository.getFrecordByUsername(username);
        return frecordList;
    }
    //判断该题是否做过
    @RequestMapping(value = "/done")
    public JSONObject done(String username,int q_id){
        List<Trecord> trecordList = trecordRepository.getTrecordByUsernameAndQId(username,q_id);
        JSONObject jo = new JSONObject();
        if (trecordList!=null&&trecordList.size()>0)
            jo.put("done",1);
        else
            jo.put("done",0);
        return jo;
    }
    //获取所有的正确答题记录
    @RequestMapping(value = "/trecord")
    public List<Trecord> getTrecord(String username){
        List<Trecord> trecordList = trecordRepository.getTrecordByUsername(username);
        return trecordList;
    }
    //获得单个用户的分数
    @RequestMapping(value = "/score")
    public JSONObject getScore(String username){
        int score = trecordRepository.getScore(username);
        JSONObject jo = new JSONObject();
        jo.put("score",score);
        return jo;
    }
    //获得所有用户的分数排行   username score
    @RequestMapping(value = "/allscore")
    public List<JSONObject> getAllScore(){

        List<Object> list = trecordRepository.getAllScore();
        List<JSONObject> res = new ArrayList<JSONObject>();
        for (Object object:list){
            Object[] o = (Object[]) object;
            JSONObject jo = new JSONObject();
            jo.put("username",o[0]);
            jo.put("score",o[1]);
            res.add(jo);
        }
        return res;
    }
}
