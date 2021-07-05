<template>
    <v-app-bar color="primary" fixed dense>
        <router-link to="/"><v-btn icon><v-icon :color="$route.path == '/' ? 'active' : 'inactive'">mdi-home</v-icon></v-btn></router-link>
        <v-toolbar-title>Pokemon</v-toolbar-title>
        <v-spacer></v-spacer>
        <router-link to="/collection"><v-btn icon><v-icon :color="$route.path == '/collection' ? 'active' : 'inactive'">mdi-sparkles</v-icon></v-btn></router-link>
    </v-app-bar>
</template>

<script>
export default {
    name: 'NavBar'
}
</script>