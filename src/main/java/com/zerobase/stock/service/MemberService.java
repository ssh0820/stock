package com.zerobase.stock.service;


import com.zerobase.stock.model.Auth;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public UserDetails loadUserByUsername(String username) {


        return null;
    }

    public Object authenticate(Auth.SignIn request) {

        return null;
    }

    public Object register(Auth.SignUp request) {

        return null;
    }
}
