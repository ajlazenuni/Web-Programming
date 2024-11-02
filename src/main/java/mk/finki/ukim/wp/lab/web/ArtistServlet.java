package mk.finki.ukim.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(name = "artist-servlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine templateEngine;

    public ArtistServlet(ArtistService artistService, SongService songService, SpringTemplateEngine templateEngine) {
        this.artistService = artistService;
        this.songService = songService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);
        String trackId = req.getParameter("trackId");
        context.setVariable("artists", artistService.listArtists());
        context.setVariable("trackId", trackId);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artistId = req.getParameter("artistId");
        String trackId = req.getParameter("trackId");

        Artist artist = artistService.findById(Long.parseLong(artistId));
        Song song = songService.findByTrackId(trackId);

        if (artist != null && song != null) {
            songService.addArtistToSong(artist, song);
        }

        resp.sendRedirect("/songDetails?trackId=" + trackId);
    }
}