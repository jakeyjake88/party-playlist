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
        const playlistName = document.getElementById('newPlaylistName').value;
        const hostFirstName = document.getElementById('hostFirstName').value;
        const hostLastName = document.getElementById('hostLastName').value;

        const playlist = await this.client.createPlaylist(playlistName);
        this.dataStore.set('playlist', playlist);
        document.getElementById('createPlaylist').innerText = 'Created';
    }

    redirectToAdmin() {
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