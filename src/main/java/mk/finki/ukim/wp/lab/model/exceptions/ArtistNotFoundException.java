package mk.finki.ukim.wp.lab.model.exceptions;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(Long id) {
        super(String.format("Artist with id %d was not found", id));
    }
}