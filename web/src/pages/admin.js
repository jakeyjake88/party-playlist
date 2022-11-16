import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import Header from '../components/header';
import PartyPlaylistClient from '../api/partyPlaylistClient';

class CreatePlaylist extends BindingClass {
    constructor(){
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToAdmin'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToAdmin);
        this.header = new Header(this.dataStore);
    }


    mount() {
        document.getElementById('createPlaylist').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new PartyPlaylistClient();
    }

    async submit() {
        document.getElementById('createPlaylist').innerText = 'Creating Playlist...';
        const playlistName = document.getElementById('newPlaylistName');
        const hostFirstName = document.getElementById('hostFirstName');
        const hostLastName = document.getElementById('hostLastName');
        console.log(playlistName);
        console.log(hostFirstName);
        console.log(hostLastName);

        const playlist = await this.client.createPlaylist(playlistName);
        this.dataStore.set('playlist', playlist);
        document.getElementById('createPlaylist').innerText = 'Created';
    }

    redirectToAdmin() {
        console.log("redirect to admin");
        const playlist = this.dataStore.get('playlist');
        if (playlist != null) {
            window.location.href = `/adminPartyPlaylist.html`;
        }
    } 

}

    const main = async () => {
        const createPlaylist = new CreatePlaylist();
        createPlaylist.mount();
    }
    window.addEventListener('DOMContentLoaded', main);