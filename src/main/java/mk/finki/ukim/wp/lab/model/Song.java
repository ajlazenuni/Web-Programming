package mk.finki.ukim.wp.lab.model;
import lombok.Data;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;
    private Album album;

    public Song(Long id, String trackId, String title, String genre, int releaseYear, Album album) {
        this.id = id;
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.album = album;

    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTrackId() { return trackId; }
    public void setTrackId(String trackId) { this.trackId = trackId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public List<Artist> getPerformers() { return performers; }
    public void setPerformers(List<Artist> performers) { this.performers = performers; }
    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }
    public void addPerformer(Artist artist) {
        if (this.performers == null) {
            this.performers = new ArrayList<>();
        }
        if (!this.performers.contains(artist)) {
            this.performers.add(artist);
        }
    }
}
