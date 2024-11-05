package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository {
    private List<Song> songs;

    public SongRepository() {
        songs = new ArrayList<>();
        songs.add(new Song("1", "Sweet Child O' Mine", "Rock", 1987));
        songs.add(new Song("2", "Livin' on a Prayer", "Rock", 1986));
        songs.add(new Song("3", "Space Oddity", "Rock", 1969));
        songs.add(new Song("4", "Bohemian Rhapsody", "Rock", 1975));
        songs.add(new Song("5", "Stairway to Heaven", "Rock", 1971));
    }

    public List<Song> findAll() {
        return songs;
    }

    public Song findByTrackId(String trackId) {
        return songs.stream()
                .filter(song -> song.getTrackId().equals(trackId))
                .findFirst()
                .orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        Song existingSong = findByTrackId(song.getTrackId());
        if (existingSong != null) {
            if (!existingSong.getPerformers().contains(artist)) {
                artist.addSongPerformed(existingSong);
                existingSong.getPerformers().add(artist);
                System.out.println("Added " + artist.getFirstName() + " to " + existingSong.getTitle());
                System.out.println("Artist's songs: " + artist.getSongsPerformed().size());
            }
            return artist;
        }
        return null;
    }

}