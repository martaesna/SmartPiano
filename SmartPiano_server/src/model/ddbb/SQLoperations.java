package model.ddbb;

import model.Amic;
import model.Cancion;
import model.json.Data;
import model.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static model.json.JsonReader.llegeixJSON;

public class SQLoperations {
    public static void registreUsuari (String name, String mail, String password, String codi) {
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        System.out.println("skr");
        conn.insertQuery("INSERT INTO User(nickname, email, password, codi) VALUES (" + "'" + name + "'" + "," + "'" + mail + "'" + "," + "'" + password + "'" + "," + "'" + codi + "'" +")");
    }
    public static boolean existeixUsuari (String name) {
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
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
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
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

    public static void borraUsuari(String name){
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM User WHERE nickname LIKE '" + name + "' OR email LIKE '" + name + "'";
        conn.deleteQuery(query);
    }

    public static ArrayList<Song> demanaCançons() {
        ArrayList<Song> songs = new ArrayList<>();
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
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
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Song AS s WHERE s.name LIKE '" + name + "'AND s.author LIKE '" + autor + "';";
        conn.deleteQuery(query);
    }

    public static ArrayList<Song> demanaTop5() {
        ArrayList<Song> songs = new ArrayList<>();
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
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
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
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

    public static void guardaCanço(Song song) {
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "INSERT INTO Song(name, author, duration, times_played, minutes_listened, privacity) VALUES('" + song.getName() + "','" + song.getAutor()
        + "'," + song.getDuration() + "," + song.getTimes_played() + "," + song.getMinutesListened() + ",'" + song.isPrivacity() + "')";
        conn.insertQuery(query);
    }

    public static LinkedList<Amic> amicsUsuari(String usuari) {
        LinkedList<Amic> amics = new LinkedList<>();
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT f.nickname2 FROM Friend AS f WHERE f.nickname1 LIKE '" + usuari + "'";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                amics.add(new Amic(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amics;
    }

    public static String trobaNickname(String usuari) {
        String nickname = null;
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT u.nickname FROM User AS u WHERE u.email LIKE '" + usuari + "'";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                nickname = (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nickname;
    }

    public static void eliminaAmic(String amic, String usuari) {
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "DELETE FROM Friend AS f WHERE f.nickname2 LIKE '" + amic + "'AND f.nickname1 LIKE '" + usuari + "';";
        conn.deleteQuery(query);
    }

    public static void afegeixAmic(String nomAmic, String nomUsuari) {
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "INSERT INTO Friend VALUES('" + nomUsuari + "','" + nomAmic + "')";
        conn.insertQuery(query);
    }

    public static String repNomAmic(String codiAmic) {
        String nickname = null;
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT u.nickname FROM User AS u WHERE codi LIKE '" + codiAmic + "'";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                nickname = (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nickname;
    }

    public static boolean amicExisteix(String nickname, String nomUsuari) {
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT f.nickname2 FROM Friend AS f WHERE nickname2 LIKE '" + nickname + "' AND nickname1 LIKE '" + nomUsuari + "'";
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

    public static LinkedList<Song> demanaCançonsReproduir(String nomUsuari) {
        LinkedList<Song> cançons = new LinkedList<>();
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "SELECT s.songId, s.name, s.author FROM Song AS s WHERE s.privacity = true OR s.author LIKE '" + nomUsuari + "'";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                Song canço = new Song();
                canço.setId(rs.getInt(1));
                canço.setName(rs.getString(2));
                canço.setAutor(rs.getString(3));
                cançons.add(canço);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cançons;
    }

    public static int afegeixCanço(Cancion canço, String nomUsuari) {
        int id = 0;
        long duration = calculaDuracio(canço);
        Data data;
        data = llegeixJSON();
        conectorDB conn = new conectorDB(data.getUser(), data.getPassword(), data.getDb(), data.getPort());
        conn.connect();
        String query = "INSERT INTO Song(name,author,duration,times_played,minutes_listened,privacity) VALUES('" + canço.getNombre() + "','"
                + nomUsuari + "', " + duration + ", 0, 0, " + canço.isPrivada() + ")";
        conn.insertQuery(query);
        query = "SELECT s.songId FROM Song AS s WHERE s.name LIKE '" + canço.getNombre() + "' AND s.author LIKE '" + nomUsuari + "'";
        ResultSet rs = conn.selectQuery(query);
        try {
            while (rs.next()) {
                id = (rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private static long calculaDuracio(Cancion canço) {
        long duration = 0;
        for (int i = 0; i < canço.getFigurasMusicales().size(); i++) {
            duration = duration + canço.getFigurasMusicales().get(i).getTiempo();
        }
        return duration;
    }
}

