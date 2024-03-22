package tu.news.geo.data;

import news.geo.data.Point2D;
import news.geo.data.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PolygonTest {
    @Test
    void testOf_3Vertices() {
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        var p3 = Point2D.of("C");
        var polygon = Polygon.of(p1, p2, p3);
        assertAll(
                () -> assertEquals("A-B-C", polygon.getName()),
                () -> assertSame(p1, polygon.vertixAt(0)),
                () -> assertSame(p2, polygon.vertixAt(1)),
                () -> assertSame(p3, polygon.vertixAt(2))
        );
    }

    @Test
    void testOf_CollectionOf3Vertices() {
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        var p3 = Point2D.of("C");
        var polygon = Polygon.of(List.of(p1,p2, p3));
        assertAll(
                () -> assertEquals("A-B-C", polygon.getName()),
                () -> assertSame(p1, polygon.vertixAt(0)),
                () -> assertSame(p2, polygon.vertixAt(1)),
                () -> assertSame(p3, polygon.vertixAt(2))
        );
    }

    @Test
    void testOf_Named3Vertices() {
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        var p3 = Point2D.of("C");
        var polygon = Polygon.of("Poly", p1, p2, p3);
        assertAll(
                () -> assertEquals("Poly", polygon.getName()),
                () -> assertSame(p1, polygon.vertixAt(0)),
                () -> assertSame(p2, polygon.vertixAt(1)),
                () -> assertSame(p3, polygon.vertixAt(2))
        );
    }

    @Test
    void testOf_NamedCollectionOf3Vertices() {
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        var p3 = Point2D.of("C");
        var polygon = Polygon.of("Poly", List.of(p1,p2, p3));
        assertAll(
                () -> assertEquals("Poly", polygon.getName()),
                () -> assertSame(p1, polygon.vertixAt(0)),
                () -> assertSame(p2, polygon.vertixAt(1)),
                () -> assertSame(p3, polygon.vertixAt(2))
        );
    }

    static Stream<Arguments> fixtureOf_KoVertixNullIn3First(){
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        return Stream.of(
                arguments(null, named("p1", p1), named("p2", p2)),
                arguments(named("p1", p1), null, named("p2", p2)),
                arguments(named("p1", p1), null, null),
                arguments(null, named("p1", p1), null),
                arguments(null, null, named("p1", p1)),
                arguments(null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("fixtureOf_KoVertixNullIn3First")
    void testOf_KoVertixNullInList(Point2D p1, Point2D p2, Point2D p3){
        var points = new ArrayList<Point2D>();
        Collections.addAll(points, p1, p2, p3);
        Assertions.assertEquals(3, points.size());
        Assertions.assertThrows(NullPointerException.class, () -> Polygon.of(points));
    }
    @ParameterizedTest
    @MethodSource("fixtureOf_KoVertixNullIn3First")
    void testOf_KoVertixNullInMandatoryArgs(Point2D p1, Point2D p2, Point2D p3){
        Assertions.assertThrows(NullPointerException.class, () -> Polygon.of(p1, p2, p3));
    }
    @Test
    void testOf_KoVertixNullInVarArgs(){
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        var p3 = Point2D.of("C");
        Assertions.assertThrows(NullPointerException.class, () -> Polygon.of(p1, p2, p3, null));
    }

    static Stream<Arguments> fixtureOf_KoNotEnoughtVertices(){
        var p1 = Point2D.of("A");
        var p2 = Point2D.of("B");
        return Stream.of(
                arguments(named("No Vertix", List.of())),
                arguments(named("1 Vertix", List.of(p1))),
                arguments(named("2 Vertices", List.of(p1, p2)))
        );
    }
    @ParameterizedTest
    @MethodSource("fixtureOf_KoNotEnoughtVertices")
    void testOf_KoNotEnoughtVertices(List<Point2D> points){
        Assertions.assertThrows(IllegalArgumentException.class, () -> Polygon.of(points));
    }
}