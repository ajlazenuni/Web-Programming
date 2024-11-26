package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;


public interface SongService {
    List<Song> listSongs();
    Song findById(Long id);
    Song findByTrackId(String trackId);
    Song saveSong(String title, String trackId, String genre, int releaseYear, Long albumId);
    Song editSong(Long id, String title, String trackId, String genre, int releaseYear, Long albumId);
    void deleteSong(Long id);
    void addArtistToSong(Long artistId, String songId);
    List<Artist> listArtistsForSong(Long songId);
    Song getDetailsForSong(String songId);
}
