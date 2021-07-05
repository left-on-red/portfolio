import {LibraryItem} from "./LibraryItems.js";

export default function LibraryCollection(){
    // inherit functionality of arrays (ES6+)
    this.__proto__ = [];

    this.addItem = function(item){
        // this.push works because "this" is also an array
        this.push(new LibraryItem(
                    item,
                    // option 1
                    //(item) => this.removeItem(item)
                    // option 2
                    ((collection) => function(){
                         collection.removeItem(this) // "this" is the LibraryItem
                    })(this) // "this" is the LibraryCollection
                    // option 3
                    //  (function(collection){
                    //      return function(){
                    //          collection.removeItem(this)
                    //      }
                    //  })(this)
            ));

        // return this so we can chain methods
        return this;
    }

    this.removeItem = function(item){
        //console.log(item, this);
        this.splice(this.indexOf(item), 1);
        return this;
    }
}
