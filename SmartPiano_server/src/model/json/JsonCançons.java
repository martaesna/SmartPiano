package model.json;

import model.Cancion;
import model.FiguraMusical;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonCançons {
    public static void jsonSongs(Cancion c, int id){
        JSONObject songObj = new JSONObject();
        songObj.put("Id", id);
        songObj.put("Nom", c.getNombre());
        songObj.put("Privacitat", c.isPrivada());

        JSONArray locationArray = new JSONArray();
        for(FiguraMusical i : c.getFigurasMusicales()){
            JSONObject figura = new JSONObject();
            figura.put("nota", i.getNota());
            figura.put("tiempoInicio", i.getTiempoInicio());
            figura.put("tiempoFinal", i.getTiempoFinal());
            figura.put("tiempo", i.getTiempo());
            figura.put("escala", i.getEscala());
            locationArray.put(figura);
        }
        songObj.put("Llista cançons", locationArray);

        String f = new File("").getAbsolutePath();
        try (FileWriter file = new FileWriter(f.concat  ("/SmartPiano_server/src/model/cançons/cançons.json"))) {
            file.write(songObj.toString());
            System.out.println("skrrrrrrrr");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
