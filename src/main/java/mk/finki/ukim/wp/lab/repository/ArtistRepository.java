package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    private List<Artist> artists;

    public ArtistRepository() {
        artists = new ArrayList<>();
        artists.add(new Artist(1L, "Axl", "Rose", "Lead singer of Guns N' Roses"));
        artists.add(new Artist(2L, "Jon", "Bon Jovi", "Founder of Bon Jovi"));
        artists.add(new Artist(3L, "David", "Bowie", "Legendary rock musician"));
        artists.add(new Artist(4L, "Freddie", "Mercury", "Lead singer of Queen"));
        artists.add(new Artist(5L, "Robert", "Plant", "Lead singer of Led Zeppelin"));
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(Long id) {
        return artists.stream()
                .filter(artist -> artist.getId().equals(id))
                .findFirst();
    }
}