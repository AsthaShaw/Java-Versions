Important Features

Java 8- Functional Programming- Lambdas & Streams, Static methods in interface

Java 9- Modularization

Java 10- Local Variable Type Inference

Java 14- Switch Expressions

Java 15- Text Blocks

Java 16- Record Classes

All Java Versions-API Improvements, Garbage Collection Improvements and Performance

Java 8

Implementation of

- forEach
- filter
- contains
- Method Reference
- map

```jsx

public class FP01 {

    public static void main(String[] args) {
        printAllNumbersInList(List.of(12,9,13,4,6,2,4, 12,15));

        List<String> courses=List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        printCourses(courses);
    }

    private static void printCourses(List<String> courses) {

        System.out.println("------PRINT COURSES WITH SPRING IN THAT------");
        courses.stream().filter(course->course.contains("Spring")).forEach(System.out::println);

        System.out.println("------PRINT COURSE NAMES WITH ATLEAST 4 LETTERS-----");
        courses.stream().filter(course->course.length()>=4).forEach(System.out::println);

        System.out.println("------PRINT COURSE NAMES WITH NUMBER OF CHARACTERS-----");
        courses.stream().map(course->course+" "+course.length()).forEach(System.out::println);

    }

    private static boolean isEven(int number){
        return number%2==0;
    }

    private static void printAllNumbersInList(List<Integer> numbers) {

        //Traditional way to loop
        for(int number:numbers){
            System.out.println(number);
        }

        //Functional way-focuses on what needs to be done
        numbers.forEach(number-> System.out.println(number));

        //Method reference-calls a method in a class
        System.out.println("-----FOR EACH------");
        numbers.forEach(System.out::println);

        //Print even numbers in the list
        //Lambda expression is nothing but a method, basically a nameless method
        System.out.println("-----PRINT EVEN NUMBERS------");
        numbers.stream().filter(x->x%2==0).forEach(System.out::println);

        System.out.println("----PRINT EVEN NUMBERS ANOTHER WAY-----");
        numbers.stream().filter(FP01::isEven).forEach(System.out::println);

        System.out.println("-----PRINT SQUARES OF EVEN NUMBERS------");
        numbers.stream()
                .filter(x->x%2==0)
                .map(x->x*x)
                .forEach(System.out::println);

        System.out.println("-----PRINT ODD NUMBERS------");
        numbers.stream().filter(x->x%2!=0).forEach(System.out::println);

        System.out.println("-----PRINT CUBES OF ODD NUMBERS------");
        numbers.stream().filter(x->x%2!=0).map(x->x*x*x).forEach(System.out::println);

        System.out.println("----PRINT ODD NUMBERS ANOTHER WAY-----");
        numbers.stream().filter(Predicate.not(FP01::isEven)).forEach(System.out::println);
    }

```

Implementation of

- collect
- reduce
- distinct
- sorted
- comparator

