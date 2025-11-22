package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertListToMap {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Hello", "World");
		Map<Character, Long> maplist = list.stream().flatMap(s -> s.chars().mapToObj(c -> (char) c)) // Flatten to a
				.collect(Collectors.groupingBy(Function.identity(), // Group by the character
						Collectors.counting() // Count occurrences
				));
		System.out.println(maplist);
	}
}
