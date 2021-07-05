<template>
    <v-dialog v-model="dialogs.deleting" activator="dialogs.deleting" max-width="300px" max-height="600px" @click:outside="cancel()" @keydown="handleEscape">
        <v-card>
            <v-card-text>
                <p class="pt-6">are you sure you want to delete <b>{{games.deleting ? games.deleting.name : ""}}</b> from your library?</p>
            </v-card-text>
            <v-card-actions>
                <v-btn class="p-3" color="primary" plain dark @click="cancel()">Nevermind</v-btn>
                <v-spacer></v-spacer>
                <v-btn class="p-3" color="danger" dark @click="remove()">Delete</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import Dialog from './Dialog.vue';

export default {
    name: 'DeleteGame',
    extends: Dialog,
    data() { return { type: 'deleting' } },

    methods: {
        remove() {
            this.games.splice(this.games.indexOf(this.games.deleting), 1);
            delete this.games.deleting;
            this.cancel();
        }
    }
}
</script>