```jsx

public class FP02 {

    public static void main(String[] args) {

        List<Integer> numbers=List.of(12,9,13, 4, 6, 2, 4, 12, 15);
        //list of numbers combine them into a single result-reducer
        System.out.println(addListFunctional(numbers));
        System.out.println("Square And Sum of Numbers::"+squareAndSum(numbers));
        System.out.println(findSumOfOddNumbers(numbers));
        distinct(numbers);
        sorted(numbers);

        List<String> courses=List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        reverseSorting(courses);

        //Adding a list of numbers to another datastructures-collect
        List<Integer> doubledNumbers=doubleList(numbers);
        System.out.println(doubledNumbers);

        //Exercises of collectors
        List<Integer> evenNumberList=evenNumberCollect(numbers);
        System.out.println(evenNumberList);

        List<Integer> lengthOfCourseTitles=listOfCourseTitleLengthCollect(courses);
        System.out.println(lengthOfCourseTitles);

    }

    private static List<Integer> listOfCourseTitleLengthCollect(List<String> courses) {
        return courses.stream().map(course->course.length()).collect(Collectors.toList());
    }

    private static List<Integer> evenNumberCollect(List<Integer> numbers) {
        return numbers.stream().filter(number->number%2==0).collect(Collectors.toList());
    }

    private static List<Integer> doubleList(List<Integer> numbers) {

        return numbers.stream().map(number->number*number).collect(Collectors.toList());
    }

    private static void reverseSorting(List<String> courses) {
        //sorting
        courses.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);

        //reverse sorting
        courses.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        //define your own comparator eg: compare by length of string
        courses.stream().sorted(Comparator.comparing(str->str.length()
        )).forEach(System.out::println);
    }

    private static int addListFunctional(List<Integer> numbers){
      // return numbers.stream().reduce(0, FP02::sum);
      //  return numbers.stream().reduce(0,(x,y)->x+y);
        return numbers.stream().reduce(0,Integer::sum);

       // return numbers.stream().reduce(Integer.MIN_VALUE,(x,y) -> x>y? x:y);

        //Square
    }

    private static int squareAndSum(List<Integer> numbers){
        BinaryOperator<Integer> sumBinaryOperator = Integer::sum;

        //Functional interfaces are Anonymous classes
        //Function descriptor

        BinaryOperator<Integer> sumBinaryOperator2=new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };

        return numbers.stream().map(number->number* number).reduce(0, sumBinaryOperator );
    }

    private static int findSumOfOddNumbers(List<Integer> numbers){
        return numbers.stream().filter(number->number%2!=0).reduce(0, Integer::sum);
    }

    private static int sum(int a, int b){
        System.out.println(a+" "+b);
        //a is the aggregate and b is the nextNumber, we are doing aggregation here. That's how a reduce number works
        return a+b;
    }

    private static void distinct(List<Integer> numbers){
        //distinct()
         numbers.stream().distinct().forEach(System.out::println);

    }

    private static void sorted(List<Integer> numbers){
        //sorted
        //numbers.stream().sorted().forEach(System.out::println);

        //distinct and sorted
        numbers.stream()
                .distinct() //Stream<T> Intermediate-operates on a stream and returns a stream
                .sorted() //Stream<T>
                .forEach(System.out::println);// void //Terminal operation-converts a stream into something else other than stream
    }

}
```

Functional Interfaces-These interfaces just have one abstract method

Implementation of Functional Interfaces

1. **`Predicate<T>`**
- **A predicate** is a function that evaluates whether a given condition is **true** or **false**. Essentially, it’s a function that takes a set of parameters and returns a boolean value.
- Represents a boolean-valued function (predicate) of one argument of type **`T`**.
- Used for filtering or testing conditions.
- Example: Checking if a number is even using **`Predicate<Integer>`**.

1. **`Function<T, R>`**:
   - Represents a function that accepts one argument of type **`T`** and produces a result of type **`R`**.
   - Commonly used for mapping or transforming data.
   - Example: Converting a list of strings to uppercase using **`Function<String, String>`**.

2. **`Consumer<T>`**:
   - Represents an operation that accepts a single input argument of type **`T`** and returns no result.
   - Used for side-effect operations (e.g., printing, logging).
   - Example: Printing each element of a list using **`Consumer<String>`**.


```jsx

public class FP03FunctionalInterfaces {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        /*
        so the lambda simplifies the below method

        boolean isEven(int x){
        return x%2==0;
        }
         */

        //Predicate, Consumer, Function are all functional interface which means they have just one abstract method

        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;//Instance of a predicate class created

        Predicate<Integer> isEvenPredicate2= new Predicate<Integer>() {
            @Override
            public boolean test(Integer x) {
                return x%2==0;
            }
        };

        Function<Integer, Integer> squareFunction = x -> x * x;//Instance of function class created

        Function<Integer, Integer> squareFunction2=new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x*x;
            }
        };

        Consumer<Integer> sysOutConsumer = System.out::println;//Instance of consumer class created

        Consumer<Integer> sysOutConsumer2=new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };

        numbers.stream()
                .filter(isEvenPredicate)
                .map(squareFunction)
                .forEach(sysOutConsumer);
```

1. BiPredicate

