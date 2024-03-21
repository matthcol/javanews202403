package news;

import news.data.Movie;
import news.util.MovieCsv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

class DemoStreamMovie2 {
    static List<Movie> movieList;

    @BeforeAll
    static void initData() throws IOException, URISyntaxException {
        String resource = "/movies.tsv";
        Path resourcePath = Path.of(Objects.requireNonNull(DemoStreamMovie2.class.getResource(resource)).toURI());
        // NB: Java 7: try-with-resources
        try (var lineStream = Files.lines(resourcePath)){
            movieList = lineStream
                    .skip(1)  // skip headers
                    .map(MovieCsv::movieFromTsvLine)
                    .toList();
        } // close file under stream
    }

    @Test
    void displaySample(){
        movieList.stream()
                .skip(500)
                .limit(10)
                .forEach(System.out::println);
    }

    // nombre de films de l'année 1984

    // total durée des films commençant par Star Wars

    // longueur maximal d'un titre

    // durée min, max, totale, moyenne et nombre de films Star Wars ...
}
