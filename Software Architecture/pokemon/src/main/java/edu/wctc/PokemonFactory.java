package edu.wctc;

import edu.wctc.pokemons.Bulbasaur;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class PokemonFactory {
    private static final String[] names = { "Bulbasaur", "Charmander", "Squirtle" };

    public static Pokemon createPokemon(String name, int level, Stats iv, Stats ev) {
        try {
            if (Arrays.asList(names).contains(name)) {
                String className = "edu.wctc.pokemons." + name;
                Class pokemonClass = Class.forName(className);
                Pokemon pokemonObject = (Pokemon)pokemonClass.getDeclaredConstructor().newInstance();
                pokemonObject.initialize(level, iv, ev);
                return pokemonObject;
            }


        }

        catch (ClassNotFoundException |
                InstantiationException |
                IllegalArgumentException |
                NoSuchMethodException |
                IllegalAccessException |
                InvocationTargetException |
                SecurityException e) {}

        return null;
    }
}