```jsx
  BiPredicate<Integer, String> biPredicate=(number,str)->{
            return number<10 && str.length()>5;
        };

        System.out.println(biPredicate.test(15, "in28minutes"));
```

1. BiConsumer-Takes 2 inputs

```jsx
  BiConsumer<Integer, String> biConsumer=(s1,s2)->{
            System.out.println(s1);
            System.out.println(s2);
        };

        biConsumer.accept(25, "in28minutes" );
```

1. BiFunction-Specify the output type

```jsx
 BiFunction<Integer, String, String> biFunction=(number, str) ->{
            return number+ " "+str;
        };

        System.out.println(biFunction.apply(15, "in28minutes"));
```

1. Supplier-
- represents a supplier of a value of type **`T`**.
- Used for lazy evaluation or generating values.
- Example: Generating random numbers using **`Supplier<Double>`**

```jsx

  Supplier<Integer> randomIntegerSupplier=()->{
            Random random=new Random();
            return random.nextInt(1000);
        };
        System.out.println(randomIntegerSupplier.get());
```

- UnaryOperator-`Unary operator takes an argument of one type and returns the output of same type`

```jsx

UnaryOperator<Integer> unaryOperator=(x)->3 * x;

        System.out.println(unaryOperator.apply(10));
```

`If you want to use lambdas with primitives then below can be used so you do not need to do autoboxing and unboxing`

- IntBinaryOperator
- IntConsumer
- IntFunction
- IntPredicate
- IntSupplier
- IntToDoubleFunction
- IntToLongFunction

Implementation of Behaviour Parametrization-When you pass behaviour as a function argument

```jsx

public class FP03BehaviourParameterization {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

       // Predicate<Integer> evenPredicate = x -> x % 2 == 0;

        filterAndPrint(numbers, x -> x % 2 == 0);

      //  Predicate<Integer> oddPredicate = x -> x % 2 != 0;

        // Below we are passing the behaviour as a parameter
        filterAndPrint(numbers, x -> x % 2 != 0);

       // Function<Integer, Integer> squareOfIntegers = number -> number * number;
       
       List<Integer> doubledNumbers = mapAndcollectToList(numbers,number -> number * number);
       List<Integer> cubedNumbers = mapAndcollectToList(numbers,number -> number * number * number);

        System.out.println(doubledNumbers);
        System.out.println(cubedNumbers);

    }

    private static List<Integer> mapAndcollectToList(List<Integer> numbers, Function<Integer, Integer> mappingFunction) {
       return numbers.stream().map(mappingFunction).collect(Collectors.toList());
    }

    private static void filterAndPrint(List<Integer> numbers, Predicate<Integer> oddPredicate) {
        numbers.stream()
                .filter(oddPredicate)
                .forEach(System.out::println);
    }
}

```

Implementation of Custom Classes

- allMatch
- noneMatch
- anyMatch

```jsx

public class FP04CustomClass {

    public static void main(String[] args) {
        List<Course> courses=List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 98, 18000),
                new Course("API", "Microservices", 97, 20000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker","Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000)

        );

        //allMatch, noneMatch, anyMatch

        System.out.println(courses.stream().allMatch(course->course.getReviewScore()>95));

        System.out.println(courses.stream().noneMatch(course->course.getReviewScore()<90));

        System.out.println(courses.stream().anyMatch(course->course.getReviewScore()<90));

        
```

- Comparator Usage

```jsx

 //sorted using comparator
        //comparing by number of students
        Comparator<Course> comparingByNoOfStudentsIncreasing= Comparator.comparing((Course::getNoOfStudents));
        Comparator<Course> comparingByNoOfStudentsDecreasing= Comparator.comparing((Course::getNoOfStudents)).reversed();

        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsIncreasing)
                .collect(Collectors.toList()));

        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsDecreasing)
                .collect(Collectors.toList()));

      //comparing by number of reviews and number of students
        Comparator<Course> comparingByNoOfStudentsAndNoOfReviews= Comparator.comparingInt((Course::getNoOfStudents))
                .thenComparingInt(Course::getReviewScore).reversed();

//you can use comparingInt or thencomparingInt as that is more efficient than comparing as boxing and unboxing is not involved
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                .collect(Collectors.toList()));

```

