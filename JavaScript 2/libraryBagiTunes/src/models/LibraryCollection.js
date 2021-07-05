//import {LibraryItem} from "./LibraryItems.js";
import $ from 'jquery';

function msToTime(s) {
    let ms = s % 1000;
    s = (s - ms) / 1000;
    let secs = s % 60;
    s = (s - secs) / 60;
    let mins = s % 60;
    let hrs = (s - mins) / 60;
    return `${hrs.toString().padStart(2, '0')}:${mins.toString().padStart(2, '0')}`;
}

function request(url) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: url,
            dataType: 'jsonp',
            success: resolve,
            error: reject
        })
    });
}

export default class LibraryCollection {
    constructor() {
        this.items = [];
    }

    populate(results, bag) {
        this.items.splice(0, this.items.length);
        for (let r = 0; r < results.length; r++) {
            let type;
            if (results[r].kind) { type = results[r].kind == 'feature-movie' ? 'Movie' : 'Book' }
            else if (results[r].collectionType) { type = 'Album' }
            else if (results[r].wrapperType) { type = 'Book' }
            
            let obj = {
                type: type
            }

            if (type == 'Book') {
                obj.name = results[r].trackName;
                obj.author = results[r].artistName;
                if (!results[r].genres) { obj.genre = results[r].primaryGenreName }
                else { obj.genre = results[r].genres.join(', ') }
                obj.price = results[r].price;
                obj.image = results[r].artworkUrl100;
                obj.url = results[r].trackViewUrl;
                obj.id = results[r].trackId;
            }

            else if (type == 'Album') {
                obj.name = results[r].collectionName;
                obj.artist = results[r].artistName;
                obj.tracks = results[r].trackCount;
                obj.price = results[r].collectionPrice;
                obj.image = results[r].artworkUrl100;
                obj.url = results[r].collectionViewUrl;
                obj.id = results[r].collectionId;
            }

            else if (type == 'Movie') {
                obj.name = results[r].trackName;
                obj.director = results[r].artistName;
                obj.runtime = msToTime(results[r].trackTimeMillis);
                obj.price = results[r].collectionPrice;
                obj.image = results[r].artworkUrl100;
                obj.url = results[r].collectionViewUrl;
                obj.id = results[r].collectionId;
            }

            obj.isBagged = () => bag.items.filter(item => item.id == obj.id).length > 0;

            this.items.push(obj);
        }
    }

    async query(text, bag) {
        try { this.populate((await request(`https://itunes.apple.com/search?term=${encodeURI(text)}&limit=20&entity=movie,album,audiobook,ebook`)).results, bag) }
        catch(e) { console.error(e) }
    }
}
