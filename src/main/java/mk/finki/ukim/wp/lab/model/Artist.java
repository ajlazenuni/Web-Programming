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
        this.songsPerformed = new ArrayList<>();
    }
    public List<Song> getSongsPerformed() {
        if (songsPerformed == null) {
            songsPerformed = new ArrayList<>();
        }
        return songsPerformed;
    }

    public void addSongPerformed(Song song) {
        if (!getSongsPerformed().contains(song)) {
            getSongsPerformed().add(song);
        if (!song.getPerformers().contains(this)) {
                song.getPerformers().add(this);
            }
        }
    }
}