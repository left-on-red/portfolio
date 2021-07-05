<template>
    <div class="container">
        <p class="label">Abilities</p>
        <div class="list">
            <PokemonAbilityListItem v-for="(ability, i) in abilities" :key="i" :obj="ability" />
        </div>
    </div>
</template>

<script>

import PokemonAbilityListItem from '@/components/PokemonAbilityListItem.vue';

export default {
    name: 'PokemonAbilityList',

    components: { PokemonAbilityListItem },

    props: {
        abilities: {
            type: Array,
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

    },

    //mounted() { console.log(this.abilities) }
}
</script>

<style scoped lang="scss">
    div.container {
        overflow: hidden;
        background-color: #E8EAF6;
        margin: 0;
        padding: 0;
        border-radius: 10px;

        p.label {
            font-weight: bold;
            font-size: 14px;
            text-transform: uppercase;
            text-align: center;
            margin: 0;
            padding: 5px;
            color: #E8EAF6;
            background-color: #3F51B5;
            
        }
    }

</style>