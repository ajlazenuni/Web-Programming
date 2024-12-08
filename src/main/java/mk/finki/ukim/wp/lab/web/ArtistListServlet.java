package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.service.ArtistService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


//@WebServlet(name = "artist-list-servlet", urlPatterns = "/artists")
public class ArtistListServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SpringTemplateEngine templateEngine;

    public ArtistListServlet(ArtistService artistService, SpringTemplateEngine templateEngine) {
        this.artistService = artistService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);
        List<Artist> artists = artistService.listArtists();
        System.out.println("Number of artists: " + artists.size());
        artists.forEach(artist -> {
            System.out.println("Artist: " + artist.getFirstName() + " " + artist.getLastName());
          //  System.out.println("Songs performed: " + artist.getSongsPerformed().size());
        });
        context.setVariable("artists", artists);
        templateEngine.process("listArtists.html", context, resp.getWriter());
    }
}