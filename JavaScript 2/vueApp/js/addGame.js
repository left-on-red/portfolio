Vue.component('add-game', {
    props: {
        dialog: Boolean
    },

    data: function() {
        return {
            tags: [],
            name: '',
            publisher: '',
            description: '',
            tagText: '',
            favorited: false,
            editing: -1
        }
    },

    template: `
        <v-dialog v-model="dialog" activator="dialog" max-width="600px" max-height="1200px" @click:outside="cancel()" @keydown="handleEscape">
            <v-card>
                <v-card-title>
                    <span v-if="editing == -1" class="headline">New Game</span>
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
                    <v-btn v-if="editing == -1" color="primary" dark @click="add()">Add</v-btn>
                    <v-btn v-else color="primary" dark @click="add()">Edit</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    `,

    methods: {
        removeTag(i) { this.tags.splice(i, 1) },

        addTag(event) {
            let text = event.target.value;
            if (text != '' && !this.tags.includes(text)) { this.tags.push(text) }
            event.target.value = '';
            this.tagText = '';
        },

        cancel: function() {
            this.clear();
            this.close();
        },

        add: function() {
            let game = {
                name: this.name,
                publisher: this.publisher,
                description: this.description,
                tags: this.tags,
                favorited: this.favorited
            }

            if (this.editing == -1) { this.$emit('add-game', game) }
            else { this.$emit('edit-game', { game: game, index: this.editing }) }

            this.cancel();
        },

        edit: function(game, index) {
            this.name = game.name;
            this.publisher = game.publisher;
            this.description = game.description;
            this.tags = game.tags;
            this.favorited = game.favorited;
            this.editing = index;
        },

        close: function() { this.$emit('close-dialog') },
        clear: function() {
            this.tags = [];
            this.name = '';
            this.publisher = '';
            this.description = '';
            this.favorited = false;
            this.editing = -1;
        },

        handleEscape(event) { if (event.key == 'Escape') { this.cancel() } }
    }
});