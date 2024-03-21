package news.util;

import news.data.Movie;

public class MovieCsv {
    public static Movie movieFromTsvLine(String tsvLine){
        var words = tsvLine.split("\t", -1);
        int id = Integer.parseInt(words[0]);
        String title = words[1];
        int year = Integer.parseInt(words[2]);
        Integer duration = words[3].isEmpty() ? null : Integer.parseInt(words[3]);
        String synopsis = words[4];
        String  posterUri = words[5];
        return Movie.builder()
                .id(id)
                .title(title)
                .year(year)
                .duration(duration)
                .synopsis(synopsis)
                .posterUri(posterUri)
                .build();
    }
}
