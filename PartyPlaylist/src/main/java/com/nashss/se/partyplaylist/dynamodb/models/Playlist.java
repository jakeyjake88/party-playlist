package com.nashss.se.partyplaylist.dynamodb.models;

import com.nashss.se.partyplaylist.converters.PlaylistEntryListConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Playlist in the playlist table.
 */
@DynamoDBTable(tableName = "playlists")
public class Playlist {

    private String playlistId;
    private String playlistName;
    private String host;
    private List<PlaylistEntry> songs;


    @DynamoDBHashKey(attributeName = "playlistId")
    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    @DynamoDBAttribute(attributeName = "playlistName")
    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    @DynamoDBAttribute(attributeName = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    @DynamoDBTypeConverted(converter = PlaylistEntryListConverter.class)
    @DynamoDBAttribute(attributeName = "songs")
    public List<PlaylistEntry> getSongs() {
        return songs;
    }

    public void setSongs(List<PlaylistEntry> songs) {
        this.songs = songs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Playlist playlist = (Playlist) o;
        return Objects.equals(playlistId, playlist.playlistId) &&
                Objects.equals(playlistName, playlist.playlistName) &&
                Objects.equals(songs, playlist.songs) &&
                Objects.equals(host, playlist.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, playlistName, songs, host);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId='" + playlistId + '\'' +
                ", playlistName='" + playlistName + '\'' +
                ", songs='" + songs + '\'' +
                ", host='" + host +
                '}';
    }
}
