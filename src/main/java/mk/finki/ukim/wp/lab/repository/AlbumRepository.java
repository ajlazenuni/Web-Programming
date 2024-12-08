package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumRepository {
    public List<Album> findAll() {
        return DataHolder.albums;
    }

    public Optional<Album> findById(Long id) {
        return DataHolder.albums.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public Album save(Album album) {
        DataHolder.albums.removeIf(a -> a.getId().equals(album.getId()));
        DataHolder.albums.add(album);
        return album;
    }
}