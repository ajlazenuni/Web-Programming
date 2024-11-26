package mk.finki.ukim.wp.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();
    public static List<Album> albums = new ArrayList<>();
    private static Long songId = 1L;
    private static Long artistId = 1L;
    private static Long albumId = 1L;

    @PostConstruct
    public void init() {
        // Initialize Albums
        albums.add(new Album(albumId++, "Abbey Road", "Rock", "1969"));
        albums.add(new Album(albumId++, "Let It Be", "Rock", "1970"));
        albums.add(new Album(albumId++, "Help!", "Rock", "1965"));
        albums.add(new Album(albumId++, "A Night at the Opera", "Rock", "1975"));
        albums.add(new Album(albumId++, "Jazz", "Rock", "1978"));

        // Initialize Artists
        artists.add(new Artist(artistId++, "John", "Lennon", "Beatles singer"));
        artists.add(new Artist(artistId++, "Paul", "McCartney", "Beatles bassist"));
        artists.add(new Artist(artistId++, "George", "Harrison", "Beatles guitarist"));
        artists.add(new Artist(artistId++, "Ringo", "Starr", "Beatles drummer"));
        artists.add(new Artist(artistId++, "Freddie", "Mercury", "Queen singer"));

        // Initialize Songs with Albums
        songs.add(new Song(songId++, "T1", "Come Together", "Rock", 1969, albums.get(0)));
        songs.add(new Song(songId++, "T2", "Something", "Rock", 1969, albums.get(0)));
        songs.add(new Song(songId++, "T3", "Let It Be", "Rock", 1970, albums.get(1)));
        songs.add(new Song(songId++, "T4", "The Long and Winding Road", "Rock", 1970, albums.get(1)));
        songs.add(new Song(songId++, "T5", "Get Back", "Rock", 1970, albums.get(1)));
    }
}