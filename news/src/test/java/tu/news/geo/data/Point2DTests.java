package tu.news.geo.data;

import com.sun.jdi.connect.Connector;
import news.geo.data.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Point2DTests {

    @Test
    void testOf(){
        var pt = Point2D.of();
        assertAll(
                () -> assertNull(pt.getName(), "no name"),
                () -> assertEquals(0, pt.getX(), "x"),
                () -> assertEquals(0, pt.getY(), "y")
        );
    }

    @Test
    void testOf_name(){
        var pt = Point2D.of("A");
        assertAll(
                () -> assertEquals("A", pt.getName(), "name"),
                () -> assertEquals(0, pt.getX(), "x"),
                () -> assertEquals(0, pt.getY(), "y")
        );
    }

    @Test
    void testOf_xy(){
        var pt = Point2D.of(1.0, 2.0);
        assertAll(
                () -> assertNull(pt.getName(), "no name"),
                () -> assertEquals(1.0, pt.getX(), "x"),
                () -> assertEquals(2.0, pt.getY(), "y")
        );
    }

    @Test
    void testOf_NameXY(){
        var pt = Point2D.of("A",1.0, 2.0);
        assertAll(
                () -> assertEquals("A", pt.getName(), "name"),
                () -> assertEquals(1.0, pt.getX(), "x"),
                () -> assertEquals(2.0, pt.getY(), "y")
        );
    }

    static Stream<Arguments> fixtureJoinName_hasNoName(){
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        var p3 = Point2D.of("C");
        var pNoName = Point2D.of();
        return Stream.of(
                arguments(named("0", Stream.of())),
                arguments(named("1/1 No Name", Stream.of(pNoName))),
                arguments(named("1/2 No Name (first)", Stream.of(pNoName, p2))),
                arguments(named("1/2 No Name (second)", Stream.of(p1, pNoName))),
                arguments(named("2/2 No Name", Stream.of(pNoName, pNoName))),
                arguments(named("1/3 No Name (first)", Stream.of(pNoName, p2, p3))),
                arguments(named("1/3 No Name (second)", Stream.of(p1, pNoName, p3))),
                arguments(named("1/3 No Name (third) ", Stream.of(p1, p2, pNoName))),
                arguments(named("2/3 No Name (first, second)", Stream.of(pNoName, pNoName, p3))),
                arguments(named("2/3 No Name (first, third)", Stream.of(pNoName, p2, pNoName))),
                arguments(named("2/3 No Name (second, third)", Stream.of(p1, pNoName, pNoName))),
                arguments(named("3/3 No Name", Stream.of(pNoName, pNoName, pNoName)))
        );
    }

    @ParameterizedTest
    @MethodSource("fixtureJoinName_hasNoName")
    void testJoinNames_hasNoName(Stream<Point2D> points){
        String sep = "-";
        var result = points.collect(Point2D.joiningNames(sep));
        assertTrue(result.isEmpty(), "no name is expected");
    }

    static Stream<Arguments> fixtureJoinName_hasName(){
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        var p3 = Point2D.of("C");
        String sep = "-";
        return Stream.of(
                arguments(named("1 point", Stream.of(p1)), sep, "A"),
                arguments(named("2 points", Stream.of(p1, p2)), sep, "A-B"),
                arguments(named("3 points", Stream.of(p1, p2, p3)), sep, "A-B-C")
        );
    }

    @ParameterizedTest
    @MethodSource("fixtureJoinName_hasName")
    void testJoinNames_hasName(Stream<Point2D> points, String sep, String expectedName) {
        var result = points.collect(Point2D.joiningNames(sep));
        assertTrue(result.isPresent(), "name is present");
        result.ifPresent(actualName -> assertEquals(expectedName, actualName));
    }
}