package com.sso.util;


import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CheckUtil {
   static final String EMAIL="william@qq.com";
    static final String PASSWORD="1234";

    static public boolean checkUser(String email,String password){
        if(EMAIL.equals(email)&&PASSWORD.equals(password)){
            return true;
        }else {
            return false;
        }
    }

    static public boolean checkCookie(HttpServletRequest request){
        Cookie[] cookies =request.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("SSO_cookie")&&cookie.getValue().equals("sso")){
                return true;
            }
        }
        return false;
    }


}
