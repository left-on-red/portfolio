package pokemonBattleSimulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Types {
    private Map<String, Type> map;
    // src: https://img.rankedboost.com/wp-content/uploads/2018/10/Pokemon-Lets-Go-Type-Chart.jpg
    public Type NORMAL = new Type("normal", "", "rock, steel", "ghost");
    private Type FIGHTING = new Type("fighting", "normal, rock, steel, ice, dark", "flying, poison, bug, psychic, fairy", "");
    private Type FLYING = new Type("flying", "fighting, bug, grass, fairy", "rock, steel, electric", "ground");
    private Type POISON = new Type("poison", "grass, fairy", "poison, ground, rock, ghost", "");
    private Type GROUND = new Type("ground", "poison, rock, steel, fire, electric", "bug, grass", "electric");
    private Type ROCK = new Type("rock", "flying, bug, fire, ice", "fighting, ground, steel", "");
    private Type BUG = new Type("bug", "grass, psychic, dark", "fighting, flying, poison, ghost, steel, fire, fairy", "");
    private Type GHOST = new Type("ghost", "ghost, psychic", "dark", "normal");
    private Type STEEL = new Type("steel", "rock, ice, fairy", "steel, fire, water, electric", "poison");
    private Type FIRE = new Type("fire", "bug, steel, grass, ice", "rock, fire, water dragon", "");
    private Type WATER = new Type("water", "ground, rock, fire", "water, grass, dragon", "");
    private Type GRASS = new Type("grass", "ground, rock, water", "flying, poison, bug, steel, fire, grass, dragon", "");
    private Type ELECTRIC = new Type("electric", "flying, water", "grass, electric, dragon", "");
    private Type PSYCHIC = new Type("psychic", "fighting, poison", "steel, psychic", "");
    private Type ICE = new Type("ice", "flying, ground, grass, dragon", "steel, fire, water, ice", "");
    private Type DRAGON = new Type("dragon", "dragon", "steel", "");
    private Type DARK = new Type("dark", "ghost, psychic", "fighting, dark, fairy", "psychic");
    private Type FAIRY = new Type("fairy", "fighting, dragon, dark", "poison, steel, fire", "dragon");

    public Types() {
        map = new HashMap<String, Type>();
        map.put("normal", NORMAL);
        map.put("fighting", FIGHTING);
        map.put("flying", FLYING);
        map.put("poison", POISON);
        map.put("ground", GROUND);
        map.put("rock", ROCK);
        map.put("bug", BUG);
        map.put("ghost", GHOST);
        map.put("steel", STEEL);
        map.put("fire", FIRE);
        map.put("water", WATER);
        map.put("grass", GRASS);
        map.put("electric", ELECTRIC);
        map.put("psychic", PSYCHIC);
        map.put("ice", ICE);
        map.put("dragon", DRAGON);
        map.put("dark", DARK);
        map.put("fairy", FAIRY);
    }

    public Type get(String key) { return map.get(key); }
    public ArrayList<Type> createList(String list) {
        ArrayList<Type> toReturn = new ArrayList<Type>();
        String[] listArr = list.split(",");
        for (int i = 0; i < listArr.length; i++) { if (!listArr[i].equals("")) { toReturn.add(this.get(listArr[i].trim())); } }
        return toReturn;
    }
}