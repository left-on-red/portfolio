<template>
    <v-lazy v-model="visible" :options="{ threshold: 0.5 }" transition="fade-transition" height="115" min-width="175" :style="`display: ${grid ? 'inline-block' : 'block'};`">
        <div v-if="pokemon != null && species != null" class="pokemon-cell-wrapper" :style="`display: ${grid ? 'inline-block' : 'block'}`">
            <div v-if="grid" class="pokemon-cell pokemon-cell-grid" :style="`background-color: ${lighter ? lighter : colors.lighter};`">
                <p :style="`background-color: ${color ? color : colors.color}; color: ${color ? 'white' : ['white', 'yellow'].includes(species.color) ? 'black' : 'white'};`">{{species.name}}</p>
                <PokemonImage :back="back" :female="female" :shiny="shiny" :id="id" />
            </div>

            <div v-else class="pokemon-cell pokemon-cell-row" :style="`background-color: ${lighter ? lighter : colors.lighter};`">
                <PokemonImage :back="back" :female="female" :shiny="shiny" :id="id" />
                <div class="content">
                    <p class="title" :style="`background-color: ${color ? color : colors.color}; color: ${color ? 'white' : ['white', 'yellow'].includes(species.color) ? 'black' : 'white'}`">
                        <span class="name">{{species.name}}</span>
                        <span class="id">#{{`${id}`.padStart(3, '0')}}</span>
                    </p>
                    <span class="types"><PokemonTypeList :types="pokemon.types" /></span>
                </div>
            </div>
        </div>
    </v-lazy>
</template>

<script>
import APIComponent from '@/components/APIComponent.vue';
import PokemonImage from '@/components/PokemonImage.vue';
import PokemonTypeList from '@/components/PokemonTypeList.vue';

export default {
    name: 'PokemonCell',

    extends: APIComponent,

    components: {
        PokemonImage,
        PokemonTypeList
    },

    props: {
        id: {
            type: Number,
            required: true
        },

        grid: {
            type: Boolean,
            default: false
        },

        color: {
            type: String,
            required: false
        },

        lighter: {
            type: String,
            required: false
        },

        back: {
            type: Boolean,
            default: false
        },

        female: {
            type: Boolean,
            default: false
        },

        shiny: {
            type: Boolean,
            default: false

            // FINISH MERGING POKEMONLISTITEM WITH POKEMONTILE
        }
    },

    data() {
        return {
            startup: false,
            table: null,
            visible: false
        }
    },

    methods: {
        //loadPokemon(id) {
            //api(`pokemon-species/${id}`).then((species) => { this.pokemon[`${id}`.padStart(3, '0')] = species }).catch(console.error);
        //}
    },

    computed: {
        colors() {
            if (this.species != null) { return this.colorMap(this.species.color) }
            else { return { color: 'white', lighter: 'white' } }
        }
    },

    watch: {
        visible(value) {
            if (value) {
                //console.log(value)
                this.$emit('rendered');
                if (!this.pokemon) { this.load() }
                //this.$emit('rendered');
                //if (!this.pokemon) { this.load() }
            }
        }
    },

    mounted() {
        
        /**const rect = element.getBoundingClientRect();
    return (
        rect.top >= 0 &&
        rect.left >= 0 &&
        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
        rect.right <= (window.innerWidth || document.documentElement.clientWidth)
    ); */
        //console.log(this.$refs.block.offsetTop);
        
        //console.log('mounted');
        //this.loadPokemon(i+1)
        //for (let i = 0; i < 20; i++) { console.log(i); this.pokemon[i] = { name: `test ${i}` } }
    }
}
</script>

<style scoped lang="scss">
    div.pokemon-cell-wrapper {
        div.pokemon-cell-row {
            height: 100px;
            width: 100%;
            border-radius: 15px;
            display: flex;
            overflow: hidden;
            position: relative;

            img {
                height: 100%;
                width: 100px;
                display: inline-block;
            }

            .content {
                flex: 1;
                

                .title {
                    padding: 5px 30px;
                    border-bottom-left-radius: 15px;

                    .id {
                        float: right;
                        font-family: monospace;
                        font-size: 16px;
                        font-weight: bold;
                    }
                }
            }

            .types {
                position: absolute;
                bottom: 0;
                margin: 5px;
            }
        }

        div.pokemon-cell-grid {
            display: inline-block;
            border-radius: 15px;
            width: 150px;
            height: 175px;
            overflow: hidden;
            margin: 10px;

            p {
                text-align: center;
                color: #E8EAF6;
                height: 25px;
                margin: 0;
                padding: 0;
                display: inline-block;
                width: 100%;
            }

            img {
                height: 150px;
                width: 150px;
            }
        }
    }
</style>