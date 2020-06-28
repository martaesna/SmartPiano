package model;

import java.io.Serializable;

public class Amic implements Serializable {
    private String nickName;

    public Amic(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

