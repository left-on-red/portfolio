package pokemonBattleSimulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pokemonBattleSimulator.moves.*;

public class Moves {
    private Map<String, Move> map;
    // src: https://bulbapedia.bulbagarden.net/wiki/List_of_moves
    //private static Type NORMAL = new Type("normal", "", "rock, steel", "ghost");
    private Move POUND = new Pound();
    private Move SWORDS_DANCE = new SwordsDance();
    private Move WATER_JET = new WaterJet();
    //private Move SWORDS_DANCE = 

    /*
8 	Ice Punch 	Ice 	Physical 	Beautiful 	15 	75 	100% 	I
9 	Thunder Punch 	Electric 	Physical 	Cool 	15 	75 	100% 	I
10 	Scratch 	Normal 	Physical 	Tough 	35 	40 	100% 	I
11 	Vice Grip 	Normal 	Physical 	Tough 	30 	55 	100% 	I
12 	Guillotine 	Normal 	Physical 	Cool 	5 	— 	—* * 	I
13 	Razor Wind 	Normal 	Special 	Cool 	10 	80 	100%* 	I
14 	Swords Dance 	Normal 	Status 	Beautiful 	20* 	— 	— 	I
15 	Cut 	Normal 	Physical 	Cool 	30 	50 	95% 	I
16 	Gust* 	Flying 	Special 	Clever 	35 	40 	100% 	I
17 	Wing Attack 	Flying 	Physical 	Cool 	35 	60* 	100% 	I
18 	Whirlwind 	Normal 	Status 	Clever 	20 	— 	—* 	I
19 	Fly 	Flying 	Physical 	Clever 	15 	90* 	95% 	I
20 	Bind 	Normal 	Physical 	Tough 	20 	15 	85%* 	I
21 	Slam 	Normal 	Physical 	Tough 	20 	80 	75% 	I
22 	Vine Whip 	Grass 	Physical 	Cool 	25* 	45* 	100% 	I 
    */


    public Moves() {
        map = new HashMap<String, Move>();
        map.put("pound", POUND);
        map.put("swords dance", SWORDS_DANCE);
        map.put("water jet", WATER_JET);
        //map.put("karate chop", KARATE_CHOP);
    }

    public Move get(String name) { return map.get(name); }
}