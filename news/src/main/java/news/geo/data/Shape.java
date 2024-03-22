package news.geo.data;

import lombok.*;
import news.geo.contract.Translatable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(includeFieldNames = false)
public abstract class Shape implements Translatable {
    private String name;
    @Override
    public abstract Shape translate(double deltaX, double deltaY);
}
