package news.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieRTest {

    @Test
    void demoMovieR(){
        var movie1 = new MovieR(1, "Dune", 2021, null, null,null);
        var movie2 = new MovieR(1, "Dune", 2021, null, null,null);
        System.out.println(movie1);
        System.out.println(movie2.title());
        // not modifiable
        assertEquals(movie1, movie2);
        var movie3 = MovieR.of("Dune: Part Two", 2024);
        System.out.println(movie3);
    }
}