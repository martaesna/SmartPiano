package Model.Json;


import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.util.ArrayList;

public class ReadJson {
    private Data data;

    public void llegeixJSON(){

        try{
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new FileReader("files/config.json"));
            this.data = gson.fromJson(jsonReader, Data.class);
            System.out.println("\nLectura JSON finalitzada.\n");

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
    }

    public Data getDades() {
        return data;
    }


}
