package com.nashss.se.partyplaylist.dependency;

import com.nashss.se.partyplaylist.activity.*;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Party Playlist.
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
     * Provides activity.
     * @return AddUpvoteToSongActivity
     */
    AddUpvoteToSongActivity provideAddUpvoteToSongActivity();


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

    /**
     * Provides the relevant activity.
     * @return CreateHostActivity
     */
    CreateHostActivity provideCreateHostActivity();

    /**
     * Provides the relevant activity.
     * @return GetHostActivity
     */
    GetHostActivity provideGetHostActivity();
}
