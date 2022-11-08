# Design Document

## _Party Starter Playlist_ Design

## 1. Problem Statement

Multiple people at the party want to play music, but don't want to fight over the AUX cable/Bluetooth. We want to solve this problem by developing a web application that allows multiple users to add their songs to the party playlist.

## 2. Top Questions to Resolve in Review

1. How will we retrieve search results and how will we store them?
2. How we will keep track of upvotes of songs added to the queue in real time?
3. How can we keep our playlist moving in real time?

## 3. Use Cases

U1.  As a guest, I want to add my song to the playlist

U2.  As a guest, I want to see all the songs on the playlist so I can upvote the ones I like

U3.  As a guest, I want the to hear the song with the most upvotes to play next in the queue

U4.  As a host, I want to be able to invite users to contribute to my playlist

U5.  As a guest, I don't want to hear the same song twice at the party

U6.  As a guest, I don't want to hear the same artist more than three times

U7.  As a guest, I want to see suggestions for songs to add to the playlist

U8.  As a guest, I want to see who upvoted a song.

U9.  As a guest, I want to see what songs a user has upvoted and added to the playlist.

U10. As a host, I want to be able to remove songs from the playlist.

U11. As a host, I want to be able to make other users admins.

U12. As a host, I don't want a limit on the amounts of songs the playlist can hold.

U13. As a host, I want to be able to start and create the playlist for the party.

U14. As a host, I want the most voted songs to be prioritized over the least voted. If there is a tie,
I want to hear the oldest added song. 

## 4. Project Scope

### 4.1. In Scope

- Playlist is initially created and populated by the host
- Each party has a playlist, one party will not have multiple playlists
- The creator of the playlist should be able to invite other people to contribute to the playlist.
- Because the playlist can hold a large amount of songs, playlist will display/be paginated 19 songs at a time.
- Allow the functionality for multiple users to contribute songs to a singular playlist. 
- Invitees should easily be able to add songs to the playlist and upvote songs they want to hear first.
- Song with the most upvotes should play after song at the top of queue is finished playing.
- Invitees should only be able to upvote a song once.
- Application should be able to check if a song and artist has already been played.
- Songs on playlist should show number of upvotes and who upvoted them.
- Admins should have their own page and be able to remove songs and make other invitees admins.
- One to three songs should be suggested to the user.
- Song suggestions will initially be randomized from the song database
- When clicked, a user page shows which songs a user has upvoted and added to the playlist.

### 4.2. Out of Scope

- Not worried about actually getting the playlist to play audio. 
- Not worried about if the playlist ends before the party.
- May want to live update the playlist so songs with the most upvotes are in order.
- May want to use Spotify API for library of songs.
- May want admin to push up a song to the top of the queue.
- May want to add more info to the user statistics, i.e. most added genre/artist.
- May want to make the song suggestions personal to each user, based off of their additions and upvotes.

# 5. Proposed Architecture Overview

Pages:
- Login page
- Playlist page
- Admin page
- User info page

Tables:
- User Table
- Playlist Table
- Song Table

API:
- Gets the playlist
- Adds songs to the playlist
- Adds upvotes to the playlist
- Create users
- Update user to admins
- Get user
- Remove song from playlist

# 6. API

## 6.1. Public Models

*`PlaylistModel`*
- id : String
- playlistName : String
- songArtist : String
- songTitle : String
- songLength : Number
- hasPlayed : Boolean
- timeAdded: ZonedDateTime

*`SongModel`*
- id : String
- songArtist : String
- songTitle : String
- genre : String
- songLength : Number
- upvoteCount : Number

*`UserModel`*
- id : String
- first name : String
- last name : String
- isAdmin : Boolean
- songsAdded : List<Songs>
- songsUpvoted : List<Songs>

## 6.2. _Get Playlist_
* Accepts `GET` requests to `/playlist/:playlistid`
* Accepts a playlist ID and returns the corresponding PlaylistModel.
    * If the given playlist ID is not found, will throw a
      `PlaylistNotFoundException`

## 6.3 _Add Song to Playlist_

* Accepts `POST` requests to `/playlist/:playlistid`
* Accepts data to add a new song with a provided artist, provided title, and a given user
  ID. Returns the new playlist.

## 6.4 _Add Upvote to Song_

* Accepts `PUT` requests to `playlist/id/song/:songid`
* Accepts data to add an upvote to a song on the playlist. Returns the updated song.

## 6.5 _Get Song_

* Accepts `GET` requests to `/song/:songid`
* Accepts a song ID and returns the corresponding SongModel.
  * If the given song ID is not found, will throw a 'SongNotFoundException'
* Might not be needed

## 6.6 _Get Guest_

* Accepts `GET` requests to `/user/:userId`
* Accepts a first name and last name and returns the corresponding user.
    * If the given name is not found, will throw a 'UserNotFoundException'

## 6.7 _Create Guest_

* Accepts `POST` requests to `/user/`
* Accepts data to add a new user with a provided first name, provided last name, and a given 
user ID. Returns the new user.

## 6.8 _Make Guest a Host_

* Accepts `PUT` requests to `/user/:userId/isAdmin`
* Updates a user to have admin status.

## 6.9 _Remove Song from Playlist_

* Accepts `DELETE` requests to `/playlist/:playlistid/:songid`
* Removes a song from the playlist.

## 6.10 _Create Playlist_

* Accepts `POST` requests to `/playlist`
* Accepts data to add a new playlist with a provided name and a given
  playlist ID. Returns the new playlist.


# 7. Tables

- User Table:
  - id : String
  - first name : String
  - last name : String
  - isAdmin : Boolean
  - songsAdded : List<Songs>
  - songsUpvoted : List<Songs>
- Playlist Table
  - id : String
  - playlistName : String
  - songArtist : String
  - songTitle : String
  - songLength : Number
  - hasPlayed : Boolean
  - timeAdded: ZonedDateTime
- Song Table
  - id : String
  - songArtist : String
  - songTitle : String
  - genre : String
  - songLength : Number
  - upvoteCount : Number


# 8. Pages

https://wireframe.cc/pro/pp/868e26f3c598098