package com.controller;

import com.dao.AccountRepository;
import com.pojo.Account;


import com.utils.CaptchaUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping(value = "/account/check")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Expires","-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        CaptchaUtil util = CaptchaUtil.Instance();
        String code = util.getString();
        request.getSession().setAttribute("code",code);
        ImageIO.write(util.getImage(),"jpg",response.getOutputStream());
    }
    @RequestMapping(value = "/account/login",method = RequestMethod.POST,produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public JSONObject login(@RequestBody String params, HttpServletRequest req){
        JSONObject request = JSONObject.fromObject(params);
        String username = request.getString("username");
        String password = request.getString("password");
        String code = request.getString("code");
        JSONObject jo = new JSONObject();

        String real_code = req.getSession().getAttribute("code").toString();
        if (!code.toLowerCase().equals(real_code.toLowerCase())){
            jo.put("status",2);
            return jo;
        }
        Account account = accountRepository.getByUsername(username);
        if (account==null||!account.getPassword().equals(password)){
            jo.put("status",1);
            jo.put("message","用户不存在或密码错误");
            return jo;
        }
        req.getSession().setAttribute("username",username);
        jo.put("status",0);
        jo.put("message","登录成功");
        return jo;
    }
    @RequestMapping(value = "/account/register",method = RequestMethod.POST,produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public JSONObject register(@RequestBody String params, HttpServletRequest req){
        JSONObject request = JSONObject.fromObject(params);
        String username = request.getString("username");
        String password = request.getString("password");
        String telnum = request.getString("telnum");


        Account account = new Account(username,password,telnum);
        JSONObject jo = new JSONObject();
        if (accountRepository.getByUsername(username)!=null){
            jo.put("status",1);
            return jo;
        }
        accountRepository.save(account);
        jo.put("status",0);
        return jo;
    }

}
