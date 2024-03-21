package news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoStringStream {

	List<String> cities;
	
	@BeforeEach
	void initData() {
		cities = new ArrayList<>();
		Collections.addAll(cities, 
				"Toulouse", "Pau", "Montauban", 
				"Lille", "Rennes", "Castres",
				"Rouen"
		);
	}
	
	@Test
	void demoStreamPrint() {
		cities.forEach(System.out::println);
	}
	
	@Test
	void demoMapFilter_J8() {
		List<String> result = cities.stream()
			//.map(city -> city.toUpperCase()) // transformation function
			.map(String::toUpperCase) // string -> string
			//.filter(String::startsWith)  // Error profil incorrect: string x string -> bool 
			.filter(city -> city.startsWith("R")) // predicate function
			.collect(Collectors.toList());
		System.out.println(result);
	}
	
	@Test
	void demoMapFilter_J11_J17() {
		//new keyword 'var' since Java 10 (LTS: Java 11)
		var result = cities.stream()
			.map(String::toUpperCase) // string -> string
			.peek(city -> System.out.println("After UpperCase: " + city))
			.filter(city -> city.startsWith("R")) // predicate function
			.peek(city -> System.out.println("After Filter: " + city))
			.toList(); // shortcut since Java 16 (LTS: Java 17) 
		System.out.println("Final result: " + result);
	}
	
	@Test
	void demoVar_ko() {
		// non conseillé !
		var city = "Toulouse";
		var nb = 12; // int
		var nbs = (short) 12;
		var nbl = 12L;
		Stream.of(city, nb, nbs, nbl)
			.forEach(System.out::println);
	}
	
	@Test
	void demoVar_ok() {
		// foreach loop
		for (var city: cities) {
			System.out.println(city.toLowerCase());
		}
		// var assigned to function result
		var cityMin = Collections.min(cities);
		System.out.println("City min: " + cityMin);
	}
	
	@Test
	void demoStreamStats() {
		// GOAL: avoid autoboxing on primitive types while streaming
		List<Integer> numbers;
		int x = 2; // STACK
		int res = cities.stream()  // Stream<String>
			.filter(city -> city.toLowerCase().indexOf("a") > 0)
			//.map(String::length) // Stream<Integer> => create n box Integer HEAP
			.mapToInt(String::length) // IntStream => primitive int STACK
			.sum();
		System.out.println("Total number of letters: " + res);		
	}
	
	@Test
	void demoImperativeStats() {
		// GOAL: avoid autoboxing on primitive types while streaming
		int total = 0;
		for (var city: cities) {
			if (city.toLowerCase().indexOf("a") > 0) {
				total += city.length();
			}
		}
		System.out.println("Total number of letters: " + total);		
	}
	
	
}











