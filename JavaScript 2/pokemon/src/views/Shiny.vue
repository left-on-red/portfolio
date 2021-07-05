<template>
    <div class="view" @keyup.space="add()">
        <v-dialog persistent v-model="shining" width="500">
            <v-card v-if="species">
                <v-card-title>You Caught a Shiny {{species.name}}!</v-card-title>
                <v-card-text>
                    And it only took you {{shiny.count}} tries.
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <router-link to="/collection">
                        <v-btn color="primary" text @click="confirm()">Collection <v-icon>mdi-chevron-right</v-icon></v-btn>
                    </router-link>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <v-row>
            <v-col cols="1" lg="2"></v-col>
            <v-col cols="10" lg="8">
                <v-row>
                    <v-col cols="12" md="4">
                        <div id="inputs">
                            <v-tooltip left>
                                <template v-slot:activator="{ on, attrs }">
                                    <span v-bind="attrs" v-on="on">
                                        <v-checkbox color="primary" label="Masuda" v-model="options.masuda" :disabled="options.generation < 4" />
                                    </span>
                                </template>
                                <span v-if="options.generation < 4">The Masuda Method is only<br>available in Generation 4+</span>
                                <span v-else>only applies to breed Pokemon,
                                <br>but if both parents are from a
                                <br>different region
                                <br>(US and JP for example),
                                <br>the shiny chance is multiplied
                                <br>by 5 in Generation 4, or multiplied
                                <br>by 6 in Generation 5+</span>
                            </v-tooltip>

                            <v-tooltip left>
                                <template v-slot:activator="{ on, attrs }">
                                    <span v-bind="attrs" v-on="on">
                                        <v-checkbox color="primary" label="Shiny Charm" v-model="options.charm" :disabled="options.generation < 5" />
                                    </span>
                                </template>
                                <span v-if="options.generation < 5">The Shiny Charm is only<br>available in generation 5+</span>
                                <span v-else>
                                    the shiny charm doubles the
                                    <br>chance of both hatching and
                                    <br>encountering a wild shiny
                                </span>
                            </v-tooltip>
                            <v-select :items="Array.from({ length: 7 }, (_, i) => i + 2)" label="generation" v-model="options.generation" />
                        </div>
                    </v-col>
                    <v-col cols="12" md="4">
                        <div v-if="pokemon != null" id="container">
                            <div class="chance">
                                1/{{chance}}
                                <br>
                                ({{percentage}})
                            </div>
                            <div class="preview">
                                <div class="title">{{species.name}}</div>
                                <div class="image">
                                    <PokemonImage shiny :id="id"></PokemonImage>
                                </div>
                                <div class="controls">
                                    <v-btn color="primary" class="white--text add" :disabled="warning_box" @click="add()">{{shiny.count}} <v-icon right dark>mdi-plus</v-icon></v-btn>
                                    <v-btn color="primary" class="shine" large icon :disabled="warning_box" @click="shine()"><v-icon dark>mdi-sparkles</v-icon></v-btn>
                                </div>
                            </div>
                        </div>
                    </v-col>
                    <v-col cols="12" md="4">
                        <div style="position: relative; padding-bottom: 100px;">
                            <v-alert dense outlined type="warning" icon="mdi-alert" :value="warning_box" style="position: absolute;" transition="fade-transition">{{warning}}</v-alert>
                            <v-alert dense outlined type="info" icon="mdi-information" :value="info_box" style="position: absolute;" transition="fade-transition">{{info}}</v-alert>
                        </div>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="1" lg="2"></v-col>
        </v-row>
    </div>
</template>

<script>

import APIComponent from '@/components/APIComponent.vue';
import PokemonImage from '@/components/PokemonImage.vue';

