package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class SongRepository {
    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId) {
        return DataHolder.songs.stream()
                .filter(s -> s.getTrackId().equals(trackId))
                .findFirst()
                .orElse(null);
    }

    public Optional<Song> findById(Long id) {
        return DataHolder.songs.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public Song save(Song song) {
        DataHolder.songs.removeIf(s -> s.getId().equals(song.getId()));
        DataHolder.songs.add(song);
        return song;
    }

    public void deleteById(Long id) {
        DataHolder.songs.removeIf(s -> s.getId().equals(id));
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return artist;
    }
}