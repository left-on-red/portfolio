// at first I interpretted the idea of inheriting components the wrong way.
// I thought that I could just extend the actual markup of the components,
// but the only thing that you really inherit is the functionality of the parent component.

// so that's what this is.

// (I also found out that I can do this to create an "abstract" component that can only be extended)
let DialogComponent = Vue.extend({
    props: {
        dialogs: {
            type: Object,
            required: true
        },

        games: {
            type: Array,
            required: true
        }
    },

    data() { return { type: 'type' } },

    // helper methods for managing the dialog box
    methods: {
        cancel: function() {
            this.clear();
            this.close();
        },

        close: function() { this.dialogs[this.type] = false },
        clear: function() {},

        handleEscape(event) { if (event.key == 'Escape') { this.cancel() } }
    }
});