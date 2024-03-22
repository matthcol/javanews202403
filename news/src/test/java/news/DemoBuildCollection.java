package news;

import com.sun.source.doctree.SeeTree;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

public class DemoBuildCollection {

    @Test
    void demoBuildCollection(){
        // => Java 8
        List<String> cities = new ArrayList<>();
        Collections.addAll(cities,
                "Toulouse", "Montauban", "Rennes",
                "Lille"
        );
        // Java 11 LTS: non mutable collections
        var cityList = List.of(
                "Toulouse", "Montauban",
                "Rennes", "Lille");
        var citySet = Set.of("Toulouse", "Montauban",
                "Rennes", "Lille");
        var cityMap = Map.of(
                "Toulouse", 700_000,
                "Montauban", 50_000,
                "Pau", 77_000,
                "Lille", 200_000,
                "Rennes", 215_000
        );
        Stream.of(cityList, citySet, cityMap)
                .forEach(c -> {
                    System.out.println(c);
                    System.out.println("class: " + c.getClass());
                    System.out.println();
                });
    }
}
