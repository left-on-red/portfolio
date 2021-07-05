<template>
  <div class="book">
    <h3 class="card-title">{{item.title}}</h3>
    <p class="card-text">Pages: {{item.pages}}</p>
  </div>
</template>

<script>
import Media from "./Media.vue";

export default {
  name: "Book",
  extends: Media
}
</script>

<style scoped lang="scss">
  div {
    h3 {
      //color: blue;
    }
  }
  h3 {
    text-transform: uppercase
  }

</style>
