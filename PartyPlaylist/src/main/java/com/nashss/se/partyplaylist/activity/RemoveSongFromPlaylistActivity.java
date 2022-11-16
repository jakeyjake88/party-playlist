package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.RemoveSongFromPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.RemoveSongFromPlaylistResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDAO;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.Song;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class RemoveSongFromPlaylistActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDAO playlistDAO;
    private final SongDAO songDAO;

    @Inject
    public RemoveSongFromPlaylistActivity(PlaylistDAO playlistDAO, SongDAO songDAO) {
        this.playlistDAO = playlistDAO;
        this.songDAO = songDAO;
    }

    public RemoveSongFromPlaylistResult handleRequest(final RemoveSongFromPlaylistRequest removeSongFromPlaylistRequest) {
        log.info("Received RemoveSongFromPlaylistRequest {} ", removeSongFromPlaylistRequest);

        String songTitle = removeSongFromPlaylistRequest.getSongTitle();
        String songArtist = removeSongFromPlaylistRequest.getSongArtist();

        Playlist playlist = playlistDAO.getPlaylist(addSongToPlaylistRequest.getPlaylistId());
        Song songToDelete = songDAO.getSong(songTitle, songArtist);
        songDAO.removeSong(songToDelete);
        playlistDAO.savePlaylist();

        return RemoveSongFromPlaylistResult.builder()
                .withSong(songToDelete)
                .build();
    }
}
