class Game {
    /**
     * the Game collection
     */
    static collection = [];

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
     * appends a new Game to the Game collection. you can alternatively just `Game.collection.push(new Game(...))` for the same effect
     * @param {string} name the name of the Game
     * @param {string} publisher the publisher of the Game
     * @param {string} description the description of the Game
     * @param {string[]} tags the tags array of the Game
     * @param {boolean} favorited whether the Game is favorited or not (should be false by default)
     */
    static add(name, publisher, description, tags, favorited) { Game.collection.push(new Game(name, publisher, description, tags, favorited)) }

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

    /**
     * deletes the Game and updates the Game collection
     */
    delete() { Game.collection.splice(Game.collection.indexOf(this), 1) }

    /**
     * converts the Game collection to a json string
     * @returns a stringified representation of the Game collection
     */
    static json() {
        let arr = [];
        for (let c = 0; c < Game.collection.length; c++) { arr.push(Game.collection[c].obj()) }
        return JSON.stringify(arr);
    }

    /**
     * populates the Game collection with the items inside a json array
     * @param {string} json the json representation of a Game collection
     */
    static populate(json) {
        let arr = JSON.parse(json);
        for (let a = 0; a < arr.length; a++) { Game.collection.push(new Game(arr[a].name, arr[a].publisher, arr[a].description, arr[a].tags, arr[a].favorited)) }
    }
}