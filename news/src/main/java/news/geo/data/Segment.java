package news.geo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
@Getter
@Setter
@ToString
public class Segment extends Shape implements Mesurable1D{

    private Point2D end1;
    private Point2D end2;

    @Override
    public double length() {
        return (Objects.isNull(end1) || Objects.isNull(end2))
                ? Double.NaN
                : end1.distance(end2);
    }

    @Override
    public Segment translate(double deltaX, double deltaY) {
        Stream.of(end1,end2)
                .forEach(end -> Objects.requireNonNull(end).translate(deltaX, deltaY));
        return this;
    }
}
