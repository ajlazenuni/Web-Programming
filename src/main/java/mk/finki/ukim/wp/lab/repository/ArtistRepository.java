package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Artist;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ArtistRepository {
    public List<Artist> findAll() {
        return DataHolder.artists;
    }

    public Optional<Artist> findById(Long id) {
        return DataHolder.artists.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }
    public List<Artist> findByNameContainingIgnoreCase(String name) {
        String lowerCaseName = name.toLowerCase();
        return DataHolder.artists.stream()
                .filter(artist -> artist.getFirstName().toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());
    }
    public Artist save(Artist artist) {
        DataHolder.artists.removeIf(a -> a.getId().equals(artist.getId()));
        DataHolder.artists.add(artist);
        return artist;
    }
}
