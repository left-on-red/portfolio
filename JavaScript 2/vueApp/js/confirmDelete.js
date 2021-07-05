Vue.component('confirm-delete', {
    props: {
        dialog: Boolean
    },

    data: function() {
        return {
            title: ''
        }
    },

    template: `
        <v-dialog v-model="dialog" activator="dialog" max-width="300px" max-height="600px" @click:outside="cancel()" @keydown="handleEscape">
            <v-card>
                <v-card-text>
                    <p class="pt-6">are you sure you want to delete <b>{{title}}</b> from your library?</p>
                </v-card-text>
                <v-card-actions>
                    <v-btn class="p-3" color="primary" plain dark @click="cancel()">Nevermind</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn class="p-3" color="danger" dark @click="sendDelete()">Delete</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    `,

    methods: {
        cancel() { this.$emit('close-dialog') },

        sendDelete() { this.cancel(); this.$emit('confirm-delete') },

        handleEscape(event) { if (event.key == 'Escape') { this.cancel() } }
    }
});