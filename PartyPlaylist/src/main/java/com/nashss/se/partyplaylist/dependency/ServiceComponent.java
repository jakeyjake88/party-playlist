package com.nashss.se.partyplaylist.dependency;
import com.nashss.se.partyplaylist.activity.AddGuestToPartyActivity;
import com.nashss.se.partyplaylist.activity.AddSongToPlaylistActivity;
import com.nashss.se.partyplaylist.activity.CreatePlaylistActivity;
import com.nashss.se.partyplaylist.activity.GetGuestActivity;
import com.nashss.se.partyplaylist.activity.GetPlaylistActivity;
import com.nashss.se.partyplaylist.activity.RemoveSongFromPlaylistActivity;

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

}
