package news.cinema.data;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of={"id", "title", "year"})
public class Movie {
    private int id;
    private String title;
    private int year;
    private Integer duration;
    private String synopsis;
    private String posterUri;
}