Implementation of further custom classes

- skip
- limit
- takeWhile
- dropWhile

```jsx

 //limit-pick up till some results
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .limit(5)
                .collect(Collectors.toList()));

        //skip-skip first few courses
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                .skip(2)
                .collect(Collectors.toList()));

//skip 3 and get rest 5
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                .skip(2)
                .limit(5)
                .collect(Collectors.toList()));

        //takeWhile-loop through till it meets the criteria

        System.out.println(courses.stream().
                takeWhile(course->course.getReviewScore()>=95)
                .collect(Collectors.toList()));

        //dropWhile-opposite of takewhile. It will keep dropping the elements that meet the criteria

        System.out.println(courses.stream().
                dropWhile(course->course.getReviewScore()>=95)
                .collect(Collectors.toList()));
```

Implementation of further custom classes

- top
- max
- min
- findFirst
- findAny

```jsx

//max-returns the last element in the list
        System.out.println(courses.stream()
                .max(comparingByNoOfStudentsAndNoOfReviews));

        //min-returns the first element in the list
        System.out.println(courses.stream()
                .min(comparingByNoOfStudentsAndNoOfReviews));

        //Below will give Optional.empty instead of a null
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()<90)
                .min(comparingByNoOfStudentsAndNoOfReviews));

        //Optional-helps to specify a default value
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()<90)
                .min(comparingByNoOfStudentsAndNoOfReviews)
                .orElse(new Course("Kubernetes", "Cloud", 91, 20000)));

        //findFirst-finds the first element that meets the criteria

        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()<90)
                .findFirst());

        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .findFirst());

        //findAny-returns any of the value from the list and which value that is nondeterministic
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .findAny());
```

Implementation of further custom classes

- sum
- average
- count

```jsx

 //sum, average and count
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .sum());

        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .average());
//count the number of students that meet the criteria
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>95)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .count());
//max
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>95)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .max());

        //min
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>95)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .min());
```

Implementation of grouping in streams

```jsx

 //grouping
        System.out.println(
        courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory)));

        //count the number of groups
        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));

        //highest review score from the group
        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.maxBy(Comparator.comparing(Course :: getReviewScore)))));

        //map just with names of the course

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList()))));

```

Implementation of

- Stream.of
- Streams with primitive values using Arrays.stream()
- Easier way to create stream

```jsx

Stream.of(12, 9, 13, 4, 5).count();

        Stream.of(12, 9, 13, 4, 5).reduce(0, Integer::sum);

        // The above are referencePipeline which means they use wrapper classes

        //streams with primitive values below

        int[] numberArray={12, 9, 13, 4, 5};
        Arrays.stream(numberArray); //this would be a IntPipeline basically primitive values

        Arrays.stream(numberArray).sum();
        Arrays.stream(numberArray).average();
        Arrays.stream(numberArray).min();
        Arrays.stream(numberArray).max();

        //Easier way to create stream

        IntStream.range(1,10).sum();  //10 not included so if you want to include 10 you can do that using rangeClosed

        IntStream.iterate(1, e->e +2).limit(10).sum();

        IntStream.iterate(1, e->e +2).limit(10).peek(System.out::println).sum();
```

Implementation of some method reference usage

```jsx
 public class PuzzlesFP {

    public static void main(String[] args) {
        List<String> courses=List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        //Method references work for both static and not static methods
        courses.stream()
            //    .map(str->str.toUpperCase())
                .map(String::toUpperCase)
                .forEach(System.out::println);

        Supplier<String> supplier=()->new String();
        //The above can be written as a constructor reference
        Supplier<String> supplier2=String::new;

    }
}
```


Java 9

`Static Map in Java-A static map is a map which is defined as static. It means that the map becomes a class member and can be easily used using class.`

`Java 9 feature – Map.of() method//In Java 9, Map.of() was introduced which is a convenient way to create instances of Map interface. It can hold up to 10 key-value pairs.`

