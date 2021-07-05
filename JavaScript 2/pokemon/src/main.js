import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './router';

Vue.config.productionTip = false;

import VueConfetti from 'vue-confetti';
Vue.use(VueConfetti);

Vue.mixin({
    methods: {
        api: async function(resource) {
            if (!resource.startsWith('https://')) { resource = `https://pokeapi.co/api/v2/${resource}` }
            let response = await fetch(resource);
            return await response.json();
        },

        colorMap(color) {
            let colors = {
                black: '#263238',
                blue: '#3F51B5',
                brown: '#795548',
                gray: '#9E9E9E',
                green: '#4CAF50',
                pink: '#E91E63',
                purple: '#9C27B0',
                red: '#F44336',
                white: '#CFD8DC',
                yellow: '#FFEB3B'
            }

            let lights = {
                black: '#546E7A',
                blue: '#9FA8DA',
                brown: '#BCAAA4',
                gray: '#E0E0E0',
                green: '#A5D6A7',
                pink: '#F48FB1',
                purple: '#CE93D8',
                red: '#EF9A9A',
                white: '#ECEFF1',
                yellow: '#FFF59D'
            }

            return { color: colors[color], lighter: lights[color] };
        },

        final_stats(stats, evs, ivs, level, nature) {
            let obj = {};

            for (let k in obj) {
                let ev = evs[k];
                let iv = ivs[k];
                let n = nature[k];
                if (k == 'hp') {
                    // Stat = floor((2 * B + I + E) * L / 100 + L + 10)
                    obj[k] = Math.floor((2 * stats[k] + iv + ev) * level / 100 + level + 10);
                }

                else {
                    obj[k] = Math.floor(Math.floor((2 * stats[k] + iv + ev) * level / 100 + 5) * n);
                }
            }

            return obj;
        },

        nature_map(nature) {
            let obj = {
                'hp': 1,
                'attack': 1,
                'special-attack': 1,
                'defense': 1,
                'special-defense': 1,
                'speed': 1
            }

            switch(nature) {
                // hardy is netural
                case 'lonely': obj['attack'] = 1.1; obj['defense'] = 0.9; break;
                case 'brave': obj['attack'] = 1.1; obj['speed'] = 0.9; break;
                case 'adamant': obj['attack'] = 1.1; obj['special-attack'] = 0.9; break;
                case 'naughty': obj['attack'] = 1.1; obj['special-defense'] = 0.9; break;
                // docile is neutral
                case 'bold': obj['defense'] = 1.1; obj['attack'] = 0.9; break;
                case 'relaxed': obj['defense'] = 1.1; obj['speed'] = 0.9; break;
                case 'impish': obj['defense'] = 1.1; obj['special-attack'] = 0.9; break;
                case 'lax': obj['defense'] = 1.1; obj['special-defense'] = 0.9; break;
                // serious is neutral
                case 'timid': obj['speed'] = 1.1; obj['attack'] = 0.9; break;
                case 'hasty': obj['speed'] = 1.1; obj['defense'] = 0.9; break;
                case 'jolly': obj['speed'] = 1.1; obj['special-attack'] = 0.9; break;
                case 'naive': obj['speed'] = 1.1; obj['special-defense'] = 0.9; break;
                // bashful is neutral
                case 'modest': obj['special-attack'] = 1.1; obj['attack'] = 0.9; break;
                case 'mild': obj['special-attack'] = 1.1; obj['defense'] = 0.9; break;
                case 'quiet': obj['special-attack'] = 1.1; obj['speed'] = 0.9; break;
                case 'rash': obj['special-attack'] = 1.1; obj['special-defense'] = 0.9; break
                // quirky is neutral
                case 'calm': obj['special-defense'] = 1.1; obj['attack'] = 0.9; break;
                case 'gentle': obj['special-defense'] = 1.1; obj['defense'] = 0.9; break;
                case 'sassy': obj['special-defense'] = 1.1; obj['speed'] = 0.9; break;
                case 'careful': obj['special-defense'] = 1.1; obj['special-attack'] = 0.9; break;
            }

            return obj;
        }

        /**Stat = floor(floor((2 * B + I + E) * L / 100 + 5) * N)
        B = base
        I = IV
        E = EV
        L = Level
        N = Nature (1.1 if increases, 0.9 if decreases, 1 if neutral) */
    }
})

new Vue({
    vuetify,
    router,
    render: h => h(App)
}).$mount('#app');


