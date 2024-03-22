package news.geo.data;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@ToString(of={"x","y"}, includeFieldNames = false, callSuper = true)
public class Point2D extends Shape {
    private double x;
    private double y;
    public double distance(@NonNull Point2D other){
        return Math.hypot(x - other.x, y - other.y);
    }
    @Override
    public Point2D translate(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
        return this;
    }

    // factory methods
    @Builder
    protected Point2D(String name, double x, double y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    public static Point2D of(String name, double x, double y){
        return Point2D.builder()
                .name(name)
                .x(x)
                .y(y)
                .build();
    }

    public static Point2D of(double x, double y){
        return Point2D.builder()
                .x(x)
                .y(y)
                .build();
    }

    public static Point2D of(){
        return Point2D.builder().build();
    }

    public static Point2D of(String name){
        return Point2D.builder()
                .name(name)
                .build();
    }
    public static Collector<Point2D,?,Optional<String>> joiningNames(String sep){
        return Collectors.mapping(
                Shape::getName,
                Collectors.teeing(
                        Collectors.filtering(Objects::isNull,
                                Collectors.counting()),
                        Collectors.filtering(Objects::nonNull,
                                Collectors.joining(sep)),
                        (count, name) -> ((count > 0) || name.isEmpty())
                                ? Optional.empty()
                                : Optional.of(name)
                )
        );
    }

    public static String nullableNameFromPoints(Stream<? extends Point2D> points){
        return points.collect(joiningNames("-"))
            .orElse(null);
    }
}
