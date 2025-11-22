package com.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctEmployeesByEmail {
	public static void main(String[] args) {
		List<Employee> list = Arrays.asList(new Employee("Kartavya", "kp@kp.com"), new Employee("kp", "kp@kp.com"), // Duplicate
																													// email
				new Employee("Pandey", "pandey@pandey.com"), new Employee("Pandey", "kartavya@kartavya.com"));

		// Collect into a Map using the email as the key.
		// The merge function (existing, replacement) -> existing
		// ensures that when a duplicate email is encountered, the *existing* employee
		// (the one found *first* in the stream) is kept, thus getting distinct
		// employees.

		Collection<Employee> distinctEmployees = list.stream().collect(Collectors.toMap(Employee::getEmail, // Key is
																											// the email
				e -> e, // Value is the Employee object
				(existing, replacement) -> existing // Merge function: keep the existing one (first occurrence)
		)).values(); // Get the collection of Employee objects (the values of the Map)

		distinctEmployees.forEach(e -> System.out.println(e.getName() + " - " + e.getEmail()));
		// Output will contain "Kartavya - kp@kp.com" (keeping the first one)
		// and the other unique employees.

	}
}

class Employee {
	String name;
	String email;

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
}
