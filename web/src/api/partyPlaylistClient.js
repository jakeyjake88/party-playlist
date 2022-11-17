import axios from "axios";
import BindingClass from "../util/bindingClass";


export default class PartyPlaylistClient extends BindingClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'getPlaylist', 
        'addSongToPlaylist', 'getSong', 'createPlaylist', 'createGuest'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;

        axios.defaults.baseURL = INVOKE_URL;
        this.client = axios;
        this.clientLoaded(this.client);
    }

    clientLoaded(client) {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady();
        }
    }

    async getIdentity(errorCallback) {
        try {
            const data = {'username': 'JakePrice'};
            return data;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async getPlaylist(id, errorCallback) {
        try {
            const response = await this.client.get(`playlists/${id}`);
            return response.data.playlist;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async getSong(id, errorCallback) {
        try {
            const response = await this.client.get(`song/${id}`);
            return response.data.Song;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async createPlaylist(playlistName, errorCallback) {
        try {
            const response = await this.client.post(`playlists`, {
                playlistName: playlistName
            });
            return response.data.playlist;
            } catch (error) {
                this.handleError(error, errorCallback);
            }
        }
    
    async addSongToPlaylist(artist, title, id, songId, errorCallback) {
        try {
            const response = await this.client.post(`playlist/id/song/${songId}`, {
                artist: artist,
                title: title,
                id: id
            });
            return response.data.Song;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * Create a new guest.
     * @param firstName The first name of the guest to create.
     * @param lastName The last name of the guest to create.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The guest that has been created.
     */
    async createGuest(firstName, lastName, errorCallback) {
        try {
            const response = await this.client.post(`users`, {
                firstName: firstName,
                lastName: lastName
            });
            console.log("Response: ", response);
            return response.data.guest;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    handleError(error, errorCallback) {
        console.error(error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message)
        }
        if (errorCallback) {
            errorCallback(error);
        }
    }
    
    
}
