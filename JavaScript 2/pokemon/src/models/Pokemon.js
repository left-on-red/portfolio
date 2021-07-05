export default class Pokemon {
    constructor(data) {
        this.name = data.name;
        this.abilities = data.abilities.map((value) => { return { name: value.ability.name, hidden: value.is_hidden } });
        this.types = data.types.map(value => value.type.name);

        this.height = data.height;
        this.weight = data.weight;

        this.sprites = data.sprites;
        this.stats = {};

        for (let s = 0; s < data.stats.length; s++) { this.stats[data.stats[s].stat.name] = data.stats[s].base_stat }
    }
}