```jsx
private static Map<String, String> *map* = 
Map.*of*("a", "candy","2", "Geek", "3", "GeeksForGeeks");
```

### Java 10

1. **copyOf method that was introduced for List, Set and map**

Returns an unmodifiable List containing the elements of the given Collection, in its iteration order. The given Collection must not be null, and it must not contain any null elements. If the given Collection is subsequently modified, the returned List will not reflect such modifications.

```jsx

public class CopyOf {

    //List, set and Map -CopyOfMethod
    public static void main(String[] args) {
        List<String> names=new ArrayList<>();
        names.add("Ranga");
        names.add("Ravi");
        names.add("John");
        List<String> copyOfNames=List.copyOf(names);

        doNotChange(copyOfNames);
        //If you want that in the other method the list of names can't be changed then you can use copyOf method
    }

    public static void doNotChange(List<String> names){
         names.add("Astha");//this line will throw an error as you are adding to an immutable list now
    }
}

```

1. **TypeInference-**`Java compiler infers the type of the variable at compile time using var.You can also use var in loops and can also have final before var too. Cannot assign var to null. Type Inference is not applicable to Member Variables`

```jsx

 List<String> names1=List.of("Ranga", "Ravi");
        List<String> names2=List.of("John", "Adam");

        //List<List<String>> names= List.of(names1, names2);
        //Now the above is getting complex and hence Java 10 introduced TypeInference so the above statement gets replaced as follows
        //So Java figures out the type and we do not need to bother about it. 

        var names=List.of(names1,names2);
        names.stream().forEach(System.out::println);

        for(var name:names1){
            System.out.println(name);
        }
        //var is not a keyword
       var var="no";

```

Java 11

1. ReadString and WriteString makes reading and writing strings so easy. By default uses UTF-8 encoding

```jsx

public class FileReadWriteRunner {

    public static void main(String[] args) {

        //readString and writeString makes reading and writing strings so easy
        Path path= Paths.get("src/java11/sample.txt");
        try {
            String fileContent= Files.readString(path);
            System.out.println(fileContent);

            String newFileContent=fileContent.replace("Line","Lines");
            Path newFilePath=Paths.get("src/java11/sample-new.txt");
            Files.writeString(newFilePath, newFileContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
```

1. Predicate.not method-Returns a predicate that is the negation of the supplied predicate. This is accomplished by returning result of the calling target. negate().

```jsx

public class PredicateNotRunner {

    public static boolean isEven(Integer number){
        return number%2==0;
    }

    public static void main(String[] args) {
         List<Integer> numbers= List.of(3,4,5,67,89,88);
         //Define a predicate of even numbers
         Predicate<Integer> evenNumberPredicate= number->number%2==0;
         numbers.stream().filter(evenNumberPredicate).forEach(System.out::println);

         //odd numbers
        numbers.stream().filter(evenNumberPredicate.negate()).forEach(System.out::println);

        //using method reference
        numbers.stream().filter(PredicateNotRunner::isEven).forEach(System.out::println);
        //But there is no negate method you can use here for printing odd numbers
        // and hence the predicate.not was introduced
       numbers.stream().filter(Predicate.not(PredicateNotRunner::isEven)).forEach(System.out::println);

    }
}

```

3. String API changes

   a) isBlank()-Returns: true if the string is empty or contains only white space codepoints, otherwise false

   b) strip()- Returns a string whose value is this string, with all leading and trailing white space removed.
   If this String object represents an empty string, or if all code points in this string are white space, then an empty string is returned.

   c) stripLeading()-Returns a string whose value is this string, with all leading white space removed. If this String object represents an empty string, or if all code points in this string are white space, then an empty string is returned.

   d)stripTrailing()-Returns a string whose value is this string, with all trailing white space removed. If this String object represents an empty string, or if all characters in this string are white space, then an empty string is returned.

   e)lines()-Returns a stream of lines extracted from this string, separated by line terminators.
   A line terminator is one of the following: a line feed character "\n" (U+000A), a carriage return character "\r" (U+000D), or a carriage return followed immediately by a line feed "\r\n" (U+000D U+000A).

