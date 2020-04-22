package Model.DB;

public class ConnectorBBDD {
    private int portBBDD;
    private String ipServerBBDDD;
    private String nomBBDD;
    private Usuari userBBDD;

    public ConnectorBBDD(int portBBDD, String ipServerBBDDD, String nomBBDD) {
        this.portBBDD = portBBDD;
        this.ipServerBBDDD = ipServerBBDDD;
        this.nomBBDD = nomBBDD;
    }

    public int getPortBBDD() {
        return portBBDD;
    }

    public void setPortBBDD(int portBBDD) {
        this.portBBDD = portBBDD;
    }

    public String getIpServerBBDDD() {
        return ipServerBBDDD;
    }

    public void setIpServerBBDDD(String ipServerBBDDD) {
        this.ipServerBBDDD = ipServerBBDDD;
    }

    public String getNomBBDD() {
        return nomBBDD;
    }

    public void setNomBBDD(String nomBBDD) {
        this.nomBBDD = nomBBDD;
    }


}
