<template>
    <v-app>
        <NavBar/>
        <router-view :key="$route.fullPath" :collection="collection" />
    </v-app>
</template>

<style>
    .view { margin-top: 75px; }
</style>

<script>
// @ is an alias to /src
import NavBar from '@/components/NavBar.vue'

export default {
    // <router-link to="/">Home</router-link> |
    // <router-link to="/about">About</router-link>
    name: 'App',
    data() {
        return {
            collection: localStorage.getItem('shiny-collection') ? JSON.parse(localStorage.getItem('shiny-collection')) : []
        }
    },

    components: {
        NavBar
    },

    watch: {
        collection: {
            deep: true,
            handler(val) { localStorage.setItem('shiny-collection', JSON.stringify(val)) }
        }
    }
}
</script>
