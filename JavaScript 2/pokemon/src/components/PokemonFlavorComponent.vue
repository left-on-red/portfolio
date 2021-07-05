<template>
    <div class="container">
        <p class="label">Pokedex Entry</p>
        <p class="entry">{{text}}</p>
    </div>
</template>

<script>

export default {
    name: 'PokemonFlavorComponent',

    props: {
        text: {
            type: String,
            required: true
        }
    }
}
</script>

<style scoped lang="scss">
    div.container {
        overflow: hidden;
        background-color: #E8EAF6;
        margin: 0;
        padding: 0;
        border-radius: 10px;

        p.label {
            font-weight: bold;
            font-size: 14px;
            text-transform: uppercase;
            text-align: center;
            margin: 0;
            padding: 5px;
            color: #E8EAF6;
            background-color: #3F51B5;
        }

        p.entry {
            margin: 10px;
            font-size: 12px;
        }
    }

</style>