```jsx

public class StringNewApiAddition {

    public static void main(String[] args) {
       //Java 11
        System.out.println((" ").isBlank());
        System.out.println((" LR ").strip().replace(" ", "@"));
        System.out.println((" LR ").stripLeading().replace(" ", "@"));
        System.out.println((" LR ").stripTrailing().replace(" ", "@"));
        //split between different lines
        "Line1\nLine2\nLine3\nLine4".lines().forEach(System.out::println);
        }
        }
```

### Java 12

1. String API changes- transform method-This method allows the application of a function to this string. The function should expect a single String argument and produce an R result

```jsx

System.out.println((String) "UPPER".transform(s -> s.substring(2)));
```

### Java 14

1. String API changes- `Explicit error messages like pointing out to the specific variable str or whatever is null and hence//easy to debug for developers. As an example:`

```jsx

String str=null;System.*out*.println(str.isBlank());

Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.isBlank()" because "str" is null
	at java11.StringNewApiAddition.main(StringNewApiAddition.java:27)
	
```

1. Switch Expressions Before and After JDK14- In the recent switch expression you don’t have fallthrough and hence no break is needed. `Fallthrough is when from case 0 it goes to case 1 and so on in absense of breaks in previous type of switch expression`

```jsx
public class SwitchExpressionRunner {

    public static String findDayOfTheWeek(int day){
 //       String dayOfWeek="";
      //Before
//        switch(day){
//            case 0: dayOfWeek="Sunday";break;
//            case 1: dayOfWeek="Monday";break;
//            case 2: dayOfWeek="Tuesday";break;
//            case 3: dayOfWeek="Wednesday";break;
//             default:throw new IllegalArgumentException("Invalid Option"+ day);
//        }

        //After JDK 14
        //In the switch expression you don't have fallthrough and hence no break is needed.
        // Fallthrough is when from case 0 it goes to case 1 and so on in absense of breaks in previous type of switch expression
        String dayOfWeek=switch(day){
            case 0-> {
                System.out.println("Do some complex logic here");
                //return is basically written as yield
                yield "Sunday";
            }
            case 1-> "Monday";
            case 2-> "Tuesday";
            case 3-> "Wednesday";
            default->throw new IllegalArgumentException("Invalid Option"+ day);
        };
       return dayOfWeek;
    }
}

```

1. Records- Eliminate Verbosity in creating Java Beans. The accessor methods, constructor, equals, hashcode and toString are automatically created. You can create custom implementations if you would want.
- `You can add static fields, static initializers and static methods` `but you cannot add instance variables or instance initializers`.
- `Compact Constructor-refining the above custom constructor further as lot of boiler plate code//Compact constructors only allowed in records and not in normal java classes`

```jsx

public class RecordsRunner {

    //record Person(String name, String email, String phoneNumber){}
    //adding custom implementation by changing the constructor
    record Person(String name, String email, String phoneNumber){
        static int value=0;
//        Person(String name, String email, String phoneNumber) {
//            if (name == null)
//                throw new IllegalArgumentException("name is null");
//
//            this.name = name;
//            this.email = email;
//            this.phoneNumber = phoneNumber;
//        }

        //Compact Constructor-refining the above custom constructor further as lot of boiler plate code
        //Compact constructors only allowed in records and not in normal java classes
        //You can add static fields, static initializers and static methods
        // but you cannot add instance variables or instance initializers

        Person{
            if (name == null)
                throw new IllegalArgumentException("name is null");

        }

        //if you are not happy with the default accessor method then you can custom it like below

        public String name(){
            System.out.println("Do something");
            return name;
        }

    }
    public static void main(String[] args) {

        Person person=new Person("Ranga","ranga@in28minutes.com", "123-456-7890");
        Person person1=new Person("Ranga", "ranga@in28minutes.com", "123-456-7890");
        Person person2=new Person("Ranga1", "ranga@in28minutes.com", "123-456-7890");
        System.out.println(person1);
        //equals, hashcode and accessor(example-person.name()) methods are by default defined
        System.out.println(person.equals(person1));
        System.out.println(person.equals(person2));
        System.out.println(person1.name());
        System.out.println(person1.name);//why records made variable names public???

    }
}

```

