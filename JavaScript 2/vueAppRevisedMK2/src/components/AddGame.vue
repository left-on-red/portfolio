<template>
    <v-dialog v-model="dialogs.adding" activator="dialog.adding" max-width="600px" max-height="1200px" @click:outside="cancel()" @input="populate" @keydown="handleEscape">
        <v-card>
            <v-card-title>
                <span v-if="!games.editing" class="headline">New Game</span>
                <span v-else class="headline">Edit Game</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-text-field v-model="name" color="accent" label="name"></v-text-field>
                    <v-text-field v-model="publisher" color="accent" label="publisher"></v-text-field>
                    <v-textarea v-model="description" color="accent" label="description"></v-textarea>
                    <v-text-field v-model="tagText" label="add tag (press enter)" color="accent" @keydown.enter="addTag"></v-text-field>
                    <v-chip v-for="(tag, i) in tags" :key="i" class="ma-1" pill color="accent" close @click:close="removeTag(i)">
                        {{tag}}
                    </v-chip>
                </v-container>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="danger" dark @click="cancel()">Cancel</v-btn>
                <v-btn v-if="!games.editing" color="primary" dark @click="add()">Add</v-btn>
                <v-btn v-else color="primary" dark @click="add()">Edit</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import Dialog from './Dialog.vue';
import Game from './../Game.js';

export default {
    name: 'AddGame',
    extends: Dialog,
    data() {
        return {
            name: '',
            publisher: '',
            description: '',
            tags: [],
            tagText: '',
            type: 'adding'
        }
    },

    methods: {
        populate() {
            if (this.games.editing) {
                // this is done to get rid of the object reference (only necessary for the array, but I'm doing it for everything)
                // so the game that's being edited doesn't actually mutate until the changes are appended by the user (by way of the submit button)
                this.name = `${this.games.editing.name}`;
                this.publisher = `${this.games.editing.publisher}`;
                this.description = `${this.games.editing.description}`;
                this.tags = [...this.games.editing.tags];
            }
        },

        removeTag(i) { this.tags.splice(i, 1) },

        addTag(event) {
            let text = event.target.value;
            if (text != '' && !this.tags.includes(text)) { this.tags.push(text) }
            event.target.value = '';
            this.tagText = '';
        },

        add() {
            if (this.games.editing) {
                this.games.editing.edit(this.name, this.publisher, this.description, this.tags);
                delete this.games.editing;
            }

            else { this.games.push(new Game(this.name, this.publisher, this.description, this.tags, false)) }

            this.cancel();
        },

        clear() {
            this.name = '';
            this.publisher = '';
            this.description = '';
            this.tags = [];
            this.tagText = '';
            delete this.games.editing;
        }
    },

    watch: {
        dialogs: {
            deep: true,
            handler(dialogs) { if (dialogs.adding) { this.populate() } }
        }
    }
}
</script>
