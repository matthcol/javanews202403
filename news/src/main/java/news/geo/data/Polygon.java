package news.geo.data;

import lombok.*;
import news.geo.contract.Mesurable2D;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ToString(callSuper = true, includeFieldNames = false)
public class Polygon extends Shape implements Mesurable2D, Iterable<Point2D> {

    private final List<Point2D> vertices;
    @Override
    public Iterator<Point2D> iterator() {
        return vertices.iterator();
    }
    public Point2D vertixAt(int index){
        return vertices.get(index);
    }
    @Override
    public double area() {
        // shoelace formula
        double res = 0.0;
        int n = vertices.size();
        return Math.abs(0.5 * IntStream.range(0, n)
                .mapToDouble(i -> {
                    var current = vertices.get(i);
                    var next = vertices.get((i+1)%n);
                    return current.getX() * next.getY() - next.getX() * current.getY();
                })
                .sum()
        );
    }

    @Override
    public double perimeter() {
        double res = 0.0;
        var previous = vertices.get(vertices.size()-1);
        for (var vertix: vertices){
            res += previous.distance(vertix);
            previous = vertix;
        }
        return res;
    }

    @Override
    public Polygon translate(double deltaX, double deltaY) {
        vertices.forEach(v -> v.translate(deltaX, deltaY));
        return this;
    }

    @Builder
    protected Polygon(String name, Stream<? extends Point2D> vertices, int size) {
        super(name);
        // share vertices, not the list
        this.vertices = vertices.collect(Collectors.toCollection(() -> new ArrayList<>(size)));
        if (this.vertices.size() < 3) {
            throw new IllegalArgumentException("At least 3 vertices are expected");
        }
    }

    public static Polygon of(String name, Collection<? extends Point2D> vertices){
        return Polygon.builder()
                .name(name)
                .vertices(vertices.stream().map(Objects::requireNonNull))
                .size(vertices.size())
                .build();
    }

    public static Polygon of(Collection<? extends Point2D> vertices){
        String name = Point2D.nullableNameFromPoints(vertices.stream());
        return of(name, vertices);
    }
    public static Polygon of(String name, @NonNull Point2D p1, @NonNull Point2D p2, @NonNull Point2D p3, Point2D... others){
        return Polygon.builder()
                .name(name)
                .vertices(Stream.concat(
                        Stream.of(p1, p2,p3),
                        Arrays.stream(others)
                                .map(Objects::requireNonNull)
                ))
                .size(others.length + 3)
                .build();
    }
    public static Polygon of(Point2D p1, Point2D p2, Point2D p3, Point2D... others){
        String name = Point2D.nullableNameFromPoints(Stream.concat(Stream.of(p1, p2,p3), Arrays.stream(others)));
        return of(name, p1, p2, p3, others);
    }

}
