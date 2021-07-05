<template>
    <div>
        <p class="card-text">Artist: {{item.artist}}</p>
        <p class="card-text">Number of Tracks: {{item.tracks}}</p>
    </div>
</template>

<script>
import Media from "./Media.vue";

export default {
  name: "Album",
  extends: Media
}
</script>

<style scoped>

</style>
