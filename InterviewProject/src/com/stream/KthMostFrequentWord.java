package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KthMostFrequentWord {
	public static void main(String[] args) {
		List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple", "kiwi", "kiwi",
				"banana");

		int k = 2; // e.g., Find the 2nd most frequent

		// Step 1: Count frequencies (Map<String, Long>)
		Set<Map.Entry<String, Long>> frequencySet = words.stream().collect(Collectors.groupingBy(Function.identity(), // Word is the key
				Collectors.counting() // Count is the value
		)).entrySet(); // Convert Map to a Set of Entry objects

		// Step 2: Sort by frequency (value) and get the k-th most frequent
		String kthMostFrequent = frequencySet.stream().sorted((e1, e2) -> {
			// Compare values (frequencies) in descending order
			int freqCompare = Long.compare(e2.getValue(), e1.getValue());
			// Use key (word) for tie-breaking in ascending order
			return freqCompare != 0 ? freqCompare : e1.getKey().compareTo(e2.getKey());
		}).skip(k - 1) // Skip the first (k-1) elements
				.map(Map.Entry::getKey) // Get the key (the word)
				.findFirst() // Get the k-th element
				.orElse(null); // Return null if the k-th element doesn't exist

		System.out.println("The " + k + "-th most frequent word is: " + kthMostFrequent);
		// Output for k=2: "banana" (apple=3, banana=3, kiwi=2, orange=1. Tie broken by
		// key 'b' < 'a' fails, so 'banana' comes second after 'apple' or 'apple' after
		// 'banana' depending on tie-breaker. The code sorts DESCENDING by count, then
		// ASCENDING by key. If the sort yields ['apple', 'banana', 'kiwi', 'orange'],
		// k=2 is 'banana'.)

	}
}
