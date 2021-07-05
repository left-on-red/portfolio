let DeleteGameComponent = Vue.component('delete-game', {
    
    extends: DialogComponent,

    data() { return { type: 'deleting' }
    },

    template: `
        <v-dialog v-model="dialogs.deleting" activator="dialogs.deleting" max-width="300px" max-height="600px" @click:outside="cancel()" @keydown="handleEscape">
            <v-card>
                <v-card-text>
                    <p class="pt-6">are you sure you want to delete <b>{{games.deleting?.name}}</b> from your library?</p>
                </v-card-text>
                <v-card-actions>
                    <v-btn class="p-3" color="primary" plain dark @click="cancel()">Nevermind</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn class="p-3" color="danger" dark @click="remove()">Delete</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    `,

    methods: {
        remove() {
            this.games.deleting.delete();
            delete this.games.deleting;
            this.cancel();
        },

        handleEscape(event) { if (event.key == 'Escape') { this.cancel() } }
    }
});