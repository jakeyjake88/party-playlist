package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.AddSongToPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.AddSongToPlaylistResult;
import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.dynamodb.models.Song;
import com.nashss.se.partyplaylist.exceptions.ArtistLimitException;
import com.nashss.se.partyplaylist.exceptions.SongNotFoundException;
import com.nashss.se.partyplaylist.models.PlaylistEntryModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import javax.inject.Inject;


/**
 * Implementation of the AddSongToPlaylistActivity for the PartyPlaylistService's AddSongToPlaylist API.
 *
 * This API allows the customer to add a song to their existing playlist.
 */

public class AddSongToPlaylistActivity {
    private static final Integer SAME_ARTIST_LIMIT = 3;
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;
    private final SongDAO songDAO;

    /**
     * Instantiates a new AddSongToPlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     * @param songDAO AlbumTrackDao to access the album_track table.
     */
    @Inject
    public AddSongToPlaylistActivity(PlaylistDao playlistDao, SongDAO songDAO) {
        this.playlistDao = playlistDao;
        this.songDAO = songDAO;
    }

    /**
     * This method handles the incoming request by adding an additional song
     * to a playlist and persisting the updated playlist.
     * <p>
     * It then returns the updated song list of the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     * <p>
     * If the album track does not exist, this should throw an AlbumTrackNotFoundException.
     *
     * @param addSongToPlaylistRequest request object containing the playlist ID and songId
     *                                 to retrieve the song data
     * @return addSongToPlaylistResult result object containing the playlist's updated list of
     *                                 API defined {@link PlaylistEntry}s
     */
    public AddSongToPlaylistResult handleRequest(final AddSongToPlaylistRequest addSongToPlaylistRequest) {
        log.info("Received AddSongToPlaylistRequest {} ", addSongToPlaylistRequest);

        String songTitle = addSongToPlaylistRequest.getSongTitle();
        String songArtist = addSongToPlaylistRequest.getSongArtist();

        Playlist playlist = playlistDao.getPlaylist(addSongToPlaylistRequest.getPlaylistId());
        Song songToAdd = songDAO.getSong(songTitle, songArtist);

        if (songToAdd == null) {
            throw new SongNotFoundException(
                    String.format("'%s' by '%s' cannot be found", songTitle, songArtist));
        }

        List<PlaylistEntry> playlistSongs = playlist.getSongs();
        PlaylistEntry playlistEntry = new PlaylistEntry(songToAdd);

        Integer artistCount = getArtistCount(playlistSongs, playlistEntry);

        if ( artistCount < SAME_ARTIST_LIMIT) {
            playlistSongs.add(playlistEntry);
        } else {
            throw new ArtistLimitException("This artist has reached the limit for this playlist at this time.");
        }

        playlist.setSongs(playlistSongs);

        playlist = playlistDao.savePlaylist(playlist);

        List<PlaylistEntryModel> playlistEntryModels = new ModelConverter().toPlaylistEntriesModel(playlist.getSongs());
        return AddSongToPlaylistResult.builder()
                .withSongList(playlistEntryModels)
                .build();
    }

    /**
     * This method will count the total number of times an artist appears in a playlist.
     *
     * @param entryList the current playlist list of playlist entries
     * @param entryToAdd the song to add in Playlist Entry form
     * @return Integer of the number of times the artist to add shows up in the current playlist
     */

    private Integer getArtistCount(List<PlaylistEntry> entryList, PlaylistEntry entryToAdd) {
        int count = 0;
        for (PlaylistEntry entry : entryList) {
            if (entry.getSongArtist().equals(entryToAdd.getSongArtist())) {
                count++;
            }
        }
        return count;
    }
}
