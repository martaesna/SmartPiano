package Model.DB;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLOperations {
    public static void registreUsuari (String name, String password, String mail) {
        ConectorDB conn = new ConectorDB("root", "Albamassa99", "Piano", 3306);
        conn.connect();
        conn.insertQuery("SELECT...");
    }
}