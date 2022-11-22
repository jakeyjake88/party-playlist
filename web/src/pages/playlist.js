import PartyPlaylistClient from '../api/partyPlaylistClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the playlist page of the website.
 */
class Playlist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'addSong'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('add-song').addEventListener('click', this.addSong);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new PartyPlaylistClient();
    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the PartyPlaylist to add a song to the
     * playlist.
     */
    async addSong() {
        document.getElementById('add-song').innerText = 'Adding...';
        const artistName = document.getElementById('song-artist').value;
        const artistTitle = document.getElementById('song-title').value;
        const playlistId = '01';

        const playlist = await this.client.addSongToPlaylist(artistName, artistTitle, playlistId);
        this.dataStore.set('songs', playlist);

        document.getElementById('add-song').innerText = 'Add Song';
        document.getElementById("add-song-form").reset();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const playlist = new Playlist();
    playlist.mount();
};

window.addEventListener('DOMContentLoaded', main);







//import PartyPlaylistClient from '../api/partyPlaylistClient';
//import Header from '../components/header';
//import BindingClass from "../util/bindingClass";
//import DataStore from "../util/DataStore";
//
///**
// * Logic needed for the view playlist page of the website.
// */
//class Playlist extends BindingClass {
//    constructor() {
//        super();
//        this.bindClassMethods(['clientLoaded', 'mount', 'addPlaylistToPage', 'addSongsToPage', 'addSong'], this);
//        this.dataStore = new DataStore();
//        this.dataStore.addChangeListener(this.addPlaylistToPage);
//        this.dataStore.addChangeListener(this.addSongsToPage);
//        this.header = new Header(this.dataStore);
//        console.log("playlist constructor");
//    }
//
//    /**
//     * Once the client is loaded, get the playlist metadata and song list.
//     */
//    async clientLoaded() {
//        const urlParams = new URLSearchParams(window.location.search);
////        const playlistId = urlParams.get('playlistId');
//
//        document.getElementById('playlist-loading-message').innerText = "Loading Playlist ...";
//        const playlist = await this.client.getPlaylist('01');
//        this.dataStore.set('playlist', playlist);
//        document.getElementById('songs').innerText = "(loading songs...)";
//        const songsList = await this.client.getPlaylistSongs('01');
//        this.dataStore.set('songs', songsList);
//    }
//
//    /**
//     * Add the header to the page and load the PartyPlaylistClient.
//     */
//    mount() {
//        document.getElementById('add-song').addEventListener('click', this.addSong);
//        this.header.addHeaderToPage();
//        this.header.loadData();
//        this.client = new PartyPlaylistClient();
//        this.clientLoaded();
//    }
//
//    /**
//     * When the playlist is updated in the datastore, update the playlist metadata on the page.
//     */
//    addPlaylistToPage() {
//        const playlist = this.dataStore.get('playlist');
//        if (playlist == null) {
//            return;
//        }
//
//        document.getElementById('playlist-display').innerText = playlist.display;
//
//        let tagHtml = '';
//        let tag;
//        for (tag of playlist.tags) {
//            tagHtml += '<div class="tag">' + tag + '</div>';
//        }
//        document.getElementById('playlist-display').innerHTML = tagHtml;
//    }
//
//    /**
//     * When the songs are updated in the datastore, update the list of songs on the page.
//     */
//    addSongsToPage() {
//        const songs = this.dataStore.get('songs')
//
//        if (songs == null) {
//            return;
//        }
//
//        let songHtml = '';
//        let song;
//        for (song of songs) {
//            songHtml += `
//                <li class="song">
//                    <span class="title">${song.title}</span>
//                    <span class="artist">${song.artist}</span>
//                </li>
//            `;
//        }
//        document.getElementById('songs').innerHTML = songHtml;
//    }
//
//    /**
//     * Method to run when the add song playlist submit button is pressed. Call the PartyPlaylistService to add a song to the
//     * playlist.
//     */
//    async addSong() {
////        const playlist = this.dataStore.get('playlist');
////        if (playlist == null) {
////            return;
////        }
//
//        document.getElementById('add-song').innerText = 'Adding...';
//        const artistName = document.getElementById('song-artist').value;
//        const songTitle = document.getElementById('song-title').value;
//
//        const playlistId = '01';
//
//        const songList = await this.client.addSongToPlaylist(playlistId, artistName, songTitle);
//        this.dataStore.set('songs', songList);
//
//        document.getElementById('add-song').innerText = 'Add Song';
//        document.getElementById("add-song-form").reset();
//    }
//}
//
///**
// * Main method to run when the page contents have loaded.
// */
//const main = async () => {
//    const viewPlaylist = new ViewPlaylist();
//    viewPlaylist.mount();
//};
//
//window.addEventListener('DOMContentLoaded', main);
