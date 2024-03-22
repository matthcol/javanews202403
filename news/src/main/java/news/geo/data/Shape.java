package news.geo;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public abstract class Shape implements Translatable {
    private String name;
    @Override
    public abstract Shape translate(double deltaX, double deltaY);
}
