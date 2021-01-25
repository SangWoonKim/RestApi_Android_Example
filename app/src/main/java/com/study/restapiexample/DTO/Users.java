package com.study.restapiexample.DTO;

import com.google.gson.annotations.SerializedName;

public class Users {
    public int id;
    public String nickname;

    public int getId() {
        return id;
    }
    public String getNickname() {
        return nickname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString(){
        return "user [id = "+id+", nickname = "+nickname+" ]";
    }
}
