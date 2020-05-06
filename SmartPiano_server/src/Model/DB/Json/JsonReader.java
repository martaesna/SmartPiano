package Model.DB.Json;

import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class LlegirJson {
    private Data data;
    // String PATH = 'C:\Users\marta\Desktop\SmartPiano\SmartPiano_server\src\assets\config.json';
    public void llegeixJSON(){
        try{
            Gson gson = new Gson();
            JsonReader reader = null;
         //   com.google.gson.stream.JsonReader reader;
            reader = new JsonReader(new FileReader("src/assets/config.json"));
            data = gson.fromJson(reader,Data.class);
          //  reader = new com.google.gson.stream.JsonReader(new FileReader("C:\\Users\\marta\\Desktop\\SmartPiano\\SmartPiano_server\\files\\config.json"));
          //  data = gson.fromJson(reader, Data.class);

            System.out.println("\nLectura JSON finalitzada.\n");

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
    }

    public Data getDades() {
        return data;
    }


}
