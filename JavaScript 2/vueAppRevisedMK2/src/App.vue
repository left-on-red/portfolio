<template>
    <v-app>
        <!--
            one thing that I'm not a fan of is that if I pass a value to a component, rather than an actual object, Vue doesn't let me modify it.
            so if I don't want to go through annoying event callbacks, I need to pass this "dialogs" object have each dialog box modify a specific prop to close it
        -->
        <add-game :dialogs="dialogs" :games="games"></add-game>
        <delete-game :dialogs="dialogs" :games="games"></delete-game>
        <bar :filter="filter"></bar>
        <v-btn color="accent" dark fab fixed bottom right @click="dialogs.adding = true">
            <v-icon>mdi-plus</v-icon>
        </v-btn>

        <v-main>
            <v-row class="mt-5">
                <!--
                    now I have a game component. in my first runthrough of this project, I wasn't really aware of how property binding worked with Vue.
                    but now it's really easy to bind information because I can just bind the whole object and render each game component based on that.
                    the only thing that I'm not really sure about now is how I could call either of the dialog boxes from the scope of the component.
                    I was thinking about generating dialog boxes inside each game component, so each game would have access to its own dialog boxes,
                    but I wasn't sure if that was good practice. either way, I'm pretty proud of how much I was able to refactor
                -->
                <game v-for="(game, g) in filteredGames" :list="games" :game="game" :dialogs="dialogs" :key="g"></game>
            </v-row>
        </v-main>
    </v-app>
</template>

<script>
//import HelloWorld from './components/HelloWorld';

import BarComponent from './components/Bar.vue';
import GameComponent from './components/Game.vue';
import AddGameComponent from './components/AddGame.vue';
import DeleteGameComponent from './components/DeleteGame.vue';

import Game from './Game.js';
import Filter from './Filter.js';

export default {
    name: 'App',

    components: {
        Game: GameComponent,
        Bar: BarComponent,
        AddGame: AddGameComponent,
        DeleteGame: DeleteGameComponent
    },

    data: () => ({
        dialogs: {
            adding: false,
            deleting: false
        },

        games: [],
        filter: new Filter()
    }),
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
            let arr = [];
            if (json) { arr = JSON.parse(json) }
            else {
                // some default Game data will be populated (for testing purposes)
                arr = [
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
            }

            for (let a = 0; a < arr.length; a++) { this.games.push(new Game(arr[a].name, arr[a].publisher, arr[a].description, arr[a].tags, arr[a].favorited)) }
            this.updateStorage();
        },

        updateStorage() {
            let arr = [];
            for (let g = 0; g < this.games.length; g++) { arr.push(this.games[g].obj()) }
            localStorage.setItem('games', JSON.stringify(arr));
        },
    },

    computed: {
        filteredGames() {
            let self = this;
            return self.games.filter(function(v) {
                // early return of false if the filter is favorited and the current game is not
                if (self.filter.favorites && !v.favorited) { return false }
                
                if (v.name.toLowerCase().includes(self.filter.text.toLowerCase())) { return true }
                if (v.publisher.toLowerCase().includes(self.filter.text.toLowerCase())) { return true }
                if (v.description.toLowerCase().includes(self.filter.text.toLowerCase())) { return true }
                for (let t = 0; t < v.tags.length; t++) { if (v.tags[t].toLowerCase().includes(self.filter.text.toLowerCase())) { return true } }
            });
        }
    },

    watch: {
        // watches the games array and calls updateStorage() whenever there's changes
        games: {
            deep: true,
            handler() { this.updateStorage() }
        }
    },

    // loads any localStorage data upon page load
    mounted() { this.loadStorage() }
}
</script>

<style>
.v-input__slot { margin: 15px 0px 0px 0px }
</style>