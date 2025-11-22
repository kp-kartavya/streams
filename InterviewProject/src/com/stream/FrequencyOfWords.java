package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyOfWords {
	public static void main(String[] args) {
		String str = "hello hello hello world world hey world";

		String[] arr = str.split("\\s+");

		List<String> list = Arrays.asList(arr);

		Map<String, Long> freq = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println(freq);
	}
}
