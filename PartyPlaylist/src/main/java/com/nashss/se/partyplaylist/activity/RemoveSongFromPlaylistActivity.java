package com.nashss.se.partyplaylist.activity;

import com.nashss.se.partyplaylist.activity.requests.RemoveSongFromPlaylistRequest;
import com.nashss.se.partyplaylist.activity.results.RemoveSongFromPlaylistResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RemoveSongFromPlaylistActivity {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDAO;
    private final SongDAO songDAO;

    /**
     *
     * @param playlistDAO PlaylistDao to access the playlist table.
     * @param songDAO AlbumTrackDao to access the album_track table.
     */
    @Inject
    public RemoveSongFromPlaylistActivity(PlaylistDao playlistDAO, SongDAO songDAO) {
        this.playlistDAO = playlistDAO;
        this.songDAO = songDAO;
    }

    /**
     *
     * @param request request containing information on song to be removed
     * @return the song being removed from the playlist
     */
    public RemoveSongFromPlaylistResult handleRequest(final RemoveSongFromPlaylistRequest request) {
        log.info("Received RemoveSongFromPlaylistRequest {} ", request);

        String songId = request.getSongId();

        Playlist playlist = playlistDAO.getPlaylist(request.getPlaylistId());
        List<PlaylistEntry> songList = new ArrayList<>(playlist.getSongs());

        songList.removeIf(p -> p.getSongId().equals(songId));
        playlist.setSongs(songList);
        playlistDAO.savePlaylist(playlist);

        return RemoveSongFromPlaylistResult.builder()
                .withSong(songDAO.getSong(request.getSongTitle(),
                        request.getSongArtist()))
                .build();
    }
}
