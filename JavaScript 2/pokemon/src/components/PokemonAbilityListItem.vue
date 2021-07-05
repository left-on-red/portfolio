<template>
    <div v-if="ability">
        <p class="name">
            {{ability.name}}
            <v-tooltip v-if="hidden" bottom>
                <template v-slot:activator="{ on, attrs }">
                    <span v-bind="attrs" v-on="on">(hidden)</span>
                </template>
                <span>Hidden Abilities are rare abilities normally<br>not found on wild Pokemon!</span>
            </v-tooltip>
        </p>
        <p class="flavor">{{ability.flavor}}</p>
    </div>
</template>

<script>

import Ability from '@/models/Ability.js';

export default {
    name: 'PokemonAbilityList',

    props: {
        obj: {
            type: Object,
            required: true
        }
    },

    data() {
        return {
            ability: null
        }
    },

    computed: {
        hidden() { return this.obj.hidden }
    },

    mounted() {
        this.api(`ability/${this.obj.name}`)
            .then((json) => { this.ability = new Ability(json) })
            .catch(console.error);
    }
}
</script>

<style scoped lang="scss">
    div {
        margin: 0;
        display: inline-block;

        p.name {
            margin: 0 5px;
            padding: 5px;
            font-weight: bold;
            position: relative;

                span {
                position: absolute;
                top: 0;
                font-size: 10px;
                padding: 5px;
                cursor: pointer;
                text-decoration: underline;
            }
        }

        p.flavor {
            font-size: 12px;
            margin: 10px;
        }
    }
</style>