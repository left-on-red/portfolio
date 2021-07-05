let generations = [
    [ 'kanto' ],
    [ 'original-johto' ],
    [ 'hoenn', 'kanto' ],
    [ 'original-sinnoh', 'extended-sinnoh', 'updated-johto' ],
    [ 'original-unova', 'updated-unova' ],
    [ 'kalos-central', 'kalos-coastal', 'kalos-mountain', 'updated-hoenn' ],
    [ 'original-alola', 'original-melemele', 'original-akala', 'original-ulaula', 'original-poni', 'updated-alola', 'updated-melemele', 'updated-akala', 'updated-ulaula', 'updated-poni' ],
    [ 'galar', 'isle-of-armor', 'crown-tundra' ]
]

export default class Species {
    constructor(data) {
        this.capture_rate = data.capture_rate;
        this.egg_groups = data.egg_groups.map(value => value.name);
        this.genus = data.genera.filter(value => value.language.name == 'en')[0].genus;
        this.name = data.names.filter(value => value.language.name == 'en')[0].name;
        this.color = data.color.name;
        this.dex = data.flavor_text_entries.filter(value => value.language.name == 'en')[this.dex = data.flavor_text_entries.filter(value => value.language.name == 'en').length - 1].flavor_text;

        this.next = data.varieties.filter(value => value.is_default == true)[0].pokemon.url;

        this.legendMyth = data.is_legendary || data.is_mythical;
        this.genderDifferent = data.has_gender_differences;

        let dexies = data.pokedex_numbers.map(value => value.pokedex.name);

        this.generation = {
            1: false,
            2: false,
            3: false,
            4: false,
            5: false,
            6: false,
            7: false,
            8: false
        }

        for (let g = 0; g < 8; g++) {
            let gen = g+1;
            for (let d = 0; d < dexies.length; d++) {
                if (generations[g].includes(dexies[d])) { this.generation[gen] = true; break; }
            }        
        }
    }
}