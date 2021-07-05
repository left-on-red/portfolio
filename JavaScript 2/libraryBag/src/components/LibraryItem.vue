<template>
  <div class="card" :class="{'border-success': item.isAvailable(), 'border-warning': !item.isAvailable()}">
    <component :is="typeOfItem" :item="item" class="card-body"></component>
    <div class="card-footer">
      <button v-if="item.isAvailable() && !item.isBagged()" @click="bag()" class="btn btn-success">Bag</button>
      <button v-if="item.isAvailable() && item.isBagged()" @click="unbag()" class="btn btn-danger">Unbag</button>
      <button v-if="!item.isAvailable()" class="btn btn-outline-warning" disabled>Unavailable</button>
    </div>
  </div>
</template>

<script>
import Book from "./Book.vue";
import Movie from "./Movie.vue";
import Album from "./Album.vue";

export default {
  name: "LibraryItem",
  components: {
    Book,
    Movie,
    Album
  },
  props: {
    item: {
      type: Object,
      required: true,
    }
  },

  methods: {
    bag() { this.$emit('bag', this.item) },
    unbag() { this.$emit('unbag', this.item) }
  },

  computed: {
    typeOfItem() {
      return this.item.constructor.name;
    }
  }
}
</script>

<style scoped>

</style>
