package com.sso.controller;

import com.sso.util.CheckUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Value("${email}") String email;
    @Value("${password}") String password;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String email, String password){
        request.setAttribute("email",email);
        if(CheckUtil.check(email, password)){
            Cookie cookie=new Cookie("SSO_cookie","sso");
            response.addCookie(cookie);
            return "/index.jsp";
        }else{
            return "/error.jsp";
        }
    }

}
