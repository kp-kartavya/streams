package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrequencyOfCharacters {

	public static void main(String[] args) {
		// Using intstream
		String str = "Hello World";
		IntStream in = str.chars();

		Map<Character, Long> freq = in.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		System.out.println(freq);
		
		// Using flatmap
		List<String> list = Arrays.asList("Hello", "World");
		Map<Character, Long> maplist = list.stream()
		    .flatMap(s -> s.chars().mapToObj(c -> (char) c)) // Flatten to a stream of Characters
		    .collect(Collectors.groupingBy(
		        Function.identity(), // Group by the character
		        Collectors.counting() // Count occurrences
		    ));
		System.out.println(maplist);
	}

}
