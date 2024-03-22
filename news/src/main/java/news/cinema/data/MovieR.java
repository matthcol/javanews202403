package news.cinema.data;

// implicit: all args constructor, getter, toString, equals, hashCode
public record MovieR(
        int id,
        String title,
        int year,
        Integer duration,
        String synopsis,
        String posterUri
) {
    // add other constructor or static factory
    // override toString
    public static Movie of(String title, int year){
        return new Movie(0, title, year, null, null, null);
    }
}
