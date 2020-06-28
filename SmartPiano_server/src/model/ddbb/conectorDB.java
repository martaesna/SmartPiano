package model.ddbb;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conectorDB {
    static String userName;
    static String password;
    static String db;
    static int port;
    static String url = "jdbc:mysql://localhost";
    static Connection conn = null;
    static Statement s;

    public conectorDB(String usr, String pass, String db, int port) {
        conectorDB.userName = usr;
        conectorDB.password = pass;
        conectorDB.db = db;
        conectorDB.port = port;
        conectorDB.url += ":"+port+"/";
        conectorDB.url += db;
        conectorDB.url += "?verifyServerCertificate=false&useSSL=true";
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Connection");
            conn = (Connection)DriverManager.getConnection(url, userName, password);
            if (conn != null) {
                System.out.println("Conexió a base de dades " + url + " ... Ok");
            }
            return true;
        }
        catch(SQLException ex) {
            System.out.println("Problema al connectar-nos a la BBDD --> " + url);
        }
        catch(ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void disconnect(){
        try {
            conn.close();
            System.out.println("Desconnectat!");
        } catch (SQLException e) {
            System.out.println("Problema al tancar la connexió --> " + e.getSQLState());
        }
    }

    public void insertQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            s =(Statement) conn.createStatement();
            rs = s.executeQuery (query);
        } catch (SQLException ex) {
            System.out.println("Problema al Recuperar les dades --> " + ex.getSQLState());
        }
        return rs;
    }

    public void deleteQuery(String query) {
        try {
            s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
