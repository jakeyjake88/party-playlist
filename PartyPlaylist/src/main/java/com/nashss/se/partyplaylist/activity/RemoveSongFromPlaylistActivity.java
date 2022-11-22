package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.RemoveSongFromPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.RemoveSongFromPlaylistResult;
import com.nashss.se.partyplaylist.converters.ModelConverter;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.models.PlaylistEntryModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import javax.inject.Inject;


public class RemoveSongFromPlaylistActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDAO;

    /**
     *
     * @param playlistDAO PlaylistDao to access the playlist table.
     */
    @Inject
    public RemoveSongFromPlaylistActivity(PlaylistDao playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    /**
     *
     * @param request request containing information on song to be removed
     * @return the song being removed from the playlist
     */
    public RemoveSongFromPlaylistResult handleRequest(final RemoveSongFromPlaylistRequest request) {
        log.info("Received RemoveSongFromPlaylistRequest {} ", request);

        String songArtist = request.getSongArtist();
        String songTitle = request.getSongTitle();
        String playlistId = request.getPlaylistId();

        Playlist playlist = playlistDAO.getPlaylist(playlistId);
        List<PlaylistEntry> songList = playlist.getSongs();

        songList.removeIf(p -> p.getSongArtist().equals(songArtist) &&
                p.getSongTitle().equals(songTitle));
        playlist.setSongs(songList);
        playlistDAO.savePlaylist(playlist);

        List<PlaylistEntryModel> playlistEntryModels = new ModelConverter().toPlaylistEntriesModel(playlist.getSongs());

        return RemoveSongFromPlaylistResult.builder()
                .withPlaylist(playlistEntryModels)
                .build();
    }
}
