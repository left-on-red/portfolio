<script>
import Pokemon from '@/models/Pokemon.js';
import Species from '@/models/Species.js';

export default {
    data() {
        return {
            pokemon: null,
            species: null,
            startup: true
        }
    },

    methods: {
        load() {
            this.api(`pokemon-species/${this.id}`)
            .then((json) => {
                this.species = new Species(json);
                return this.api(this.species.next);
            })
            .then((json) => { this.pokemon = new Pokemon(json); this.$emit('fetched', { species: this.species, pokemon: this.pokemon }) })
            .catch(console.error);
        }
    },

    mounted() {
        if (this.id && this.startup == true) { this.load() }
    },

    watch: {
        id() { this.load() }
    }
}
</script>