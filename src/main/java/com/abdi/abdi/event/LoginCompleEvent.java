package com.abdi.abdi.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.time.Clock;
public class LoginCompleEvent extends ApplicationEvent {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginCompleEvent(String token) {
        super(token);
        this.token = token;
    }

}
