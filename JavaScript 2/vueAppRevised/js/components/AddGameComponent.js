let AddGameComponent = Vue.component('add-game', {
    
    extends: DialogComponent,
    
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

    template: `
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
    `,

    methods: {
        populate() {
            if (this.games.editing) {
                // This was done to remove the references to the individual properties (so they wouldn't change the Game component before the changes have been submitted)
                // Vue obviously sets up a proxy for each property to listen for changes, but I'm honestly not a fan of it.
                // It's cool because it makes it easier to update the state of components because of these dynamic properties,
                // but I liked it better with vanilla JS where only objects were passed by reference and the standard primitives were passed by value.

                // either way, this works and is the best I could come up with. (better than passing a parsed stringified JSON object like how I had it before)
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

        add: function() {
            if (this.games.editing) {
                this.games.editing.edit(this.name, this.publisher, this.description, this.tags);
                delete this.games.editing;
            }

            else { this.games.push(new Game(this.name, this.publisher, this.description, this.tags, false)) }

            this.cancel();
        },

        clear: function() {
            this.name = '';
            this.publisher = '';
            this.description = '';
            this.tags = [];
            this.tagText = '';
        }
    },

    watch: {
        dialogs: {
            deep: true,
            handler(dialogs) { if (dialogs.adding) { this.populate() } }
        }
    }
});