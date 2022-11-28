import axios from "axios";
import BindingClass from "../util/bindingClass";


export default class PartyPlaylistClient extends BindingClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'getPlaylist', 
        'addSongToPlaylist', 'getSong', 'createPlaylist', 'createGuest', 'removeSongFromPlaylist', 'getGuestList'];
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
            const response = await this.client.get(`playlist/${id}`);
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
            const response = await this.client.post(`playlist`, {
                playlistName: playlistName
            });
            return response.data.playlist;
            } catch (error) {
                this.handleError(error, errorCallback);
            }
        }
    
    async addSongToPlaylist(songArtist, songTitle, playlistId, errorCallback) {
        try {
            const response = await this.client.post(`playlist/${playlistId}/songs`, {
                songArtist: songArtist,
                songTitle: songTitle,
                playlistId: playlistId
            });
            return response.data.songList;
        } catch (error) {
            let err = document.getElementById("err");
            err.innerHTML = "Song not found! Try again.";
            this.handleError(error, errorCallback);
        }
    }

    async removeSongFromPlaylist(songArtist, songTitle, playlistId, errorCallback) {
        try {
            const response = await this.client.put(`playlist/${playlistId}/songs`, {
                songArtist: songArtist,
                songTitle: songTitle,
                playlistId: playlistId
            });
            return response.data.songList;
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

        /**
         * Gets a guest list.
         *
         * @param playlistId The playlist Id associated with the guest list.
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The guest list that has been retrieved.
         */
        async getGuestList(playlistId, errorCallback) {
            try {
                const response = await this.client.get(`playlist/${playlistId}`);
                console.log("Response: ", response);
                return response.data.guestList;
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
