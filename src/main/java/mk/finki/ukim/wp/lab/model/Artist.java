package mk.finki.ukim.wp.lab.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Artist {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Song> songsPerformed;

    public Artist(Long id, String firstName, String lastName, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.songsPerformed = new ArrayList<>();  // Initialize the list
    }

    // Existing getters and setters...

    public List<Song> getSongsPerformed() {
        return songsPerformed;
    }

    public void setSongsPerformed(List<Song> songsPerformed) {
        this.songsPerformed = songsPerformed;
    }

    // Helper methods for managing the songs list
    public void addSong(Song song) {
        if (this.songsPerformed == null) {
            this.songsPerformed = new ArrayList<>();
        }
        this.songsPerformed.add(song);
    }

    public void removeSong(Song song) {
        if (this.songsPerformed != null) {
            this.songsPerformed.remove(song);
        }
    }
}