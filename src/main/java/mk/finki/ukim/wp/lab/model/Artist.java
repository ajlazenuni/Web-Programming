package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(length = 1000)  // For longer bio texts
    private String bio;

    @ManyToMany(mappedBy = "performers", fetch = FetchType.LAZY)
    private List<Song> songsPerformed;

    // Default constructor required by JPA
    public Artist() {
        this.songsPerformed = new ArrayList<>();
    }

    public Artist(Long id, String firstName, String lastName, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songsPerformed = new ArrayList<>();
    }

    // Helper methods for managing the songs list
    public void addSong(Song song) {
        if (this.songsPerformed == null) {
            this.songsPerformed = new ArrayList<>();
        }
        if (!this.songsPerformed.contains(song)) {
            this.songsPerformed.add(song);
            // Maintain bidirectional relationship
            if (!song.getPerformers().contains(this)) {
                song.getPerformers().add(this);
            }
        }
    }

    public void removeSong(Song song) {
        if (this.songsPerformed != null) {
            this.songsPerformed.remove(song);
            // Maintain bidirectional relationship
            if (song.getPerformers().contains(this)) {
                song.getPerformers().remove(this);
            }
        }
    }

    // Lombok will generate getters and setters
}