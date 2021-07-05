<template>
    <div>
        <p class="card-text">Author: {{item.author}}</p>
        <p class="card-text">Genre: {{item.genre}}</p>
    </div>
</template>

<script>
import Media from "./Media.vue";

export default {
  name: "Book",
  extends: Media
}
</script>
