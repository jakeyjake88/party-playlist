import PartyPlaylistClient from '../api/partyPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create guest page of the website.
 */
class Admin extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'addSong', 'removeSong'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the PartyPlaylistClient.
     */
    mount() {
        document.getElementById('addGuestButton').addEventListener('click', this.submit);
        document.getElementById('add-song-admin').addEventListener('click', this.addSong);
        document.getElementById('remove-song-admin').addEventListener('click', this.removeSong);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new PartyPlaylistClient();
    }

    /**
     * Method to run when the create guest submit button is pressed. Call the PartyPlaylistService to create the
     * playlist.
     */
    async submit() {
        document.getElementById('addGuestButton').innerText = 'Adding...';
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;

        const guest = await this.client.createGuest(firstName, lastName);
        this.dataStore.set('user', guest);
        document.getElementById('addGuestButton').innerText = 'Add Guest';
        var guestList = document.getElementById('guestList');
        guestList.innerHTML += "<li>" + guest.firstName + " " + guest.lastName + "</li>";
    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the PartyPlaylist to add a song to the
     * playlist.
     */
    async addSong() {
        document.getElementById('add-song-admin').innerText = 'Adding...';
        const artistName = document.getElementById('song-artist').value;
        const artistTitle = document.getElementById('song-title').value;
        const playlistId = '01';

        const playlist = await this.client.addSongToPlaylist(artistName, artistTitle, playlistId);
        this.dataStore.set('songs', playlist);

        document.getElementById('add-song-admin').innerText = 'Add Song';
        document.getElementById("add-song-form-admin").reset();
    }

    /**
     * Method to run when the remove song playlist submit button is pressed. Call the PartyPlaylist to remove a song to the
     * playlist.
     */
    async removeSong() {
        document.getElementById('remove-song-admin').innerText = 'Removing...';
        const artistName = document.getElementById('song-artist').value;
        const artistTitle = document.getElementById('song-title').value;
        const playlistId = '01';

        const playlist = await this.client.removeSongFromPlaylist(artistName, artistTitle, playlistId);
        this.dataStore.set('songs', playlist);

        document.getElementById('remove-song-admin').innerText = 'Remove Song';
        document.getElementById("add-song-form-admin").reset();
    }
 }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const admin = new Admin();
    admin.mount();
};

window.addEventListener('DOMContentLoaded', main);