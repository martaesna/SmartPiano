package model.json;

import com.google.gson.Gson;
import model.Cancion;
import model.FiguraMusical;
import model.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class JsonCançons {
    private Cancion cancion;

    /**
     * crea un json amb la canço creada
     * @param c canço creada
     * @param id id de la canço
     */
    public static void jsonSongs(Cancion c, int id){
        JSONObject songObj = new JSONObject();
        songObj.put("Id", id);
        songObj.put("Nom", c.getNombre());
        songObj.put("Privacitat", c.isPrivada());

        JSONArray notasArray = new JSONArray();
        for(FiguraMusical i : c.getFigurasMusicales()){
            JSONObject figura = new JSONObject();
            figura.put("nota", i.getNota());
            figura.put("tiempoInicio", i.getTiempoInicio());
            figura.put("tiempoFinal", i.getTiempoFinal());
            figura.put("tiempo", i.getTiempo());
            figura.put("escala", i.getEscala());
            notasArray.put(figura);
        }
        songObj.put("Notas", notasArray);

        String f = new File("").getAbsolutePath();
        try (FileWriter file = new FileWriter(f.concat  ("/SmartPiano_server/src/model/cançons/" + id + ".json"))) {
            file.write(songObj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * busca la canço a reproduir
     * @param canço canço que tria l'usuari a reproduir
     * @return canço sencera a reproduir
     */
    public static Cancion buscaCançoJson(Song canço) {
        LinkedList<Cancion> cançoTrobada = new LinkedList<>();
        String path = new File("").getAbsolutePath();
        try{
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            reader = new com.google.gson.stream.JsonReader(new FileReader(path.concat("/Smartpiano_server/src/model/cançons/cançons.json")));
            cançoTrobada = gson.fromJson(reader, Cancion.class);

            System.out.println("\nLectura JSON finalitzada.\n");

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
        for (Cancion c: cançoTrobada) {
            if(canço.getId() == c.getId()) {
                return c;
            }
        }
        return null;
    }
}
