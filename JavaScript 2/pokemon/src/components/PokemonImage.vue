<template>
    <img :src="image" />
</template>

<script>

export default {
    name: 'PokemonImage',

    props: {
        female: {
            type: Boolean,
            default: false
        },

        shiny: {
            type: Boolean,
            default: false
        },

        back: {
            type: Boolean,
            default: false
        },

        id: {
            type: Number,
            required: true
        }
    },

    computed: {
        image() {
            let url = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon';
            if (this.back) { url += '/back' }
            if (this.shiny) { url += '/shiny' }
            if (this.female) { url += '/female' }
            url += `/${this.id}.png`;
            return url;
        }
    }
}
</script>