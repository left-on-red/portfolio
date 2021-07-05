let app = new Vue({
    el: '#app',
    vuetify: new Vuetify({
        theme: {
            themes: {
                light: {
                    primary: '#009688',
                    accent: '#3F51B5',
                    danger: '#E91E63'
                }
            }
        }
    }),

    data: {
        dialogs: {
            adding: false,
            deleting: false
        },

        games: Game.collection,
        filter: new Filter()
    },

    methods: {
        addGame(game) { this.games.push(game) },

        editGame(game) {
            this.games.editing = game;
            this.adding = true;
        },

        deleteGame(game) {
            this.games.deleting = game;
            this.deleting = true;
        },
        
        loadStorage() {
            let json = localStorage.getItem('games');

            if (json) { Game.populate(json) }
            else {

                // some default Game data will be populated (for testing purposes)
                let arr = [
                    {
                        name: 'The Legend of Zelda: Ocarina of Time',
                        publisher: 'Nintendo',
                        description: 'A fantasy action-adventure game set in an expansive environment. The player controls series protagonist Link from a third-person perspective in a three-dimensional world.',
                        tags: [ 'fantasy', 'action', 'adventure', 'puzzle-solving' ],
                        favorited: false
                    },

                    {
                        name: 'The Elder Scrolls V: Skyrim',
                        publisher: 'Bethesda',
                        description: 'An action role-playing game, playable from either a first or third-person perspective. The player may freely roam over the land of Skyrim which is an open world environment consisting of wilderness expanses, dungeons, caves, cities, towns, fortresses, and villages.',
                        tags: [ 'fantasy', 'roleplaying', 'first-person', 'adventure', 'open-world', 'action' ],
                        favorited: false
                    },

                    {
                        name: 'Animal Crossing: New Horizons',
                        publisher: 'Nintendo',
                        description: 'A life simulation game played in real time in an open-ended fashion as the player explores the island, and develops it into a community of anthropomorphic animals.',
                        tags: [ 'relaxing', 'community', 'multiplayer' ],
                        favorited: false
                    },

                    {
                        name: 'CrossCode',
                        publisher: 'Radical Fish Games',
                        description: 'A retro-inspired 2D action role-playing video game set in the distant future, combining 16-bit Super NES-style graphics with a fast-paced combat system and puzzle mechanics.',
                        tags: [ 'action', 'adventure', 'puzzle-solving', 'indie' ],
                        favorited: false
                    },

                    {
                        name: 'Metroid Prime',
                        publisher: 'Nintendo',
                        description: 'Explore the world of Tallon IV and witness the deadly experiments performed by the remaining Space Pirates in this first-person adventure game.',
                        tags: [ 'first-person', 'adventure', 'action', 'isolation' ],
                        favorited: false
                    }
                ]

                Game.populate(JSON.stringify(arr));
            }
        },

        // is it good practice to reference the Game class globally like this? I feel like it would be considered "cheating", but if only the main app component relies on it, I don't see a problem with it
        updateStorage() { localStorage.setItem('games', Game.json()) },
    },

    computed: {
        filteredGames() {
            let self = this;
            return self.games.filter(function(v) {
                // because of this major refactor, I was finally able to properly implement a favorites filter into the filter system
                if (self.filter.favorites && !v.favorited) { return false }
                
                if (v.name.toLowerCase().includes(self.filter.text.toLowerCase())) { return true }
                if (v.publisher.toLowerCase().includes(self.filter.text.toLowerCase())) { return true }
                if (v.description.toLowerCase().includes(self.filter.text.toLowerCase())) { return true }
                for (let t = 0; t < v.tags.length; t++) { if (v.tags[t].toLowerCase().includes(self.filter.text.toLowerCase())) { return true } }
            });
        }
    },

    watch: {
        // watches the games array (reference to Games.collection) and calls updateStorage() whenever there's changes
        games: {
            deep: true,
            handler() { this.updateStorage() }
        }
    },

    // loads any localStorage data upon page load
    mounted() { this.loadStorage() }
});