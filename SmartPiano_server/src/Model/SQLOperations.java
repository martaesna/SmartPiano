package Model;

import Model.DDBB.ConectorDB;

public class SQLOperations {
    public static void registreUsuari (String name, String password, String mail) {
        ConectorDB conn = new ConectorDB("root", "Albamassa99", "Piano", 3306);
        conn.connect();
        conn.insertQuery("SELECT...");
    }
}