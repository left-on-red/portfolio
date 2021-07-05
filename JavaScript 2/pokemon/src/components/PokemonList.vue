<template>
    <div>
        <router-link v-for="(v, i) in indices" :key="v" :to="`/pokemon/${v+1}`" class="link">
            <PokemonCell :id="v+1" :grid="grid" @rendered="next(i)" />
        </router-link>
    </div>
</template>

<script>

import PokemonCell from '@/components/PokemonCell.vue';

export default {
    name: 'PokemonList',
    components: { PokemonCell },
    props: {
        query: {
            type: String,
            required: true
        },

        limit: {
            type: Number,
            default: 20
        },

        grid: {
            type: Boolean,
            default: false
        }
    },

    data() {
        return {
            loaded: 1,
            table: null
        }
    },

    methods: {
        next(index) {
            if (this.query == '' && index % this.limit == 0) {
                // 898
                console.log('loaded');
                this.loaded += 1;
            }
        }
    },

    computed: {
        indices() {
            if (this.table == null || this.query == '') { return Array.from(Array(this.limit * this.loaded).keys()) }
            else {
                let arr = [];
                for (let t = 0; t < this.table.length; t++) { if (this.table[t].includes(this.query.toLowerCase())) { arr.push(t) } }
                return arr.slice(0, this.limit);
            }
        }
    },

    mounted() {
        this.api('pokemon-species?limit=1000').then((data) => { this.table = data.results.map(v => v.name) });
    }
}
</script>

<style scoped lang="scss">
    .link {
        text-decoration: none;
        transition: all 0.2s;
        cursor: pointer;

        &:hover {
            filter: brightness(95%);
        }
    }
</style>