package com.nashss.se.partyplaylist.dynamodb.models;

import com.nashss.se.partyplaylist.models.SongModel;

import java.util.Objects;

public class PlaylistEntry {

    private final String songId;
    private final String songTitle;
    private final String songArtist;
    private final String genre;
    private final Integer songLength;

    private Boolean hasPlayed;

    private Integer upvotes;

    public PlaylistEntry(SongModel songModel) {
        this.songId = songModel.getSongId();
        this.songTitle = songModel.getSongTitle();
        this.songArtist = songModel.getSongArtist();
        this.genre = songModel.getGenre();
        this.songLength = songModel.getSongLength();
    }

    public Boolean getHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(Boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaylistEntry that = (PlaylistEntry) o;
        return Objects.equals(songId, that.songId) && Objects.equals(songTitle, that.songTitle) && Objects.equals(songArtist, that.songArtist) && Objects.equals(genre, that.genre) && Objects.equals(songLength, that.songLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songTitle, songArtist, genre, songLength);
    }

    @Override
    public String toString() {
        return "PlaylistEntry{" +
                "songId='" + songId + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", genre='" + genre + '\'' +
                ", songLength=" + songLength +
                ", hasPlayed=" + hasPlayed +
                ", upvotes=" + upvotes +
                '}';
    }
}
