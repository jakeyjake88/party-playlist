package com.nashss.se.partyplaylist.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "songs")
public class Song {
    private String songId;
    private String songTitle;
    private String songArtist;
    private String genre;
    private Integer songLength;

    @DynamoDBHashKey(attributeName = "songId")
    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    @DynamoDBAttribute(attributeName = "songTitle")
    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    @DynamoDBAttribute(attributeName = "songArtist")
    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    @DynamoDBAttribute(attributeName = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @DynamoDBAttribute(attributeName = "songLength")
    public Integer getSongLength() {
        return songLength;
    }

    public void setSongLength(Integer songLength) {
        this.songLength = songLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Song song = (Song) o;
        return Objects.equals(songId, song.songId) && Objects.equals(songTitle, song.songTitle) && Objects.equals(songArtist, song.songArtist) && Objects.equals(genre, song.genre) && Objects.equals(songLength, song.songLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songTitle, songArtist, genre, songLength);
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId='" + songId + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", genre='" + genre + '\'' +
                ", songLength=" + songLength +
                '}';
    }
}