### Java 15

1. String API changes-formatted-Formats using this string as the format string, and the supplied arguments.

```jsx

 System.out.println("My name is %s. My age is %d.".formatted("Ranga", 25));
```

1. Text Blocks-Used to simplify complex Text Strings. Imp-

`3 quotes and then a new line and then start writing. You cannot have a text block on a single line like // """ abcdefg""".`

`At the end if you need a new line then after the end of statement next line will have 3 quotes else same line can have quotes. Specific formatting is retained.Trailing whitespace is stripped of. All operations on strings can be applied on text blocks.`

```jsx

    public static void main(String[] args) {
        System.out.println("\"FirstLine\"\n Second Line \n Third Line");

        //after intro of text blocks
        System.out.println("""
                "First Line"
                 Second Line
                 Third Line """);

      
        String str= """
                Line1:%s
                "Line2":%s
                Line3
                """.formatted("SomeValue", "Some Other Value");

        System.out.println(str);
    }
```

# Important Interview Topics
### Sealed classes

- If a parent is sealed class then the child which is permitted has to be a sealed or final class or non-sealed

In summary:

- **Regular (Non-Sealed) Classes**: Open for extension by any class.
- **Sealed Classes**: Specify a closed set of permitted subclasses.
- **Non-Sealed Classes**: Open for extension but can break the seal for further subclasses.

### Comparable vs Comparator

https://www.youtube.com/watch?v=ZA2oNhtNk3w

- **`Comparable`**: Natural ordering within the class. Implement it when the sorting behavior is intrinsic to the class.

        Use **`Comparable`** when you want to establish a default sorting behavior for your class.

        Example: Suppose you have a **`Person`** class, and you want to sort a list of **`Person`** objects by their age. Implement **`Comparable<Person>`** to define the natural order based on age.

- **`Comparator`**: External ordering behavior. Use it when you need custom sorting criteria or want to override the default ordering.

Use **`Comparator`** when you need to:

- Sort objects based on different attributes (e.g., sort **`Person`** objects by name, age, or salary).
- Override the default ordering behavior provided by **`Comparable`**.

Example: Suppose you want to sort a list of **`Product`** objects by their price. Create a **`PriceComparator`** that compares products based on their prices.


### What is Garbage Collection ?

The process of automatically reclaiming unused memory by destroying unused objects.

### What are memory leaks?

Forgetting to destroy useless objects will eventually lead to what we call as “memory leaks” and that was very common in languages like C and C++, the programmer is responsible for both the creation and destruction of objects. So after a certain point memory won’t be available anymore to create new objects, and the entire application will terminate due to OutOfMemoryErrors

In Java, Garbage collection happens automatically during the lifetime of a program, eliminating the need to de-allocate memory and therefore avoiding memory leaks.

### When are objects sweeped through Garbage Collection?

- Out of Scope- When exits the method
- Having no reference-when the reference points to some other object
- Referencing null-when the reference is set to null
- By using anonymous object-myMethod(new BankAccount());

### Basic Explanation - how garbage collector works in the background

Young Generation Heap-All new object gets created here. Then Java at some point realises that it is starting to get somewhat full and applies mark and sweep, which means that java will check each of these items and see if there any references to them and then start marking them. Marks the one which has references and then does the sweep up of the unmarked ones to free more memory spaces for new objects to be created. Also Garbage Collector checks the time the objects are referenced for and if they are referenced for a longer time then they are moved from young to old generation of heap and this way is effficient as java is going to sweep and mark in the old generation way too slowly than in Young Generation because Old Generation has objects that have survived the previous rounds of Garbage Collection.

1. Marking objects as Alive
2. Sweeping Dead objects
3. Compact Remaining objects- so that the objects are in contiguous blocks at the start of the heap. But this can be inefficient use of time and hence Generational Garbage Collection came into picture