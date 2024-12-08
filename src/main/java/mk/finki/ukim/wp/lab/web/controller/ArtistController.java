package mk.finki.ukim.wp.lab.web.controller;

import org.springframework.ui.Model;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/add")
    public String getAddArtistPage(@RequestParam String songId, Model model) {
        try {
            model.addAttribute("artists", artistService.listArtists());
            model.addAttribute("songId", songId);
            model.addAttribute("song", songService.findByTrackId(songId));
            return "artistsList";
        } catch (RuntimeException ex) {
            return "redirect:/songs?error=" + ex.getMessage();
        }
    }
    @GetMapping
    public String getArtistsPage(Model model) {
        model.addAttribute("artists", artistService.listArtists());
        return "listArtists";
    }
    @GetMapping("/search")
    public String searchArtists(@RequestParam String name, Model model) {
        try {
            model.addAttribute("artists", artistService.searchByName(name));
            return "listArtists";
        } catch (RuntimeException ex) {
            model.addAttribute("error", "Error occurred while searching for artists.");
            return "listArtists";
        }
    }
    @PostMapping("/add-to-song")
    public String addArtistToSong(
            @RequestParam Long artistId,
            @RequestParam String songId,
            RedirectAttributes redirectAttributes) {
        try {
            songService.addArtistToSong(artistId, songId);
            redirectAttributes.addFlashAttribute("success",
                    "Artist successfully added to song!");
            return "redirect:/songs/details/" + songId;
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/artists/add?songId=" + songId;
        }
    }
}