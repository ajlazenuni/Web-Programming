package mk.finki.ukim.wp.lab.service.impl;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.jpa.SongRepository;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private Long counter;

    public SongServiceImpl(SongRepository songRepository,
                           AlbumRepository albumRepository,
                           ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.counter = 6L; // Since we have 5 initial songs
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));
    }

    @Override
    public Song findByTrackId(String trackId) {
        Song song = songRepository.findByTrackId(trackId);
        if (song == null) {
            throw new RuntimeException("Song not found with trackId: " + trackId);
        }
        return song;
    }

    @Override
    public Song saveSong(String title, String trackId, String genre, int releaseYear, Long albumId) {
        // Validate input
        if (title == null || title.isEmpty()) {
            throw new RuntimeException("Title cannot be empty");
        }
        if (trackId == null || trackId.isEmpty()) {
            throw new RuntimeException("Track ID cannot be empty");
        }
        if (genre == null || genre.isEmpty()) {
            throw new RuntimeException("Genre cannot be empty");
        }
        if (releaseYear < 1900 || releaseYear > 2024) {
            throw new RuntimeException("Invalid release year");
        }

        // Check if trackId is unique
        Song existingSong = songRepository.findByTrackId(trackId);
        if (existingSong != null) {
            throw new RuntimeException("Song with track ID " + trackId + " already exists");
        }

        // Get album
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + albumId));

        // Create and save new song
        Song song = new Song(counter++, trackId, title, genre, releaseYear, album);
        return songRepository.save(song);
    }

    @Override
    public Song editSong(Long id, String title, String trackId, String genre, int releaseYear, Long albumId) {
        // Validate input
        if (title == null || title.isEmpty()) {
            throw new RuntimeException("Title cannot be empty");
        }
        if (trackId == null || trackId.isEmpty()) {
            throw new RuntimeException("Track ID cannot be empty");
        }
        if (genre == null || genre.isEmpty()) {
            throw new RuntimeException("Genre cannot be empty");
        }
        if (releaseYear < 1900 || releaseYear > 2024) {
            throw new RuntimeException("Invalid release year");
        }

        // Get existing song
        Song song = findById(id);

        // Check if trackId is unique (excluding current song)
        Song existingSong = songRepository.findByTrackId(trackId);
        if (existingSong != null && !existingSong.getId().equals(id)) {
            throw new RuntimeException("Song with track ID " + trackId + " already exists");
        }

        // Get album
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + albumId));

        // Update song
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        return songRepository.save(song);
    }

    @Override
    public void deleteSong(Long id) {
        // Check if song exists
        findById(id); // This will throw if song doesn't exist
        songRepository.deleteById(id);
    }

    @Override
    public void addArtistToSong(Long artistId, String songId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + artistId));

        Song song = findByTrackId(songId);
        if (song == null) {
            throw new RuntimeException("Song not found with trackId: " + songId);
        }

        // Check if artist is already added
        boolean artistExists = song.getPerformers().stream()
                .anyMatch(a -> a.getId().equals(artistId));

        if (artistExists) {
            throw new RuntimeException("Artist is already added to this song");
        }

        // Update both sides of the relationship
        song.addPerformer(artist);
        artist.addSong(song);

        // Save both entities
        artistRepository.save(artist);
        songRepository.save(song);
    }
    @Override
    public List<Artist> listArtistsForSong(Long songId) {
        Song song = findById(songId);
        return song.getPerformers();
    }

    @Override
    public Song getDetailsForSong(String songId) {
        return findByTrackId(songId);
    }
}