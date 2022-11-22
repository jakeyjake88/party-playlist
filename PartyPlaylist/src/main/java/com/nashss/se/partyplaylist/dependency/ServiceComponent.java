package com.nashss.se.partyplaylist.dependency;
import com.nashss.se.partyplaylist.activity.*;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */

@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();


    /**
     * Removes song from playlist.
     * @return RemoveSongFromPlaylistActivity
     */
    RemoveSongFromPlaylistActivity provideRemoveSongFromPlaylistActivity();


    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */

    CreatePlaylistActivity provideCreatePlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    GetPlaylistActivity provideGetPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return AddGuestToPartyActivity
     */
    AddGuestToPartyActivity provideAddGuestToPartyActivity();

    /**
     * Provides the relevant activity.
     * @return AddGuestToPartyActivity
     */
    GetGuestActivity provideGetGuestActivity();

    /**
     * Provides the relevant activity.
     * @return GetGuestListActivity
     */
    GetGuestListActivity provideGetGuestListActivity();
}
