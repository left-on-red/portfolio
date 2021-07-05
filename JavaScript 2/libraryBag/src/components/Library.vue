<template>
  <div class="card-columns">
   <LibraryItem v-for="(thing, i) in library" :item="thing" :key="i" @bag="bag" @unbag="unbag"></LibraryItem>
  </div>
</template>

<script>
import LibraryItem from "./LibraryItem.vue";

export default {
  name: "Library",
  components: {
    LibraryItem,
  },
  props: {
    library: {
      type: Object,
      required: true
    }
  },

  methods: {
    bag(item) { this.$emit('bag', item) },
    unbag(item) { this.$emit('unbag', item) }
  }
}
</script>

<style scoped>

</style>
