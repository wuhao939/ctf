package com.controller;

import com.dao.MessageRepository;
import com.pojo.Message;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageRepository repository;

    //获取所有留言
    @RequestMapping(value = "/message",method = RequestMethod.GET)
    public List<Message> getAllMessage(){
        return repository.findAll();
    }
    //增加留言
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    public void addMessage(@RequestBody String requestBody, HttpServletRequest request){
        JSONObject params = JSONObject.fromObject(requestBody);
        String username = params.getString("username");
        String contexts = params.getString("contexts");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());

        Message message = new Message(username,contexts,time);
        repository.save(message);
    }
}
