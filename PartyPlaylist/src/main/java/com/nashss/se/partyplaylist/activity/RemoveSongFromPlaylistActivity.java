package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.RemoveSongFromPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.RemoveSongFromPlaylistResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.Song;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class RemoveSongFromPlaylistActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDAO;
    private final SongDAO songDAO;

    @Inject
    public RemoveSongFromPlaylistActivity(PlaylistDao playlistDAO, SongDAO songDAO) {
        this.playlistDAO = playlistDAO;
        this.songDAO = songDAO;
    }

    public RemoveSongFromPlaylistResult handleRequest(final RemoveSongFromPlaylistRequest removeSongFromPlaylistRequest) {
        log.info("Received RemoveSongFromPlaylistRequest {} ", removeSongFromPlaylistRequest);

        String songTitle = removeSongFromPlaylistRequest.getSongTitle();
        String songArtist = removeSongFromPlaylistRequest.getSongArtist();

        Playlist playlist = playlistDAO.getPlaylist(removeSongFromPlaylistRequest.getPlaylistId());
        Song songToDelete = songDAO.getSong(songTitle, songArtist);
        songDAO.removeSong(songToDelete);
        playlistDAO.savePlaylist(playlist);

        return RemoveSongFromPlaylistResult.builder()
                .withSong(songToDelete)
                .build();
    }
}
