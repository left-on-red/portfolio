package edu.wctc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonFactoryTest {

    @Test
    void createPokemon() {
        Stats iv = new Stats(0, 0, 0, 0, 0, 0);
        Stats ev = new Stats(0, 0, 0, 0, 0, 0);

        assert PokemonFactory.createPokemon("Bulbasaur", 100, iv, ev).getTypes()[0] == TYPE.GRASS;
        assert PokemonFactory.createPokemon("Charmander", 100, iv, ev).getTypes()[0] == TYPE.FIRE;
        assert PokemonFactory.createPokemon("Squirtle", 100, iv, ev).getTypes()[0] == TYPE.WATER;
    }
}