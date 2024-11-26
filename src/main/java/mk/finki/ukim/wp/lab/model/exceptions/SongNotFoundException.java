package mk.finki.ukim.wp.lab.model.exceptions;


public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(Long id) {
        super(String.format("Song with id %d was not found", id));
    }

    public SongNotFoundException(String trackId) {
        super(String.format("Song with trackId %s was not found", trackId));
    }
}
