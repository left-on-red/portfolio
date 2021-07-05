<template>
    <div class="view">
        <v-dialog v-model="modal.active" width="500">
            <v-card>
                <v-card-title>{{modal.name}} ({{modal.count}})</v-card-title>
                <v-card-text>
                    <v-checkbox color="primary" label="Masuda" v-model="modal.masuda" disabled />
                    <v-checkbox color="primary" label="Shiny Charm" v-model="modal.charm" disabled />
                    <v-text-field label="Generation" v-model="modal.generation" disabled />
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="modal.active = false">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
        <v-row>
            <v-col></v-col>
            <v-col cols="12" sm="8" md="6">
                <a v-for="(v, i) in collection" :key="i" @click="modalate(i)">
                    <PokemonCell shiny grid color="#3F51B5" lighter="#E8EAF6" :id="v.id" @fetched="fetched($event, i)" />
                </a>
            </v-col>
            <v-col></v-col>
        </v-row>
    </div>
</template>

<script>

//import PokemonList from '@/components/PokemonList.vue';
//import PokemonTile from '@/components/PokemonTile.vue';
import PokemonCell from '@/components/PokemonCell.vue';


export default {
    name: 'Collection',

    components: {
        PokemonCell
    },

    props: {
        collection: {
            type: Array,
            required: true
        }
    },

    data() {
        return {
            modal: {
                active: false,
                name: null,
                count: false,
                masuda: false,
                charm: false,
                generation: 2,
            },

            list: {}
        }
    },

    methods: {
        modalate(index) {
            this.modal.name = this.list[index].species.name;
            this.modal.count = this.collection[index].shiny.count;
            this.modal.masuda = this.collection[index].options.masuda;
            this.modal.charm = this.collection[index].options.charm;
            this.modal.generation = this.collection[index].options.generation;
            this.modal.active = true;
        },

        fetched(obj, i) { this.list[i] = obj }
    }
}
</script>

<style scoped lang="scss">
    a {
        text-decoration: none;
        cursor: pointer;
        transition: all 0.2s;

        &:hover {
            filter: brightness(95%);
        }
    }
</style>
