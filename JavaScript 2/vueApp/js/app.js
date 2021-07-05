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
        adding: false,
        deleting: false,
        deletingIndex: -1,
        games: [],
        filter: ''
    },

    methods: {
        addGame(game) { this.games.push(game); this.updateStorage() },

        startDeleteGame(game) {
            this.deletingIndex = this.games.indexOf(game);
            this.$refs.deleteDialog.title = game.name;
        },

        // had to shake off the object reference, so I made it parse a stringified object to do so
        startEditGame(game) { this.$refs.gameDialog.edit(JSON.parse(JSON.stringify(game)), this.games.indexOf(game)) },

        editGame(obj) { this.games[obj.index] = obj.game; this.updateStorage(); this.$refs.nav.clearFilter() },

        removeGame() { this.games.splice(this.deletingIndex, 1); this.deletingIndex = -1; this.updateStorage(); },
        
        loadStorage() {
            let json = localStorage.getItem('games');
            if (json) { this.games = JSON.parse(json) }
            else {
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

                this.games = arr;
            }
        },

        updateStorage() {
            let json = JSON.stringify(this.games);
            localStorage.setItem('games', json);
        },

        setFilter(text) { this.filter = text }
    },

    computed: {
        filteredGames() {
            let self = this;
            return self.games.filter(function(v, i) {
                if (v.name.toLowerCase().includes(self.filter.toLowerCase())) { return true }
                if (v.publisher.toLowerCase().includes(self.filter.toLowerCase())) { return true }
                if (v.description.toLowerCase().includes(self.filter.toLowerCase())) { return true }
                for (let t = 0; t < v.tags.length; t++) { if (v.tags[t].toLowerCase().includes(self.filter.toLowerCase())) { return true } }
            });
        }
    },

    mounted() { this.loadStorage() }
});