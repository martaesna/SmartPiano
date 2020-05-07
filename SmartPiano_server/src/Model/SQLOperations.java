package Model;

import Model.DDBB.ConectorDB;
import Model.Json.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Model.Json.JsonReader.llegeixJSON;

public class SQLOperations {
    public static void registreUsuari (String name, String password, String mail) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        conn.insertQuery("INSERT INTO User(nickname, email, password, codi) VALUES (" + "'" + name + "'" + "," + "'" + mail + "'" + "," + "'" + password + "'" + ")");
    }
    public static boolean existeixUsuari (String name) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        ResultSet rs = conn.selectQuery("SELECT u.nickname FROM User AS u WHERE u.nickname LIKE '" + name + "'");
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}