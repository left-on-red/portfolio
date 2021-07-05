<script>
export default {
    name: 'Dialog',

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

    methods: {
        cancel: function() {
            this.clear();
            this.close();
        },

        close: function() { this.dialogs[this.type] = false },
        clear: function() {},

        handleEscape(event) { if (event.key == 'Escape') { this.cancel() } }
    }
}
</script>