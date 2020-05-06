package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuari implements Serializable {
    private String nickname;
    private String email;
    private String password;
    private String code;
    private ArrayList<Amic> amics = new ArrayList<>();

    public Usuari(String nickname, String email, String password, String code) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.code = code;
    }
///madafakaa
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //////////////////A IMPLEMENTAR/////////////////////////

    //public boolean comprovaDades(){}
    //public void tocarCanco(){}
    //public boolean comencaGravacio(){}
    //public boolean aturaGravacio(){}
    public void configuraTecles(){}
    public void pujaCanco(){}
    public void veureCancons(){}
    public void veureCanconsAmsitat(String name){}
    public void reproduirCanconsAmistat(String friendname){}

   // public SilenceButton activarSilenceButton(Button silencebutton){}
    //////////////////A IMPLEMENTAR/////////////////////////






}
