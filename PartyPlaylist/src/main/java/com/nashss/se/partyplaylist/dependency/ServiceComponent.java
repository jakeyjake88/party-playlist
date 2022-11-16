package com.nashss.se.partyplaylist.dependency;

import com.nashss.se.partyplaylist.activity.AddSongToPlaylistActivity;
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

    RemoveSongFromPlaylistActivity provideRemoveSongFromPlaylistActivity();

}
