import PartyPlaylistClient from '../api/partyPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create guest page of the website.
 */
class CreateGuest extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the PartyPlaylistClient.
     */
    mount() {
        document.getElementById('addGuestButton').addEventListener('click', this.submit);
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
        console.log(guest);
        this.dataStore.set('user', guest);
        document.getElementById('addGuestButton').innerText = 'Add Guest';
        var guestList = document.getElementById('guestList');
        guestList.innerHTML += "<li>" + guest.firstName + " " + guest.lastName + " " + "</li>";
    }
 }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createGuest = new CreateGuest();
    createGuest.mount();
};

window.addEventListener('DOMContentLoaded', main);