package tu.news.geo.data;

import news.geo.data.Point2D;
import news.geo.data.Polygon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PolygonMesurable2DTests {

    static Polygon triangle345;
    static Polygon rectangle;
    static Polygon hexagon;
    @BeforeAll
    static void createPolygons(){
        var p1 = Point2D.of(1, 6);
        var pB = Point2D.of(4, 6);
        var pC = Point2D.of(4, 10);
        var pD = Point2D.of(1, 10);
        var p2 = Point2D.of(3,1);
        var p3 = Point2D.of(7,2);
        var p4 = Point2D.of(4,4);
        var p5 = Point2D.of(8,5);
        triangle345 = Polygon.of(p1, pB, pC);
        rectangle = Polygon.of(p1, pB, pC, pD);
        // hexagon fromWikipedia article:
        // https://en.wikipedia.org/wiki/Shoelace_formula
        hexagon = Polygon.of(p1, p2, p3, p4, p5);

    }
    static Stream<Arguments> fixtureArea(){
        return Stream.of(
                arguments(named("triangle345", triangle345), 6.0),
                arguments(named("rectangle", rectangle), 12.0),
                arguments(named("concave pentagon", hexagon), 16.5)
        );
    }
    @ParameterizedTest
    @MethodSource("fixtureArea")
    void testArea(Polygon polygon, double expectedArea) {
        double actualArea = polygon.area();
        assertEquals(expectedArea, actualArea, 1E-10);
    }

    static Stream<Arguments> fixturePerimeter(){
        return Stream.of(
                arguments(named("triangle345", triangle345), 12.0),
                arguments(named("rectangle", rectangle), 14.0)
        );
    }

    @ParameterizedTest
    @MethodSource("fixturePerimeter")
    void perimeter(Polygon polygon, double expectedPerimeter) {
        double actualPerimeter = polygon.perimeter();
        assertEquals(expectedPerimeter, actualPerimeter, 1E-10);
    }
}