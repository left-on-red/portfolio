export default class Game {
    /**
     * 
     * @param {string} name the name of the Game
     * @param {string} publisher the publisher of the Game
     * @param {string} description the description of the Game
     * @param {string[]} tags the tags array of the Game
     * @param {boolean} favorited whether the Game is favorited or not (should be false by default)
     */
    constructor(name, publisher, description, tags, favorited) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.tags = tags;
        this.favorited = favorited;
    }

    /**
     * toggles the boolean value `favorited` between true and false
     */
    toggleFavorite() { this.favorited = !this.favorited }

    /**
     * edits the Game with specified values and updates the Game collection
     * @param {string} name the new name of the Game
     * @param {string} publisher the new publisher of the Game
     * @param {string} description the new description of the Game
     * @param {string[]} tags the new tags array of the Game
     */
    edit(name, publisher, description, tags) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.tags = tags;
    }

    /**
     * 
     * @returns a json friendly object (only prop values)
     */
    obj() {
        return {
            name: this.name,
            publisher: this.publisher,
            description: this.description,
            tags: this.tags,
            favorited: this.favorited
        }
    }
}