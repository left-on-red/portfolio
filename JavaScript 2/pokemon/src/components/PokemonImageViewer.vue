<template>
    <div class="container">
        <div class="image_header">
            <p class="image_label">front</p>
            <p class="image_label">back</p>
        </div>
        <div class="controls">
            <v-btn v-if="genderDifferent" icon @click="female = !female">
                <v-icon v-if="female" color="pink">mdi-gender-female</v-icon>
                <v-icon v-else color="indigo">mdi-gender-male</v-icon>
            </v-btn>
            <v-btn v-if="unlockedShiny" icon @click="shiny = !shiny">
                <v-icon :color="`${shiny ? '#9575CD' : '#78909C'}`">mdi-sparkles</v-icon>
            </v-btn>
        </div>

        <PokemonImage :female="female" :shiny="shiny" :id="id" />
        <PokemonImage :female="female" :shiny="shiny" back :id="id" />
    </div>
</template>

<script>

import PokemonImage from '@/components/PokemonImage.vue';

export default {
    name: 'PokemonImageViewer',

    components: {
        PokemonImage
    },

    props: {
        unlockedShiny: {
            type: Boolean,
            required: true
        },

        genderDifferent: {
            type: Boolean,
            default: false
        },

        id: {
            type: Number,
            required: true
        }
    },

    data() {
        return {
            female: false,
            shiny: false
        }
    },

    methods: {
        //loadPokemon(id) {
            //api(`pokemon-species/${id}`).then((species) => { this.pokemon[`${id}`.padStart(3, '0')] = species }).catch(console.error);
        //}
    },

    computed: {
        front_image() {
            if (this.female) { return this.shiny ? this.images.front_shiny_female : this.images.front_female }
            else { return this.shiny ? this.images.front_shiny : this.images.front_default }
        },

        back_image() {
            if (this.female) { return this.shiny ? this.images.back_shiny_female : this.images.back_female }
            else { return this.shiny ? this.images.back_shiny : this.images.back_default }
        }
    }
}
</script>

<style scoped lang="scss">

.container {
    border-radius: 10px;
    overflow: hidden;
    margin: 0;
    padding: 0;
    background-color: #E8EAF6;
    position: relative;

    div.image_header {
        p {
            background-color: #3F51B5;
            color: #E8EAF6;
            display: inline-block;
            width: 50%;
            text-align: center;
            padding: 5px;
            font-size: 14px;
            font-weight: bold;
            margin: 0;
            text-transform: uppercase;
        }
    }

    div.controls {
        position: absolute;
        right: 5px;
    }

    img { width: 50%; }
}

</style>