<template>
  <div>
  <ul class="list-group">
    <li class="list-group-item" v-for="(item, i) in bag.items" :key="i">{{item.title}}</li>
  </ul>
  <button v-if="bag.items.length > 0" class="btn btn-success" @click="checkout">Checkout</button>
  </div>
</template>

<script>
export default {
  name: "Bag",
  components: {
    //LibraryItem,
  },
  props: {
    bag: {
      type: Object,
      required: true
    }
  },
  methods: {
      checkout() {
        this.bag.checkOut();
      }
  }
}
</script>

<style scoped>

</style>
