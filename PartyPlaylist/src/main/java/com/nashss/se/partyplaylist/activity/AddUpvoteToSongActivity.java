package com.nashss.se.partyplaylist.activity;


import com.nashss.se.partyplaylist.activity.requests.AddUpvoteToSongRequest;
import com.nashss.se.partyplaylist.activity.results.AddUpvoteToSongResult;
import com.nashss.se.partyplaylist.dynamodb.PlaylistDao;
import com.nashss.se.partyplaylist.dynamodb.SongDAO;
import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.dynamodb.models.Song;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class AddUpvoteToSongActivity {

    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;
    private final SongDAO songDao;

    @Inject
    public AddUpvoteToSongActivity(PlaylistDao playlistDao, SongDAO songDao) {
        this.playlistDao = playlistDao;
        this.songDao = songDao;
    }

    public AddUpvoteToSongResult handleRequest(final AddUpvoteToSongRequest request) {
        log.info("Received upvote request {} ", request);

        String songTitle = request.getSongTitle();
        String songArtist = request.getSongArtist();

        Playlist playlist = playlistDao.getPlaylist(request.getPlaylistId());

        List<PlaylistEntry> songs = playlist.getSongs();
        Song songToUpvote = songDao.getSong(songTitle, songArtist);
        PlaylistEntry playlistEntry = new PlaylistEntry(songToUpvote);

        for (PlaylistEntry e : songs) {
            if (e.equals(playlistEntry)) {
                e.setUpvotes(e.getUpvotes() + 1);
                playlistEntry = e;
            }
        }
        playlist.setSongs(songs);
        playlistDao.savePlaylist(playlist);

        return AddUpvoteToSongResult.builder()
                .withSong(playlistEntry)
                .build();
    }
}
