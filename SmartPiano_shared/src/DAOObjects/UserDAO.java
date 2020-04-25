package DAOObjects;

import java.io.Serializable;

public class UserDAO implements Serializable {
    public String nickname;
    public String email;
    public String password;

    public UserDAO (String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getNickname(){
        return this.nickname;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    //changes are here!
}

