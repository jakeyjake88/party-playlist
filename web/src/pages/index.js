import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import Header from '../components/header';
import PartyPlaylistClient from '../api/partyPlaylistClient';

class CreatePlaylist extends BindingClass {
    constructor(){
        super();
        this.bindClassMethods(['mount', 'createPlaylist', 'redirectToAdmin', 'hostLogin', 'guestLogin', 'redirectToPlaylist'], this);
        this.dataStore = new DataStore();
        // this.dataStore.addChangeListener(this.redirectToAdmin);
        this.header = new Header(this.dataStore);
    }


    mount() {
        document.getElementById('createPlaylist').addEventListener('click', this.createPlaylist);
        document.getElementById('guestLoginButton').addEventListener('click', this.guestLogin);
        document.getElementById('adminLoginButton').addEventListener('click', this.hostLogin);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new PartyPlaylistClient();
    }

    async createPlaylist() {
        document.getElementById('createPlaylist').innerText = 'Creating Playlist...';
        const playlistName = document.getElementById('newPlaylistName').value;
        const hostFirstName = document.getElementById('hostFirstName').value;
        const hostLastName = document.getElementById('hostLastName').value;

        const host = await this.client.createHost(hostFirstName, hostLastName);
        this.dataStore.set('user', host);

        const playlistHost = hostFirstName +'\xa0'+ hostLastName;
        const playlist = await this.client.createPlaylist(playlistName, playlistHost, (error)=> {
            document.getElementById('createPlaylist').innerHTML = "Create Playlist"
            document.getElementById('sameNameError').innerHTML = error.response.data.error_message
        });
        this.dataStore.set('playlist', playlist);
        if (playlist != null) {
            this.redirectToAdmin(playlist.playlistID);
        }
    }

    async hostLogin() {
        document.getElementById('adminLoginButton').innerText = 'Logging In...';
        const playlistName = document.getElementById('adminPlaylistName').value;
        const hostName = document.getElementById('hostName').value;
       
        const playlistId = await this.client.getHost(playlistName, hostName, (error)=> {
            document.getElementById("adminLoginButton").innerHTML = "Login"
            document.getElementById("hostError").innerHTML = error.response.data.error_message
        });

        if (playlistId != null) {
            this.redirectToAdmin(playlistId)
        }
    }

    async guestLogin() {
        document.getElementById('guestLoginButton').innerText = 'Logging in...';
        console.log(document.getElementById('playlistName'));
        const playlistName = document.getElementById('playlistName').value;
        const partyPlaylist = await this.client.getPlaylistByName(playlistName, (error)=> {
            document.getElementById("guestLogin").innerHTML = "Login"
            document.getElementById("songNotFoundError").innerHTML = error.response.data.error_message
        });
        this.dataStore.set('partyPlaylist', partyPlaylist);
        this.redirectToPlaylist();
        document.getElementById('guestLoginButton').innerText = 'Logged in';
    }

    async redirectToAdmin(playlistId) {
        window.location.href = `/admin.html?playlistId=${playlistId}`;
    }

    async redirectToPlaylist() {
        const playlist = this.dataStore.get('partyPlaylist');
        if (playlist != null) {
            window.location.href = `/playlist.html?playlistId=${playlist.playlistID}`;
        }
    }
}

    const main = async () => {
        const createPlaylist = new CreatePlaylist();
        createPlaylist.mount();
    }

    window.addEventListener('DOMContentLoaded', main);