<template>
    <div class="card border-success">
        <img class="card-img-top" :src="item.image" style="height: 10rem; width: auto; float: right;">
        <h5 class="card-title" style="margin: 10px;"><a :href="item.url" target="_blank">{{item.name}}</a></h5>
        <component :is="typeOfItem" :item="item" class="card-body"></component>
        <div><p class="card-text">Price: {{item.price > 0 ? `$${item.price}` : 'Free'}}</p></div>
        <div class="card-footer">
            <button v-if="!item.isBagged()" @click="bag()" class="btn btn-success">Bag</button>
            <button v-else @click="unbag()" class="btn btn-danger">Unbag</button>
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
    typeOfItem() { return this.item.type }
  }
}

/*
*/
</script>

<style scoped>
    div div p {
        padding: 0px 0px 20px 20px;
    }
</style>
