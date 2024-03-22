package news.util;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class CityUtil {

    /**
     * Look for 1st city in repository equals to city
     * and returns normalized code
     * @param city city to search
     * @param cityRepository city repository
     * @return city code if city found
     * @throws IllegalArgumentException if city not found
     */
    public static String code(String city, Collection<String> cityRepository){
        var optCity = cityRepository.stream()
                .filter(Predicate.isEqual(city))
                .findFirst();
        return optCity
                .map(c -> c.toUpperCase().substring(0, 3))
                .orElseThrow(() -> new IllegalArgumentException("No city found"));
    }
}
