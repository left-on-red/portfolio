<template>
    <div>
        <p class="card-text">Director: {{item.director}}</p>
        <p class="card-text">Runtime: {{item.runtime}}</p>
    </div>
</template>

<script>
import Media from "./Media.vue";

export default {
  name: "Movie",
  extends: Media
}
</script>

<style scoped>

</style>
