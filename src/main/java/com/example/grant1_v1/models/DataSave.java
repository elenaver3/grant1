package com.example.grant1_v1.models;
import java.util.prefs.Preferences;
public class DataSave {
    Preferences preferences;
    public DataSave(String name){
        preferences = Preferences.userRoot().node(name);

    }
    public void saveValue(String name,String value){
        preferences.put(name, value);
    }
    public String getValue(String name,String value){
        return preferences.get(name,value);
    }
    public void deleteValue(String name){
        preferences.remove(name);
    }

}
