package mk.finki.ukim.wp.lab.model.exceptions;

public class DuplicateTrackIdException extends RuntimeException {
    public DuplicateTrackIdException(String trackId) {
        super(String.format("Song with trackId %s already exists", trackId));
    }
}