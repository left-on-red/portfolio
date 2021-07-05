export default class LibraryBag {
    constructor(){
        this.items = [];
    }

    add(item) { this.items.push(item); item.bagged = true; }
    remove(item) { this.items.splice(this.items.indexOf(item), 1); item.bagged = false; }

    checkOut() {
        for (let i = 0; i < this.items.length; i++) { this.items[i].checkOut() }
        this.items = [];
    }
}