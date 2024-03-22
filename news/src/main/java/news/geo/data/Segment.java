package news.geo.data;

import lombok.*;
import lombok.experimental.SuperBuilder;
import news.geo.contract.Mesurable1D;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@ToString(callSuper = true, includeFieldNames = false)
public class Segment extends Shape implements Mesurable1D {

    private final Point2D end1;
    private final Point2D end2;

    @Builder
    protected Segment(String name, @NonNull Point2D end1, @NonNull Point2D end2) {
        super(name);
        this.end1 = end1;
        this.end2 = end2;
    }

    public static Segment of(String name, Point2D end1, Point2D end2) {
        return Segment.builder()
                .name(name)
                .end1(end1)
                .end2(end2)
                .build();
    }
    public static Segment of(Point2D end1, Point2D end2){
        String name = Stream.of(end1, end2)
                .collect(Point2D.joiningNames("-"))
                .orElse(null);
        return Segment.of(name, end1, end2);
    }
    @Override
    public double length() {
        return end1.distance(end2);
    }

    @Override
    public Segment translate(double deltaX, double deltaY) {
        Stream.of(end1,end2)
                .forEach(end -> end.translate(deltaX, deltaY));
        return this;
    }
}
