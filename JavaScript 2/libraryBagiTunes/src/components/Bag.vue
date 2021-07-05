<template>
  <div>
  <ul class="list-group">
    <li class="list-group-item" v-for="(item, i) in bag.items" :key="i">{{item.name}} <button class="btn btn-danger" @click="bag.remove(item)">Remove</button></li>
  </ul>
  <button v-if="bag.items.length > 0" class="btn btn-success" @click="checkout">Checkout (${{total}})</button>
  </div>
</template>

<script>
export default {
    name: "Bag",
    props: {
        bag: {
            type: Object,
            required: true
        }
    },

    computed: {
        total() {
            let sum = 0;
            for (let i = 0; i < this.bag.items.length; i++) { if (this.bag.items[i].price > 0) { sum += this.bag.items[i].price } }
            return sum.toFixed(2);
        }
    },

    methods: {
        checkout() { this.bag.checkOut() }
    }
}
</script>

<style scoped>

</style>
