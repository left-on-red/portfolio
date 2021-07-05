<template>
  <div id="app" >
    <nav class="navbar bg-dark text-light">
      <span class="navbar-brand"><i class="fas fa-shopping-bag mx-2"></i> CHECK IT OUT!</span>
      <form class="form-inline my-2 my-lg-0" @submit.prevent="search">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" v-model="query">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </nav>

    <div class="container-fluid mt-3">
      <Library :library="library" @bag="bagItem" @unbag="unbagItem"></Library>
    </div>

    <div class="container-fluid mt-3">
      <Bag :bag="bag"></Bag>
    </div>
  </div>
</template>

<script>
import LibraryCollection from "./models/LibraryCollection.js";
import LibraryBag from "./models/LibraryBag.js";
import Library from "./components/Library.vue";
import Bag from "./components/Bag.vue";

export default {
  name: 'App',
  data(){
    return {
      library: new LibraryCollection(),
      bag: new LibraryBag(),
      query: ''
    }
  },
  components: {
    Library,
    Bag
  },

  methods: {
    bagItem(item) {
        this.bag.add(item);
    },

    unbagItem(item) {
        this.bag.remove(item);
    },

    search() {
        this.query.trim();
        if (this.query.length > 0) {
            this.library.query(this.query, this.bag);
            this.query = '';
        }
    }
  }
}
</script>

<style>

</style>
