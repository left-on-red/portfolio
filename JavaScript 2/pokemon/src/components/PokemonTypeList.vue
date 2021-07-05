<template>
    <div class="type-list">
        <span class="chip-container" v-for="(type, index) in types" :key="index">
            <v-chip :color="colors[index].color" :dark="colors[index].dark" label x-small>{{type.toUpperCase()}}</v-chip>
        </span>
    </div>
</template>

<script>
//async function api(resource) {
//    let response = await fetch(`https://pokeapi.co/api/v2/${resource}`);
//    return await response.json();
//}

let types = {
    bug: {
        color: '#CDDC39', // lime
        dark: false
    },

    dark: {
        color: '#3E2723', // brown darken-4
        dark: true
    },

    dragon: {
        color: '#651FFF', // deep-purple accent-3
        dark: true,
    },

    electric: {
        color: '#FFEB3B', // yellow
        dark: false
    },

    fairy: {
        color: '#F8BBD0', // pink lighten-4
        dark: false
    },

    fighting: {
        color: '#B71C1C', // red darken-4
        dark: true
    },

    fire: {
        color: '#FF5722', // deep-orange,
        dark: true
    },

    flying: {
        color: '#B3E5FC', // light-blue lighten-4
        dark: false
    },

    ghost: {
        color: '#311B92', // deep-purple darken-4
        dark: true
    },

    grass: {
        color: '#4CAF50', // green
        dark: true
    },

    ground: {
        color: '#BCAAA4', // brown lighten-3
        dark: false
    },

    ice: {
        color: '#80DEEA', // cyan lighten-3
        dark: false
    },

    normal: {
        color: '#CFD8DC', // blue-grey lighten-4
        dark: false
    },

    poison: {
        color: '#9C27B0', // purple
        dark: true
    },

    psychic: {
        color: '#E91E63', // pink
        dark: true
    },

    rock: {
        color: '#A1887F', // brown lighten-2
        dark: true
    },

    steel: {
        color: '#78909C', // blue-grey lighten-1
        dark: true
    },

    water: {
        color: '#2196F3', // blue
        dark: true
    }
}

export default {
    name: 'PokemonTypeList',
    props: {
        types: {
            type: Array,
            required: true
        }
    },

    computed: {
        colors() {
            let arr = [];

            for (let t = 0; t < this.types.length; t++) { arr.push(types[this.types[t]]) }

            return arr;
        }
    }
}
</script>

<style scoped lang="scss">
    .chip-container {
        margin: 3px;
    }
</style>