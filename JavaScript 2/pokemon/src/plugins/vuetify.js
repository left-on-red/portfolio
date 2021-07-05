import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#3F51B5', // indigo
                secondary: '#E91E63', // pink
                active: '#C5CAE9', // indigo lighten-4
                inactive: '#1A237E' // indigo darken-4
            }
        }
    }
});
