<template>
    <div class="view">
        <v-row>
            <v-col cols="1" md="2"></v-col>
            <v-col cols="10" md="8">
                <div v-if="pokemon != null">
                    <v-row>
                        <v-col cols="12" sm="8" md="4">
                            <div class="name">
                                <p class="species_name">{{species.name}}<span class="dex_id">#{{`${this.id}`.padStart(3, '0')}}</span></p>
                                <PokemonTypeList :types="pokemon.types" />
                                <p class="species_genus">the "{{species.genus}}"</p>
                            </div>
                            <div class="stats">
                                <p class="stats_label">base stats</p>
                                <p class="stats_chart"><StatSpreadChart :stats="pokemon.stats" /></p>
                            </div>
                        </v-col>
                        <v-col cols="12" sm="8" md="8">
                            <v-row>
                                <v-col cols="12" md="6">
                                    <PokemonImageViewer :unlockedShiny="found_shiny" :genderDifferent="species.genderDifferent" :id="this.id" />
                                    <router-link v-if="!found_shiny" :to="`/shiny/${this.id}`" tag="div" class="hunt-link">Shiny Hunt this Pokemon</router-link>
                                </v-col>
                                <v-col cols="12" md="6">
                                    <PokemonAbilityList :abilities="pokemon.abilities" />
                                </v-col>
                                <v-col cols="12">
                                    <PokemonFlavorComponent :text="species.dex" />
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </div>
            </v-col>
            <v-col cols="1" md="2"></v-col>
        </v-row>
    </div>
</template>

<script>

import APIComponent from '@/components/APIComponent.vue';
import PokemonTypeList from '@/components/PokemonTypeList.vue';
import StatSpreadChart from '@/components/StatSpreadChart.vue';
import PokemonImageViewer from '@/components/PokemonImageViewer.vue';
import PokemonAbilityList from '@/components/PokemonAbilityList.vue';
import PokemonFlavorComponent from '@/components/PokemonFlavorComponent.vue';

export default {
    name: 'Pokemon',

    extends: APIComponent,

    components: {
        PokemonTypeList,
        StatSpreadChart,
        PokemonImageViewer,
        PokemonAbilityList,
        PokemonFlavorComponent
    },

    props: {
        collection: {
            type: Array,
            required: true
        }
    },

    computed: {
        id() { return parseInt(this.$route.params.id) },
        found_shiny() { return this.collection.map(p => p.id).includes(this.id) }
    }
}
</script>

<style scoped lang="scss">

    .name {
        width: 100%;

        .species_name {
            font-size: 32px;
            font-weight: bold;
            text-align: center;
            position: relative;
            margin: 0;

            .dex_id {
                font-size: 12px;
                position: absolute;
                top: 5px;
            }
        }

        .type-list {
            text-align: center;
            padding: 0 0 5px 0;
            margin: 0;
        }

        .species_genus {
            font-size: 14px;
            text-align: center;
            margin-top: 5px;
        }
    }

    .stats {
        margin-top: 10px;

        .stats_label {
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            margin: 0;
        }

        .stats_chart {
            canvas {
                margin: 0 auto;
            }
        }
    }

    .hunt-link {
        text-align: center;
        font-size: 12px;
        color: #3F51B5;
        cursor: pointer;
    }

    .hunt-link:hover {
        text-decoration: underline;
    }

</style>
