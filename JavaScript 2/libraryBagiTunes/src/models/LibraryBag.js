export default class LibraryBag {
    constructor(){
        this.items = [];
    }

    add(item) { this.items.push(item); item.bagged = true; }
    remove(item) {
        let index = -1;
        let id = item.id;
        for (let i = 0; i < this.items.length; i++) { if (this.items[i].id == id) { index = i; break; } }
        if (index >= 0) { this.items.splice(index, 1); item.bagged = false }
    }

    checkOut() {
        this.items.splice(0, this.items.length);
    }
}