let locks = {
    // zekrom, reshiram, victini
    5: [ 644, 643, 494 ],

    // articuno, zapdos, moltres, mewtwo, xerneas, yveltal, zygarde, kyogre, groudon, raquaza, deoxys
    6: [ 144, 145, 146, 150, 716, 717, 718, 382, 383, 384, 386 ],

    // this list is only the pokemon that are shiny-locked in **both** Sun/Moon and UltraSun/UltraMoon.
    // there's going to need to be individual warnings for certain discrepancies regarding certain games

    // tapu koko, tapu lele, tapu bulu, tapu fini, cosmog, solgaleo, lunala, necrozma
    7: [ 785, 786, 787, 788, 789, 791, 792, 800 ],

    // galarian kanto birds are shiny locked but kantonian kanto birds are not.
    // additionally, most of the obtainable legends in gen 8 are only available through
    // dynamax adventures which have their own custom shiny rate of 1/300 (1/100 w/ shiny charm)

    // may also be cool to include a note about regieleki and regidrago about how they're the first legendaries
    // since gen 5 that haven't been shiny locked in their debute generation

    // zacian, zamazenta, eternatus, type: null, kubfu, urshifu, cosmog, poipole, glastrier, spectrier, calyrex, keldeo
    8: [ 888, 889, 890, 772, 891, 892, 789, 803, 896, 897, 898, 647 ]
}

let dmax_adventures = [
    // articuno, zapdos, moltres, mewtwo, raikou, entei, suicune
    144, 145, 146, 150, 243, 244, 245,

    // lugia, ho-oh, latias, latios, kyogre, groudon, rayquaza
    249, 250, 380, 381, 382, 383, 384,

    // uxie, mesprit, azelf, dialga, palkia, heatran, giratina
    480, 481, 482, 483, 484, 485, 487,

    // cresselia, tornadus, thundurus, landorus, reshiram, zekrom, kyurem
    488, 641, 642, 645, 643, 644, 646,

    // xerneas, yveltal, zygarde, tapu koko, tapu lele, tapu bulu, tapu fini
    716, 717, 718, 785, 786, 787, 788,

    // solgaleo, lunala, nihilego, buzzwole, pheromosa, xurkitree, celesteela
    791, 792, 793, 794, 795, 796, 797,

    // kartana, guzzlord, stakataka, blacephalon, necrozma
    798, 799, 805, 806, 800
]

// these pokemon are not technically "shiny locked" but the shiny variant of that pokemon or even the non-shiny variant
// were directly distribted through events and were not available otherwise
let unobtainables = {
    // mew
    2: [ 151 ],

    // celebi
    3: [ 251 ],

    // arceus, celebi
    4: [ 493, 251 ],

    // arceus, celebi, keldeo, meleotta
    5: [ 493, 251, 647, 648 ],

    // arceus, celebi, keldeo, meleotta, victini, diancie
    6: [ 493, 251, 647, 648, 494, 719 ],

    // keldeo, meleotta, victini, hoopa, volcanion, magearna, marshadow, zeraora
    7: [ 647, 648, 494, 720, 721, 801, 802, 807 ]
}

