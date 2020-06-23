package Model;
import java.io.Serializable;
import java.util.LinkedList;


public class User implements Serializable {
    private String name;
    private String mail;
    private String password;
    private String codi;
    private LinkedList<String> amics;

    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCodi(String codi) {this.codi = codi;}
}
