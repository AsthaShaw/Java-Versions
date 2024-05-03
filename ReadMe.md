Important Features

Java 8- Functional Programming- Lambdas & Streams, Static methods in interface

Java 9- Modularization

Java 10- Local Variable Type Inference

Java 14- Switch Expressions

Java 15- Text Blocks

Java 16- Record Classes

All Java Versions-API Improvements, Garbage Collection Improvements and Performance

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