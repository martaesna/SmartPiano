package Model.DB.Json;

import java.io.FileReader;
import com.google.gson.Gson;

public class JsonReader {
    private Data data;
    final String PATH = "/Users/mariorodriguez/Desktop/uni/haciendo/DPO/Haciendo/Project Piano/SmartPiano/SmartPiano_server/files/config.json";
    public void llegeixJSON(){
        Gson gson = new Gson();
        com.google.gson.stream.JsonReader reader;
        try{

            reader = new com.google.gson.stream.JsonReader(new FileReader(PATH));
            data = gson.fromJson(reader, Data.class);

            System.out.println("\nLectura JSON finalitzada.\n");





        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }


    }

    public Data getDades() {
        return data;
    }


}
