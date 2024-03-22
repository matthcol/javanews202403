package tu.news.geo.data;

import news.geo.data.Circle;
import news.geo.data.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @ParameterizedTest
    @CsvSource({
        "1.0,3.141592653589793",
        "10.0,314.1592653589793",
        "100.0,31415.92653589793",
        "1E150,3.141592653589793E300"
    })
    void testArarea(double radius, double expectedArea) {
        var center = Point2D.of();
        var circle = Circle.of(radius, center);
        double margin = expectedArea / 1E15;
        double actualArea = circle.area();
        Assertions.assertEquals(expectedArea, actualArea, margin);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0,6.283185307179586",
            "10.0,62.83185307179586",
            "100.0,628.3185307179586",
            "1E300,6.283185307179586E300"
    })
    void perimeter(double radius, double expectedPerimeter) {
        var center = Point2D.of();
        var circle = Circle.of(radius, center);
        double margin = expectedPerimeter / 1E15;
        double actualPerimeter = circle.perimeter();
        Assertions.assertEquals(expectedPerimeter, actualPerimeter, margin);
    }

}