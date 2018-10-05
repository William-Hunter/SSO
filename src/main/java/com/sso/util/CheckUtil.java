package com.sso.util;


import org.springframework.beans.factory.annotation.Value;

public class CheckUtil {
   static final String EMAIL="william@qq.com";
    static final String PASSWORD="1234";

    static public boolean check(String email,String password){
        if(EMAIL.equals(email)&&PASSWORD.equals(password)){
            return true;
        }else {
            return false;
        }
    }

}
