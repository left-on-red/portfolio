let GameComponent = Vue.component('game', {
    props: {
        list: {
            type: Array,
            required: true
        },

        game: {
            type: Object,
            required: true
        },

        dialogs: {
            type: Object,
            required: true
        }
    },

    template: `
    <v-col cols="12" sm="4" md="3">
        <v-card class="mx-auto p-0" max-width="400" :key="1">
            <h4 class="d-block primary white--text" style="padding: 8px; text-align: center;">{{game.name}}</h4>
            <v-card-text>
                <div class="mb-3">{{game.publisher}}</div>
                <p class="text--primary">{{game.description}}</p>
                <p>
                    <v-chip v-for="(tag, t) in game.tags" :key="t" class="ma-1" pill color="accent">
                        {{tag}}
                    </v-chip>
                </p>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn icon color="primary" @click="del()"><v-icon>mdi-delete</v-icon></v-btn>
                <v-btn icon color="primary" @click="edit()"><v-icon>mdi-pencil</v-icon></v-btn>
                <v-btn icon @click="game.favorited = !game.favorited">
                    <v-icon v-if="game.favorited" color="amber">mdi-star</v-icon>
                    <v-icon v-else>mdi-star-outline</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-col>
    `,

    methods: {
        del() {
            this.dialogs.deleting = true;
            this.list.deleting = this.game;
        },

        edit() {
            this.dialogs.adding = true;
            this.list.editing = this.game;
        }
    }
});