package mk.finki.ukim.wp.lab.web.controller;


import mk.finki.ukim.wp.lab.model.exceptions.AlbumNotFoundException;
import mk.finki.ukim.wp.lab.model.exceptions.ArtistNotFoundException;
import mk.finki.ukim.wp.lab.model.exceptions.DuplicateTrackIdException;
import mk.finki.ukim.wp.lab.model.exceptions.SongNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            SongNotFoundException.class,
            ArtistNotFoundException.class,
            AlbumNotFoundException.class,
            DuplicateTrackIdException.class
    })
    public String handleNotFoundException(RuntimeException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/songs";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error",
                "An error occurred: " + ex.getMessage());
        return "redirect:/songs";
    }
}