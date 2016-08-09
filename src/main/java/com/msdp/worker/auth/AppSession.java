package com.msdp.worker.auth;


import java.util.Date;

public class AppSession {

    String userName;
    String sessionKey;
    Date expiration;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expirationTime) {
        this.expiration = expirationTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
