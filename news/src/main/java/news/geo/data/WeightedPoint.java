package news.geo.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true, includeFieldNames = false)
public class WeightedPoint extends Point2D {
    private double weight;

    @Builder(builderMethodName = "builderW")
    protected WeightedPoint(String name, double x, double y, double weight) {
        super(name, x, y);
        this.weight = weight;
    }

    public static WeightedPoint of(String name, double x, double y, double weight) {
        return WeightedPoint.builderW()
                .name(name)
                .x(x)
                .y(y)
                .weight(weight)
                .build();
    }

    public static WeightedPoint of(double x, double y, double weight) {
        return WeightedPoint.builderW()
                .x(x)
                .y(y)
                .weight(weight)
                .build();
    }

    public static WeightedPoint of(String name, double weight) {
        return WeightedPoint.builderW()
                .name(name)
                .weight(weight)
                .build();
    }

}
