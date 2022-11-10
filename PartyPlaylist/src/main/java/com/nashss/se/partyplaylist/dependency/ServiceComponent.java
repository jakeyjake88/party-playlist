package com.nashss.se.partyplaylist.dependency;

import com.nashss.se.partyplaylist.activity.GetPlaylistActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    GetPlaylistActivity provideGetPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return AddGuestToPartyActivity
     */
    //AddGuestToPartyActivity provideAddGuestToPartyActivity();


}
