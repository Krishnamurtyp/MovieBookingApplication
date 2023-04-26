package com.rbp.movieapp.payload.response;

import org.bson.types.ObjectId;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private ObjectId _id;

    private String loginId;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, ObjectId  _id, String loginId, String email, List<String> roles) {
        this.token = accessToken;
        this._id = _id;
        this.loginId = loginId;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return loginId;
    }

    public void setUsername(String loginId) {
        this.loginId = loginId;
    }

    public List<String> getRoles() {
        return roles;
    }
}