export default {
    name: 'Shiny',

    extends: APIComponent,

    components: {
        PokemonImage
    },

    props: {
        collection: {
            type: Array,
            required: true
        }
    },

    data() {
        return {
            options: {
                masuda: false,
                charm: false,
                generation: 2
            },

            shiny: {
                count: 0,
                caught: false
            },

            warning: null,
            info: null,

            warning_box: false,
            info_box: false,

            shining: false
        }
    },

    methods: {
        add() { this.shiny.count += 1 },

        shine() {
            if (!this.shining) {

                this.shining = true;
                this.$confetti.start({
                    particles: [{ type: 'circle' }, { type: 'rect' }],
                    windSpeedMax: 3,
                    defaultDropRate: 20,
                    particlesPerFrame: 6
                });

                setTimeout(() => { this.$confetti.stop() }, 2000)
            }
        },

        confirm() {
            this.shiny.caught = true;
            this.collection.push({
                id: this.id,
                shiny: JSON.parse(JSON.stringify(this.shiny)),
                options: JSON.parse(JSON.stringify(this.options))
            });

            localStorage.removeItem(`shiny-${this.id}`);
            localStorage.removeItem(`options-${this.id}`);
        },

        compute_warning() {
                let str = null;

                let legendMyth = this.species ? this.species.legendMyth : false;

                let locked = locks[this.options.generation] ? locks[this.options.generation].includes(this.id) : false;
                let unobtainable = unobtainables[this.options.generation] ? unobtainables[this.options.generation].includes(this.id) : false;

                if (legendMyth && this.options.masuda) { str = `Nearly every legendary or mythical Pokemon cannot be breed and only obtained via wild encounter/gift` }
                else if (locked) { str = `That Pokemon is shiny locked in generation ${this.options.generation}` }
                else if (unobtainable) { str = `That Pokemon is unobtainable in Generation ${this.options.generation} (shiny or otherwise not)` }
                else if (this.species ? this.species.generation[this.options.generation] == false : false) { str = `That Pokemon does not have a regional Pokedex ID corresponding to that generation` }

                if (str == null) { this.warning_box = false; setTimeout(() => { this.warning = str }, 200) }
                else { this.warning = str; this.warning_box = true; }
        },

        compute_info() {
            if (!this.warning_box) {
                let str = null;

                if (this.options.generation == 8 && [894, 895].includes(this.id)) { str = `Regieleki and Regidrago are the first Legendaries since Generation 5 that are shiny unlocked in their debute Generation` }
                else if (this.options.generation == 8 && [144, 145, 146].includes(this.id)) { str = `Galarian form Kanto Birds are shiny locked. Kantonian form Kanto Birds however, are not` }
                else if (this.options.generation == 8 && dmax_adventures.includes(this.id)) { str = `That Pokemon is only obtainable in Generation 8 through Dynamax Adventures` }
                else if (this.options.generation == 7 && [793, 794, 795, 796, 798, 799].includes(this.id)) { str = `The Ultra Beasts are shiny locked in Sun and Moon but are shiny unlocked in UltraSun and UltraMoon` }

                if (str == null) { this.info_box = false; setTimeout(() => { this.info = str }, 200) }
                else { this.info = str; this.info_box = true; }
            }

            else { this.info_box = false; }
        },

        first_generation() {
            if (!this.species) { return 1 }
            else { for (let g = 0; g < 8; g++) { if (this.species.generation[g+1]) { return g+1 } } }
        },

        compute_boxes() {
            this.compute_info();
            this.compute_warning();
        }
    },

    computed: {
        id() { return parseInt(this.$route.params.id) },

        chance() {
            let masuda = this.options.masuda;
            let charm = this.options.charm;
            let gen = this.options.generation;

            let base = gen > 5 ? 4096 : 8192;
            let value = 1;
            
            if (masuda && gen == 5) { value = 5 }
            else if (masuda && gen > 5) { value = 6 }

            if (charm && masuda) { value += 1 }
            else if (charm) { value += 2 }

            return Math.floor(base/value);
        },

        percentage() {
            return `${((1/this.chance) * 100).toFixed(3)}%`
        }
    },

    watch: {
        'options.generation'(gen) {
            if (gen < 5) { this.options.charm = false }
            if (gen < 4) { this.options.masuda = false }
        },

        options: {
            deep: true,
            handler(obj) {
                localStorage.setItem(`options-${this.id}`, JSON.stringify(obj));
                this.compute_boxes();
            }
        },

        shiny: {
            deep: true,
            handler(obj) { localStorage.setItem(`shiny-${this.id}`, JSON.stringify(obj)) }
        }
    },

    mounted() {
        if (localStorage.getItem(`shiny-${this.id}`) != undefined) { this.shiny = JSON.parse(localStorage.getItem(`shiny-${this.id}`)) }
        if (localStorage.getItem(`options-${this.id}`) != undefined) { this.options = JSON.parse(localStorage.getItem(`options-${this.id}`)) }

        this.$on('fetched', () => { setTimeout(() => { this.compute_boxes() }, 200) });
    }
}
</script>

<style scoped lang="scss">
    #inputs {
        margin-top: 50px;
        width: 100%;

        div.v-input {
            margin: 0;
        }
    }


    #container {
        text-align: center;

        .chance {
            width: 100%;
            display: inline-block;
            font-size: 18px;
        }

        .preview {
            display: inline-block;
            border-radius: 10px;
            overflow: hidden;
            margin: 0;
            padding: 0;
            height: 400px;
            width: 300px;

            .title {
                color: #E8EAF6;
                background-color: #3F51B5;
            }

            .image {
                background-color: #E8EAF6;
                margin: 0;
                padding: 0;
                width: 100%;
                height: auto;
                margin: 0 auto;
                display: inline-block;
                border-bottom-right-radius: 10px;
                border-bottom-left-radius: 10px;
            }

            .image img {
                width: 100%;
                height: 100%;
            }

            .controls {
                margin-top: 5px;

                .add {
                    width: 80%;
                }
            }
        }
    }

</style>
