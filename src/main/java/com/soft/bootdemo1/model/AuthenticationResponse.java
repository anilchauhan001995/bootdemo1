package com.soft.bootdemo1.model;

public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationResponse(){
        this.jwt = null;
    }

    public String getJwt() {
        return jwt;
    }

}
