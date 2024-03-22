package news;

import org.junit.jupiter.api.Test;

import java.util.List;
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
        var optRes2 = Stream.<String>of("Pau", "Toulouse")
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

        // how to compute if input Optiona.Empty, you can return:
        // - Optional.Empty
        // - special value: 0, NaN
        // - throw Exception
    }
}
