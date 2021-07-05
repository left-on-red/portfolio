<template>
    <div class="view">
        <v-row>
            <v-col cols="1" lg="3">
                <v-btn color="primary" icon large class="btn-icon d-none d-md-block" @click="grid = !grid">
                    <v-icon v-if="grid">mdi-view-grid</v-icon>
                    <v-icon v-else>mdi-view-grid-outline</v-icon>
                </v-btn>
            </v-col>

            <v-col cols="10" lg="6">
                <v-text-field label="Search for Pokemon" v-model="query" />
                <PokemonList :query="query" :grid="grid" />
            </v-col>
            <v-col cols="1" lg="3"></v-col>
        </v-row>
    </div>
</template>

<script>
// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'

import PokemonList from '@/components/PokemonList.vue';

export default {
    name: 'Home',
    components: {
        PokemonList
    },

    data() {
        return {
            query: '',
            grid: false
        }
    }
}
</script>

<style scoped lang="scss">

.btn-icon {
    float: right;
    margin: 20px;
}

</style>