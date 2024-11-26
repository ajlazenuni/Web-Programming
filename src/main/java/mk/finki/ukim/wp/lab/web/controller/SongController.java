package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.ui.Model;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("songs", songService.listSongs());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "listSongs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        try {
            Song song = songService.findById(id);
            model.addAttribute("song", song);
            model.addAttribute("albums", albumService.findAll());
            return "add-song";
        } catch (RuntimeException ex) {
            return "redirect:/songs?error=" + ex.getMessage();
        }
    }

    @PostMapping("/add")
    public String saveSong(
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            RedirectAttributes redirectAttributes) {
        try {
            validateSongInput(title, trackId, genre, releaseYear);
            songService.saveSong(title, trackId, genre, releaseYear, albumId);
            redirectAttributes.addFlashAttribute("success", "Song successfully created!");
            return "redirect:/songs";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/songs/add-form";
        }
    }

    @PostMapping("/edit/{id}")
    public String editSong(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            RedirectAttributes redirectAttributes) {
        try {
            validateSongInput(title, trackId, genre, releaseYear);
            songService.editSong(id, title, trackId, genre, releaseYear, albumId);
            redirectAttributes.addFlashAttribute("success", "Song successfully updated!");
            return "redirect:/songs";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/songs/edit-form/" + id;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            songService.deleteSong(id);
            redirectAttributes.addFlashAttribute("success", "Song successfully deleted!");
            return "redirect:/songs";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/songs";
        }
    }

    @GetMapping("/details/{songId}")
    public String showSongDetails(@PathVariable String songId, Model model) {
        try {
            Song song = songService.getDetailsForSong(songId);
            model.addAttribute("song", song);
            return "songDetails";
        } catch (RuntimeException ex) {
            return "redirect:/songs?error=" + ex.getMessage();
        }
    }

    private void validateSongInput(String title, String trackId, String genre, int releaseYear) {
        StringBuilder errors = new StringBuilder();

        if (title == null || title.trim().isEmpty()) {
            errors.append("Title cannot be empty. ");
        }

        if (trackId == null || trackId.trim().isEmpty()) {
            errors.append("Track ID cannot be empty. ");
        }

        if (genre == null || genre.trim().isEmpty()) {
            errors.append("Genre cannot be empty. ");
        }

        if (releaseYear < 1900 || releaseYear > 2024) {
            errors.append("Release year must be between 1900 and 2024. ");
        }

        if (errors.length() > 0) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}