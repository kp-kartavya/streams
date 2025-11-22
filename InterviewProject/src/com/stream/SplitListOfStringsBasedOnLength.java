package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SplitListOfStringsBasedOnLength {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("kartavya", "kp", "arun", "sid", "siddharth");

		// Partition into two lists: length > 3 and length <= 3
		Map<Boolean, List<String>> partitioned = names.stream()
				.collect(Collectors.partitioningBy(name -> name.length() > 3));

		// The key 'true' holds the list of strings satisfying the Predicate (length >
		// 3)
		List<String> longerThan3 = partitioned.get(true);
		// The key 'false' holds the list of strings NOT satisfying the Predicate
		// (length <= 3)
		List<String> shorterOrEqual3 = partitioned.get(false);

		System.out.println("Length > 3: " + longerThan3);
		System.out.println("Length <= 3: " + shorterOrEqual3);
		// Output:
		// Length > 3: [kartavya, arun, siddharth]
		// Length <= 3: [kp, sid]

	}
}
