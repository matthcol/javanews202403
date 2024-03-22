package news.geo.data;

import lombok.*;
import news.geo.contract.Mesurable2D;

import java.util.Objects;

@Getter
@ToString(callSuper = true, includeFieldNames = false)
public class Circle extends Shape implements Mesurable2D {
    @Setter
    private double radius;

    private final Point2D center;

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return Math.PI * radius * 2;
    }

    @Override
    public Shape translate(double deltaX, double deltaY) {
        return null;
    }

    @Builder
    public Circle(String name, double radius, Point2D center) {
        super(name);
        this.radius = radius;
        this.center = Objects.requireNonNull(center);
    }

    public static Circle of(String name, double radius, Point2D center) {
        return Circle.builder()
                .name(name)
                .radius(radius)
                .center(center)
                .build();
    }

    public static Circle of(double radius, Point2D center) {
        return Circle.builder()
                .radius(radius)
                .center(center)
                .build();
    }
}