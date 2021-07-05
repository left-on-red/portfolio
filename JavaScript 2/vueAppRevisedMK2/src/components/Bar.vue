<template>
    <v-app-bar color="primary" app fixed>
        <span>My Game Collection</span>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-btn icon @click="filter.favorites = !filter.favorites">
            <v-icon v-if="filter.favorites" color="amber">mdi-star</v-icon>
            <v-icon v-else>mdi-star-outline</v-icon>
        </v-btn>
        <v-text-field append-icon="mdi-magnify" color="blue-grey darken-4" label="filter by name, publisher, tags, etc" v-model="filter.text"></v-text-field>
    </v-app-bar>
</template>

<script>
export default {
    name: 'Bar',
    props: {
        filter: {
            type: Object,
            required: true
        }
    }
}
</script>
