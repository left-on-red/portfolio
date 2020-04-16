package pokemonBattleSimulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// src: https://stackoverflow.com/questions/6607550/what-does-t-angle-brackets-mean-in-java
public abstract class Collection<T> {
    public Map<String, T> map;

    public Collection() {
        map = new HashMap<String, T>();
    }

    public void add(String key, T prop) { map.put(key, prop); }
    public T get(String key) { return map.get(key); }
    public ArrayList<T> createList(String list) {
        ArrayList<T> toReturn = new ArrayList<T>();
        String[] listArr = list.split(",");
        for (int i = 0; i < listArr.length; i++) { if (!listArr[i].equals("")) { toReturn.add(get(listArr[i].trim())); } }
        return toReturn;
    }
}