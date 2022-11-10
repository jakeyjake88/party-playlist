package com.nashss.se.partyplaylist.converters;

import com.nashss.se.partyplaylist.dynamodb.models.Playlist;
import com.nashss.se.partyplaylist.dynamodb.models.User;
import com.nashss.se.partyplaylist.models.PlaylistModel;
import com.nashss.se.partyplaylist.models.UserModel;

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
}
