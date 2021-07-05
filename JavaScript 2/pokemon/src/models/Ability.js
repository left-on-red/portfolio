export default class Ability {
    constructor(data) {
        this.name = data.names.filter(value => value.language.name == 'en')[0].name;
        this.flavor = data.flavor_text_entries.filter(value => value.language.name == 'en')[data.flavor_text_entries.filter(value => value.language.name == 'en').length - 1].flavor_text;
        this.effect = data.effect_entries.filter(value => value.language.name == 'en')[0].short_effect;
    }
}