<template>
  <div id="app" >
    <nav class="navbar bg-dark text-light">
      <span class="navbar-brand"><i class="fas fa-shopping-bag mx-2"></i> CHECK IT OUT!</span>
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
import {Book, Movie, Album} from "./models/LibraryItems.js";
import Library from "./components/Library.vue";
import Bag from "./components/Bag.vue";

export default {
  name: 'App',
  data(){
    return {
      library: new LibraryCollection()
          .addItem(new Book('Learning Vue', 120))
          .addItem(new Book('Interaction Design', 200))
          .addItem(new Movie('Princess Bride', 118))
          .addItem(new Movie('Strange Brew', 92))
          .addItem(new Album('Evolve', 'Imagine Dragons', 12))
          .addItem(new Album('The Search', 'NF', 19)),
      bag: new LibraryBag()
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
    }
  },
  mounted(){
    
    // this.library.removeItem(this.library[3]);
  }
}
</script>

<style>
/* styles that are not scoped are like putting them in a style sheet */
#app {

}

</style>
