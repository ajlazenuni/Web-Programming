package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String trackId;

    private String title;
    private String genre;
    private int releaseYear;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "song_artists",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> performers;

    @ManyToOne
    private Album album;

    public Song() {
        this.performers = new ArrayList<>();
    }

    public Song(Long id, String trackId, String title, String genre, int releaseYear, Album album) {
        this.id = id;
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.album = album;
    }

    public void addPerformer(Artist artist) {
        if (this.performers == null) {
            this.performers = new ArrayList<>();
        }
        if (!this.performers.contains(artist)) {
            this.performers.add(artist);
        }
    }

}