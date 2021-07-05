export default class Filter {
    constructor() {
        this.text = '';
        this.favorites = false;
    }

    setFilter(text) { this.text = text }
    setFavorites(bool) { this.favorites = bool }
}