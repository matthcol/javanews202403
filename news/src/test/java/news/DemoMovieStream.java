package news;

import news.data.Movie;
import news.util.MovieCsv;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class DemoMovieStream {

    @Test
    void readMovies() throws IOException {
        var file = new File(getClass().getResource("/movies.tsv").getFile());
        // Nb: Files.lines => Stream<String>
        Files.lines(file.toPath())
                .skip(1)
                .limit(5)
                .map(line -> line.split("\t"))
                // display data
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    @Test
    void readMovies2() throws IOException {
        var file = new File(getClass().getResource("/movies.tsv").getFile());
        // Nb: Files.lines => Stream<String>
        var movies = Files.lines(file.toPath())
                .skip(1)
                //.limit(5)
                .map(MovieCsv::movieFromTsvLine)
                //.forEach(System.out::println);
                .toList();
        movies.stream()
                .skip(500)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    void oneMovie(){
        var movie1 = new Movie();
        var movie2 = new Movie(1, "E.T.", 1983, null, null, null);
        var movie3 = Movie.builder()
                .title("Dune")
                .year(2021)
                .build();
        Stream.of(movie1, movie2, movie3)
                .forEach(System.out::println);
        System.out.println(movie2.getTitle());
    }
}
