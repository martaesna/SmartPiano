package model.json;

import java.io.FileReader;
import com.google.gson.Gson;

public class JsonReader {
    static Data data = new Data();

    /**
     * llegeix el json de configuració
     * @return classe data amb la informacio llegida
     */
    public static Data llegeixJSON(){
        try{
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            reader = new com.google.gson.stream.JsonReader(new FileReader("SmartPiano_server/files/config.json"));
            data = gson.fromJson(reader, Data.class);

            System.out.println("\nLectura JSON finalitzada.\n");

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
        return data;
    }
    public Data getDades() {
        return data;
    }
}

