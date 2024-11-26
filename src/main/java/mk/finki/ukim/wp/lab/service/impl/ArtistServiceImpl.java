package mk.finki.ukim.wp.lab.service.impl;


import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Artist> listArtists() {
        List<Artist> artists = artistRepository.findAll();
        // Ensure songsPerformed is initialized for each artist
        artists.forEach(artist -> {
            if (artist.getSongsPerformed() == null) {
                artist.setSongsPerformed(new ArrayList<>());
            }
        });
        return artists;
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));
    }
}