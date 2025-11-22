package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyIsWordValueIsLength {
	public static void main(String[] args) {
		List<String> str = Arrays.asList("hello", "world", "hey");

		Map<String, Integer> map = str.stream().collect(Collectors.toMap(word -> word, String::length));
		
		System.out.println(map);
	}
}
