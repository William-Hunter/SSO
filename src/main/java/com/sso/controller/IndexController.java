package com.sso.controller;

import com.sso.util.CheckUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/sso")
public class IndexController {

    @Value("${email}") String email;
    @Value("${password}") String password;

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, String email, String password, String gotoUrl){
        request.setAttribute("email",email);
        if(CheckUtil.checkUser(email, password)){
            session.setAttribute("email",email);
            Cookie cookie=new Cookie("SSO_cookie","sso");
            cookie.setPath("/");
            response.addCookie(cookie);
            if(gotoUrl!=null&&!"".equals(gotoUrl)){
                System.out.println("登录成功，进入"+gotoUrl);
                return gotoUrl;
            }else{
                System.out.println("登录成功，但是无法跳转");
                return "/login.jsp";
            }
        }else{
            System.out.println("登录失败，进入错误页面");
            return "/error.jsp";
        }
    }

    @RequestMapping(value = "/checkuser")
    public String checkuser(HttpServletRequest request, HttpServletResponse response,Integer site){
        if(CheckUtil.checkCookie(request)){
            System.out.println("cookie验证成功，进入用户界面");
            switch (site){
                case 1:
                    return "/site1/user.jsp";
                case 2:
                    return "/site2/user.jsp";
                default:break;
            }
        }else{
            System.out.println("cookie验证失败，进入登录界面");
            request.setAttribute("gotoUrl","/site"+site+"/user.jsp");
            return "/login.jsp";
        }
        return "/index.jsp";
    }



}
