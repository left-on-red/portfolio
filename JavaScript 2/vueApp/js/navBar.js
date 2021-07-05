Vue.component('nav-bar', {
    data: function() {
        return {
            filterFavorites: false,
            filterText: ''
        }
    },

    template: `
    <v-app-bar color="primary" app fixed>
        <span>My Game Collection</span>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-text-field append-icon="mdi-magnify" color="blue-grey darken-4" label="filter by name, publisher, tags, etc" v-model="filterText" @input="filterBox"></v-text-field>
    </v-app-bar>
    `,

    methods: {
        filterBox(text) { this.$emit('filtered', text) },
        clearFilter() {
            if (this.filterText == '') { this.filterBox(' ') }
            else { this.filterBox('') }
            this.filterText = '';
        }
    }
});