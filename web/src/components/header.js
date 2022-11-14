import PartyPlaylistClient from "../api/partyPlaylistClient";
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor(dataStore = new DataStore()) {
        super();
        const methodsToBind = ['clientLoaded', 'loadData', 'addHeaderToPage', 'updateUsernameInHeader'];
        this.bindClassMethods(methodsToBind, this);
        this.dataStore = dataStore;
        this.dataStore.set('username', '');
    }

    /**
     * Once the client has loaded successfully, get the identity of the current user.
     * @returns {Promise<void>}
     */
    async clientLoaded() {
        // TODO auth?
        //const identity = await this.client.getIdentity();
        //this.dataStore.set('username', identity.username);
        this.dataStore.set('username', 'Nashville Software School');
    }

    loadData() {
        this.client = new PartyPlaylistClient({ onReady: this.clientLoaded });
    }

    /**
     * Add the header to the page.
     */
    addHeaderToPage() {
        document.getElementById('header').innerHTML = `
        <h1>Party Playlist</h1>
        <p><i>Your Party: Your Playlist</i></p>
        `;
    }

    /**
     * When the datastore has been updated, update the username in the header.
     */
    updateUsernameInHeader() {
        document.getElementById('user').innerText = this.dataStore.get('username');
    }
}
