package Model.DDBB;

import Model.DDBB.ConectorDB;
import Model.Json.Data;
import Model.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Model.Json.JsonReader.llegeixJSON;

public class SQLOperations {
    public static void registreUsuari (String name, String mail, String password, String codi) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        conn.insertQuery("INSERT INTO User(nickname, email, password, codi) VALUES (" + "'" + name + "'" + "," + "'" + mail + "'" + "," + "'" + password + "'" + "," + "'" + codi + "'" +")");
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
    public static boolean loginUsuariCorrecte (String name, String password) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT u.nickname FROM User AS u WHERE (u.nickname LIKE '" + name + "' OR u.email LIKE '" + name + "') AND u.password LIKE '" + password + "'";
        ResultSet rs = conn.selectQuery(query);
        try {
            if (rs.isBeforeFirst()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void BorrarUsuari(String name){
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        //String query =
    }

    public static ArrayList<Song> demanaCançons() {
        ArrayList<Song> songs = new ArrayList<>();
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT s.name, s.author, s.times_played FROM Song AS s";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                songs.add(new Song(rs.getString("name"), rs.getString("author"), rs.getInt("times_played")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public static void eliminaCanço(String name, String autor) {
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Song AS s WHERE s.name LIKE '" + name + "'AND s.author LIKE '" + autor + "';";
        conn.deleteQuery(query);
    }

    public static ArrayList<Song> demanaTop5() {
        ArrayList<Song> songs = new ArrayList<>();
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT s.name, s.author, s.times_played FROM Song AS s ORDER BY s.times_played DESC LIMIT 5";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                songs.add(new Song(rs.getString("name"), rs.getString("author"), rs.getInt("times_played")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public static ArrayList<String> calculaEvolucio() {
        ArrayList<String> songs = new ArrayList<>();
        Data data;
        data = llegeixJSON();
        ConectorDB conn = new ConectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT SUM(s.times_played), SUM(s.minutes_listened) FROM Song AS s";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                songs.add(rs.getString(1));
                songs.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }
}