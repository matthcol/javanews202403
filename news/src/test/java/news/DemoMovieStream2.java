package news;

import news.data.Movie;
import news.util.MovieCsv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

class DemoMovieStream2 {
    static List<Movie> movieList;

    @BeforeAll
    static void initData() throws IOException, URISyntaxException {
        String resource = "/movies.tsv";
        Path resourcePath = Path.of(Objects.requireNonNull(DemoMovieStream2.class.getResource(resource)).toURI());
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
    @ParameterizedTest
    @ValueSource(ints={1984, 3000})
    void nbFilmsThisYear(int year) {
        long nombreFilm = movieList.stream()
                .filter(movie -> movie.getYear() == year)
                .count();
        System.out.println("Nombre de films de cette année: " + nombreFilm);
    }

    @Test
    void moviesWithoutDuration(){
        movieList.stream()
                .filter(m -> Objects.isNull(m.getDuration()))
                .forEach(System.out::println);
    }

    // total durée des films commençant par Star Wars
    @ParameterizedTest
    @ValueSource(strings={"Star Wars", "Number"})
    void displayTotalDurarionStartingWith(String beginTitle) {
        int totalDuration = movieList.stream()
                .filter(m -> m.getTitle().startsWith(beginTitle))
                // .peek(System.out::println)
                .peek(m -> System.out.println("Filter: " + m + ", duration: " + m.getDuration()))
                .map(Movie::getDuration)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(MessageFormat.format(
                "Durée totale films commençant par {0} : {1} min",
                beginTitle,
                totalDuration
        ));
    }

    // longueur maximale des titre
    @Test
    void longueurMaximaleTitre() {
        OptionalInt optLongueurMax = movieList.stream()
                //.mapToInt(movie->movie.getTitle().length())
                .map(Movie::getTitle)
                .mapToInt(String::length)
                .max();
        System.out.println(" longeur de titre max: " + optLongueurMax); //9 ??
        if (optLongueurMax.isPresent()){
            int longueurMax = optLongueurMax.getAsInt();
            movieList.stream()
                    .filter(m -> m.getTitle().length() == longueurMax)
                    .forEach(System.out::println);
        }
    }

    // durée min, max, totale, moyenne et nombre de films Star Wars ...
}
