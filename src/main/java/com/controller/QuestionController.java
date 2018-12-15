package com.controller;

import com.dao.MessageRepository;
import com.dao.QuestionRepository;
import com.pojo.Message;
import com.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionRepository repository;
    //获取所有问题
    @RequestMapping(value = "/question")
    @ResponseBody
    public List<Question> getAllQuestion(){
        return repository.findAll();
    }
}
