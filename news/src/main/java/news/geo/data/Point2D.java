package news.geo;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@SuperBuilder
@Getter
@Setter
@ToString
public class Point2D extends Shape {
    private double x;
    private double y;

    double distance(@NonNull Point2D other){
        return Math.hypot(x - other.x, y - other.y);
    }
    @Override
    public Point2D translate(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
        return this;
    }
}
