function LibraryItem(media, removeMethod){
    // list of possible status (enum)
    const STATUSES = {CHECKED_OUT: 'out', AVAILABLE: 'in'};

    // set the default status
    media.status = STATUSES.AVAILABLE;
    media.bagged = false;

    // methods
    media.checkIn = function(){
        this.status = STATUSES.AVAILABLE;
    }

    media.checkOut = function(){
        this.status = STATUSES.CHECKED_OUT;
    }

    media.isAvailable = function(){
        return this.status === STATUSES.AVAILABLE;
    }

    media.isBagged = function(){
        return this.bagged;
    }

    if(removeMethod){
        // option 1
        // media.remove = function(){
        //     removeMethod(this);
        // }
        // option 2 or 3
        media.remove = removeMethod;
    }

    return media;
}

function Book(title, pages){
    this.title = title;
    this.pages = pages;
}


// same as
class Movie{
    constructor(title, runtime) {
        this.title = title;
        this.runtime = runtime;
    }
}

class Album{
    constructor(title, artist, tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }
}

// export for use as JS modules
export {LibraryItem, Book, Movie, Album};
