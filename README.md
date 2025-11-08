JAVA STREAM API INTERVIEW QUESTIONS

What is the difference between map() and flatMap() in Java Streams?
map() -> One-to-One Transformations.
Transforms each element of the stream into another form.
A stream of the same size, but with transformed elements.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<Integer> nameLengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());

flatMap() -> One-to-Many Transformation
Flattens nested structures (like List<List<T>) into a single stream.
A stream with potentially more elements than the original, depending on how the inner structures are flattened.
List<List<String>> nestedList = Arrays.asList(
    Arrays.asList("a", "b"),
    Arrays.asList("c", "d"),
    Arrays.asList("e", "f")
);
List<String> flatList = nestedList.stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());

What parameter does the filter() method accept in Java Streams?
The filter() method is used to select elements from a stream based on a given condition.
It accepts a predicate (Predicate<T>).
A predicate is a functional interface that represents a boolean-valued function:
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
List<String> filteredNames = names.stream()
    .filter(name -> name.startsWith("A"))
Predicate is a functional interface that represents a boolean-valued function of one argument.
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

What is the purpose of function.identity() in Streams?
Function.identity() serves as a convenient and readable way to represent a function that simply returns its input argument without any modification.
It acts as a "no-operation" function, useful in scenarios where a Function parameter is required by a Stream operation (like map or collect) but no actual transformation of the elements is desired.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
Map<String, String> map = names.stream()
    .collect(Collectors.toMap(
        String::toUpperCase,    // key mapper
        Function.identity()     // value mapper (keep original)

Output: {ALICE=Alice, BOB=Bob, CHARLIE=Charlie}

Find the frequency of words in a string using Streams.
String str = "hello hello hello world world hey world";
String[] strArr = str.split("\\s+");

List<String> words = Arrays.asList(strArr);
Map<String, Long> frequency = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

System.out.println(frequency);

Find the frequency of characters in a string using Streams.
// character frequency in a string
String str = "Hello World";
IntStream in = str.chars();
Map<Character, Long> map = in.mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
System.out.println(map);

// character frequency in a list
List<String> list = Arrays.asList("Hello", "World");
Map<Character, Long> mapList = list.stream()
    .flatMap(s -> s.chars().mapToObj(c -> (char) c))
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
System.out.println(mapList);

Convert a List into a Map using Java 8 Streams.
// character frequency in a string
String str = "Hello World";
IntStream in = str.chars();
Map<Character, Long> map = in.mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
Get distinct employees based on email using Streams.

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

public class GetDistinctEmployeeByEmail {
    public static void main(String[] args) {
        // If name and email are in a nested list
        List<List<String>> employeeList = Arrays.asList(
            Arrays.asList("Kartavya", "kp@kp.com"),
            Arrays.asList("kp", "kp@kp.com"),
            Arrays.asList("Pandey", "pandey@pandey.com"),
            Arrays.asList("Pandey", "kartavya@kartavya.com")
        );

        Collection<List<String>> map = employeeList.stream()
            .collect(Collectors.toMap(
                emp -> emp.get(1), // key = email
                Function.identity(), // value = the whole list
                (existing, replace) -> existing 
   // keep the first occurrence
            )).values();

        map.forEach(emp -> System.out.println("Name: " + emp.get(0) + ", Email: " + emp.get(1)));

        // Using Employee class
        List<Employee> list = Arrays.asList(
            new Employee("Kartavya", "kp@kp.com"),
            new Employee("kp", "kp@kp.com"),
            new Employee("Pandey", "pandey@pandey.com"),
            new Employee("Pandey", "kartavya@kartavya.com")
        );

        Collection<Employee> distinctEmployees = list.stream()
            .collect(Collectors.toMap(
                Employee::getEmail,
                e -> e,
                (existing, replacement) -> existing
            ))
            .values();

        distinctEmployees.forEach(e ->
            System.out.println(e.getName() + " - " + e.getEmail())
        );
    }
}

Split a list of strings into two lists based on string length using Streams.
List<String> names = Arrays.asList("kartavya", "kp", "arun", "sid", "siddharth");
// Split into two lists: length > 3 and length <= 3
Map<Boolean, List<String>> partitioned = names.stream()
            .collect(Collectors.partitioningBy(name -> name.length() > 3));

List<String> longerThan3 = partitioned.get(true);
List<String> shorterOrEqualTo3 = partitioned.get(false);

System.out.println("Length > 3: " + longerThan3);
System.out.println("Length <= 3: " + shorterOrEqualTo3);








Find the k-th most frequent word using Streams.
public static void main(String[] args) {
        List<String> words = Arrays.asList(
            "apple", "banana", "apple", "orange", "banana", "apple", "kiwi", "kiwi", "banana"
        );

        int k = 2; // 2nd most frequent

        // Step 1: Count frequencies
        Set<Entry<String, Long>> frequencySet = words.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet();

        // Step 2: Sort by frequency and get the k-th most frequent
        String kthMostFrequent = frequencySet.stream()
            .sorted((e1, e2) -> {
                int freqCompare = Long.compare(e2.getValue(), e1.getValue());
                return freqCompare != 0 ? freqCompare : e1.getKey().compareTo(e2.getKey()); // tie-breaker
            })
            .skip(k - 1)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);

        System.err.println("The " + k + "-th most frequent word is: " + kthMostFrequent);
}

Return a Map where key is word and value is length of word
List<String> words = Arrays.asList("kartavya", "banana", "kiwi", "arun");
Map<String, Integer> wordLengthMap = words.stream()
            .collect(Collectors.toMap(
                word -> word,
                String::length
));
System.out.println(wordLengthMap);

Difference between Collectors.groupingBy() and Collectors.partitioningBy().
Collectors.groupingBy()
Purpose: Groups elements by a classifier function.
Returns: A Map<K, List<T>> where K is the key returned by the classifier function.
Use Case: When you want to group elements based on a property that can have multiple distinct values.
Map<String, List<Employee>> employeesByDept =
    employees.stream()
   .collect(Collectors.groupingBy(Employee::getDepartment));
Collectors.partitioningBy()
Purpose: Partitions elements into two groups based on a predicate.
Returns: A Map<Boolean, List<T>> with keys true and false.
Use Case: When you want to split elements into two categories (e.g., pass/fail, male/female, etc.).
Map<Boolean, List<Employee>> partitionedBySalary =
    employees.stream()
             .collect(Collectors.partitioningBy(e -> e.getSalary() > 50000));
How do you sort a Map by values using Streams?
To sort a Map by values using Java Streams, you can convert the Map into a stream of its entries, sort them by value, and then collect the result into a new LinkedHashMap to preserve the order.
Map<String, Integer> unsortedMap = Map.of(
    "apple", 50,
    "banana", 20,
    "cherry", 30
);

Map<String, Integer> sortedByValue = unsortedMap.entrySet()
    .stream()
    .sorted(Map.Entry.comparingByValue()) // ascending order
    .collect(Collectors.toMap(
        Map.Entry::getKey,
        Map.Entry::getValue,
        (e1, e2) -> e1,
        LinkedHashMap::new // maintains insertion order
    ));

How do you handle null values in Streams?
Filter out null before processing
List<String> list = Arrays.asList("apple", null, "banana", "apple");

Map<String, Long> frequency = list.stream()
    .filter(Objects::nonNull)
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
Use Optional to safely handle nulls.
Optional.ofNullable(someObject)
    .map(Object::toString)
Provide default values using map
List<String> list = Arrays.asList("apple", null, "banana");

List<String> cleaned = list.stream()
    .map(s -> s == null ? "default" : s)
    .collect(Collectors.toList());


Avoid NullPointerException is groupingBy
Map<String, Long> frequency = list.stream()
    .filter(Objects::nonNull)
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
Defensive Programming: null-safe custom creation
List<String> list = null;

Map<String, Long> frequency = Optional.ofNullable(list)
    .orElse(Collections.emptyList())
    .stream()
    .filter(Objects::nonNull)
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

What are intermediate and terminal operations in Streams?
Intermediate Operations: These are operations that transform a stream into another stream. They are lazy, meaning they don’t process data until a terminal operation is invoked.
Example: .filter(), .map(), .flatMap() etc.
Terminal Operations: These operations trigger the processing of the stream and produce a result or a side-effect. Once a terminal operation is called, the stream is consumed and cannot be reused.
Example: forEach(), collect(), findFirst() etc.


