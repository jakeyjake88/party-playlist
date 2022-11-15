package com.nashss.se.partyplaylist.converters;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.PlaylistEntry;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.models.PlaylistEntryModel;
import com.nashss.se.partyplaylist.models.PlaylistModel;
import com.nashss.se.partyplaylist.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        return PlaylistModel.builder()
                .withPlaylistId(playlist.getPlaylistId())
                .withPlaylistName(playlist.getPlaylistName())
                .withSongs(playlist.getSongs())
                .build();
    }

    /**
     * Converts a provided {@link User} into a {@link UserModel} representation.
     * @param user the user to convert
     * @return the converted user
     */
    public UserModel toUserModel(User user) {
        return UserModel.builder()
                .withUserId(user.getUserId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withIsHost(user.isHost())
                .withSongsAdded(user.getSongsAdded())
                .withSongsUpvoted(user.getSongsUpvoted())
                .build();
    }

    /**
     * Converts a provided PlaylistEntry into a PlaylistEntryModel representation.
     * @param playlistEntry the playlistEntry to convert to PlaylistEntryModel
     * @return the converted PlaylistEntryModel with fields mapped from playlistEntry
     */
    public PlaylistEntryModel toPlaylistEntryModel(PlaylistEntry playlistEntry) {
        return PlaylistEntryModel.builder()
                .withSongId(playlistEntry.getSongId())
                .withSongTitle(playlistEntry.getSongTitle())
                .withSongArtist(playlistEntry.getSongArtist())
                .withGenre(playlistEntry.getGenre())
                .withSongLength(playlistEntry.getSongLength())
                .withHasPlayed(playlistEntry.getHasPlayed())
                .withUpvotes(playlistEntry.getUpvotes())
                .build();
    }

    /**
     * Converts a list of PlaylistEntry to a list of PlaylistEntryModels.
     * @param playlistEntries The PlaylistEntries to convert to PlaylistEntryModels
     * @return The converted list of PlaylistEntryModels
     */
    public List<PlaylistEntryModel> toPlaylistEntriesModel(List<PlaylistEntry> playlistEntries) {
        List<PlaylistEntryModel> playlistEntryModels = new ArrayList<>();
        for (PlaylistEntry playlistEntry : playlistEntries) {
            PlaylistEntryModel playlistEntryModel = toPlaylistEntryModel(playlistEntry);
            playlistEntryModels.add(playlistEntryModel);
        }
        return playlistEntryModels;
    }
}
