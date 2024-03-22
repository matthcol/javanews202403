package news;

import news.util.CityUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

public class DemoOptional {

    // Return type of a method
    // null => Optional<T>, OptionalInt, OptionalDouble, ...

    // fields
    //  => @Null @NotNull (bean validation)
    //  => @Column(nullable=false (JPA/Hibernate)

    @Test
    void demoOptional(){
        var optRes = Stream.<String>of()
                .findFirst();
        // System.out.println(optRes);
        var optRes2 = Stream.<String>of("Toulouse", "Pau")
                .findFirst();
        // System.out.println(optRes2);
        for (var optCity: List.of(optRes, optRes2)){
            System.out.println(optCity);
            if (optCity.isPresent()){
                String city = optCity.get();
                System.out.println("Extract from optional: " + city);
            }
            System.out.println();
        }

        // functional: consumer
        System.out.println("Consume optionals");
        Stream.of(optRes, optRes2)
                .forEach(optCity -> optCity.ifPresent(
                        city -> System.out.println("Extract from optional: " + city))
                );

        System.out.println("Consume optionals else");
        Stream.of(optRes, optRes2)
                .forEach(optCity -> optCity.ifPresentOrElse(
                        city -> System.out.println("Extract from optional: " + city),
                        () -> System.out.println("No data")
                ));
        System.out.println();
        // how to compute if input Optional.Empty, you can return:
        // - Optional.Empty
        System.out.println("Compute with optional, returning empty optional if not present");
        Stream.of(optRes, optRes2)
                .forEach(optCity -> {
                    Optional<String> res = optCity.map(
                            city -> city.toUpperCase().substring(0,3));
                    System.out.println("Value computed: " + res);
                });
        System.out.println();

        // - special value: 0, NaN
            // Ex: longueur du nom de la ville, 0 sinon
        System.out.println("Compute with optional, returning special value if not present");
        Stream.of(optRes, optRes2)
                .forEach(optCity -> {
                    int res1 = optCity
                            .map(String::length)
                            .orElse(0);
                    int res2 = optCity.stream()
                            .mapToInt(String::length)
                            .findFirst()
                            .orElse(0);
                    System.out.println("Value computed: " + res1
                            + ", " + res2
                    );
                });
        System.out.println();
        // - throw Exception

        System.out.println("Compute with optional, throwing exception if not present");
        for (var optCity: List.of(optRes, optRes2)){
            try {
                // compute or throw exception
                var cityCode = optCity.map(city -> city.toUpperCase().substring(0, 3))
                        .orElseThrow(() -> new IllegalArgumentException("No city found"));
                // handle result
                System.out.println("Computed code: " + cityCode);
            } catch (IllegalArgumentException e){
                System.out.println("Computation impossible for: " + optCity);
            }
        }
        System.out.println();
    }

}
