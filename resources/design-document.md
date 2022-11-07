# Design Document

## Instructions

## _Project Title_ Design

## 1. Problem Statement

Multiple people at the party want to play music, but don't want to fight over the AUX cable/Bluetooth. We want to solve this problem by developing a web application that allows multiple users to add their songs to the party playlist.

## 2. Top Questions to Resolve in Review

1. How will we retrieve search results and how will we store them?
2. How we will keep track of upvotes of songs added to the queue in real time?
3. How can we keep our playlist moving in real time?

## 3. Use Cases

U1.  As a user, I want to add my song to the playlist

U2.  As a user, I want to see all the songs on the playlist so I can upvote the ones I like

U3.  As a user, I want the to hear the song with the most upvotes to play next in the queue

U4.  As a user, I want to be able to invite users to contribute to my playlist

U5.  As a user, I don't want to hear the same song twice at the party

U6.  As a user, I don't want to hear the same artist more than three times

U7.  As a user, I want to see suggestions for songs to add to the playlist

U8.  As a user, I want to see who upvoted a song.

U9.  As a user, I want to see what songs a user has upvoted and added to the playlist.

U10. As an admin, I want to be able to remove songs from the playlist.

U11. As an admin, I want to be able to make other users admins.

## 4. Project Scope

### 4.1. In Scope

Allow the functionality for multiple users to contribute songs to a singular playlist. 
Invitees should easily be able to add songs to the playlist and upvote songs they want to hear first.
Song with the most upvotes should play after song at the top of queue is finished playing.
The creator of the playlist should be able to invite other people to contribute to the playlist.
Invitees should only be able to upvote a song once.
One to three songs should be suggested to the user.
Application should be able to check if a song and artist has already been played.
Songs on playlist should show number of upvotes and who upvoted them.
A user list should display, when clicked, shows which songs a user has upvoted and added to the playlist.
Admins should have their own page and be able to remove songs and make other invitees admins.

### 4.2. Out of Scope

Not worried about actually getting the playlist to play audio. 
Not worried about if the playlist ends before the party.
May want to live update the playlist so songs with the most upvotes are in order.
May want to use Spotify API for library of songs.
May want admin to push up a song to the top of the queue.
May want to add more info to the user statistics, i.e. most added genre/artist.
May want to make the song suggestions personal to each user.

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

*`PlaylistModel`* and *`SongModel`*

## 6.2. _First Endpoint_

_Describe the behavior of the first endpoint you will build into your service API. This should include what data it requires, what data it returns, and how it will handle any known failure cases. You should also include a sequence diagram showing how a user interaction goes from user to website to service to database, and back. This first endpoint can serve as a template for subsequent endpoints. (If there is a significant difference on a subsequent endpoint, review that with your team before building it!)_

_(You should have a separate section for each of the endpoints you are expecting to build...)_
* Accepts `GET` requests to `/playlist/:id`
* Accepts a playlist ID and returns the corresponding PlaylistModel.
    * If the given playlist ID is not found, will throw a
      `PlaylistNotFoundException`

## 6.3 _Second Endpoint_

_(repeat, but you can use shorthand here, indicating what is different, likely primarily the data in/out and error conditions. If the sequence diagram is nearly identical, you can say in a few words how it is the same/different from the first endpoint)_

* Accepts `POST` requests to `/playlist/song`
* Accepts data to add a new song with a provided artist, provided title, and a given user
  ID. Returns the new playlist.

## 6.4 _Third Endpoint_

* Accepts `PUT` requests to `/song:id`
* Accepts data to add an upvote to a song on the playlist. Returns the updated song.

## 6.5 _Fourth Endpoint_

* Accepts `GET` requests to `/song:id`
* Accepts a song ID and returns the corresponding SongModel.
  * If the given song ID is not found, will throw a 'SongNotFoundException'

## 6.6 _Fifth Endpoint_

* Accepts `GET` requests to `/user:name`
* Accepts a first name and last name and returns the corresponding user.
    * If the given name is not found, will throw a 'UserNotFoundException'

## 6.7 _Sixth Endpoint_

* Accepts `POST` requests to `/user:name`
* Creates a user and returns the corresponding user.

## 6.8 _Seventh Endpoint_

* Accepts `PUT` requests to `/user:name`
* Updates a user to have admin status.

## 6.9 _Eighth Endpoint_

* Accepts `DELETE` requests to `/playlist:song`
* Removes a song from the playlist.


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
  - songLength : Number
  - upvoteCount : Number


# 8. Pages

https://wireframe.cc/pro/pp/868e26f3c598098