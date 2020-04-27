package Model.Json;

import java.util.*;


public class Data {

    private ArrayList<Data> data;

    public Data(ArrayList<Data> data) {
        this.data = data;
    }

    public Data(Data dades) {

    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

}

