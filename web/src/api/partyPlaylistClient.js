import axios from "axios";
import BindingClass from "../util/bindingClass";


export default class PartyPlaylistClient extends BindingClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'getPlaylist', 'getHost',
        'addSongToPlaylist', 'getSong', 'createPlaylist', 'createGuest', 'removeSongFromPlaylist', 'getGuestList', 'createHost', 'removeSongFromPlaylist', 'addUpvoteToSong'];
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

    async createPlaylist(playlistName, host, errorCallback) {
        try {
            const response = await this.client.post(`playlist`, {
                playlistName: playlistName,
                host: host
            });
            return response.data.playlist;
            } catch (error) {
                // let err = document.getElementById("err");
                // err.innerHTML = "Playlist name already exists! Please try again with a different name.";
                this.handleError(error, errorCallback);
            }
        }


    async addUpvoteToSong(songId, playlistId, songTitle, songArtist, errorCallback) {
                try {
                    const response = await this.client.put(`playlist/${playlistId}/songs/${songId}`, {
                       playlistId: playlistId,
                       songId: songId,
                       songTitle: songTitle,
                       songArtist: songArtist
                    });
                    console.log("Response: ", response);
                    return response.data.Song;
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

   async createHost(firstName, lastName, errorCallback) {
        try {
            const response = await this.client.post(`users`, {
                firstName: firstName,
                lastName: lastName
            });
        console.log("Response: ", response);
        return response.data.host;
        } catch (error) {
            this.handleError(error, errorCallback)
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
            const playlistId = "01";
            const response = await this.client.post(`users/${playlistId}`, {
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
            const response = await this.client.get(`guests/${playlistId}`);
            console.log("Response: ", response);
            return response.data.guestList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

            /**
     * Gets playlist associated with a host.
     *
     * @param playlistName The playlist name.
     * @param hostName The host associated with the playlist.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlistId that has been retrieved.
     */
    async getHost(playlistName, hostName, errorCallback) {
        try {
            const response = await this.client.get(`host/${hostName}/playlists/${playlistName}`);
            console.log("Response: ", response);
            return response.data.playlistId;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    handleError(error, errorCallback) {
        console.error(error);
        if (error.response.data.error_message !== undefined) {
            console.error(error.response.data.error_message)
        }
        if (errorCallback) {
            errorCallback(error);
        }
    }
}
