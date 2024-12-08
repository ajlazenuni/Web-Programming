package mk.finki.ukim.wp.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.jpa.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ArtistRepository;
import mk.finki.ukim.wp.lab.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public DataInitializer(AlbumRepository albumRepository,
                           ArtistRepository artistRepository,
                           SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        if (albumRepository.count() == 0) {
            // Initialize Albums
            Album abbeyRoad = albumRepository.save(new Album(null, "Abbey Road", "Rock", "1969"));
            Album letItBe = albumRepository.save(new Album(null, "Let It Be", "Rock", "1970"));
            Album help = albumRepository.save(new Album(null, "Help!", "Rock", "1965"));
            Album nightAtOpera = albumRepository.save(new Album(null, "A Night at the Opera", "Rock", "1975"));
            Album jazz = albumRepository.save(new Album(null, "Jazz", "Rock", "1978"));

            // Initialize Artists
            Artist johnLennon = artistRepository.save(new Artist(null, "John", "Lennon", "Beatles singer"));
            Artist paulMcCartney = artistRepository.save(new Artist(null, "Paul", "McCartney", "Beatles bassist"));
            Artist georgeHarrison = artistRepository.save(new Artist(null, "George", "Harrison", "Beatles guitarist"));
            Artist ringoStarr = artistRepository.save(new Artist(null, "Ringo", "Starr", "Beatles drummer"));
            Artist freddieMercury = artistRepository.save(new Artist(null, "Freddie", "Mercury", "Queen singer"));

            // Initialize Songs with Albums and Artists
            Song comeTogether = new Song(null, "T1", "Come Together", "Rock", 1969, abbeyRoad);
            comeTogether.addPerformer(johnLennon);
            comeTogether.addPerformer(paulMcCartney);
            songRepository.save(comeTogether);

            Song something = new Song(null, "T2", "Something", "Rock", 1969, abbeyRoad);
            something.addPerformer(georgeHarrison);
            songRepository.save(something);

            Song letItBeSong = new Song(null, "T3", "Let It Be", "Rock", 1970, letItBe);
            letItBeSong.addPerformer(paulMcCartney);
            songRepository.save(letItBeSong);

            Song longAndWinding = new Song(null, "T4", "The Long and Winding Road", "Rock", 1970, letItBe);
            longAndWinding.addPerformer(paulMcCartney);
            songRepository.save(longAndWinding);

            Song getBack = new Song(null, "T5", "Get Back", "Rock", 1970, letItBe);
            getBack.addPerformer(johnLennon);
            getBack.addPerformer(paulMcCartney);
            songRepository.save(getBack);
        }
    }
}