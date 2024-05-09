package java8;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FP03BehaviourParameterization {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

       // Predicate<Integer> evenPredicate = x -> x % 2 == 0;

        filterAndPrint(numbers, x -> x % 2 == 0);

      //  Predicate<Integer> oddPredicate = x -> x % 2 != 0;

        // Below we are passing the behaviour as a parameter
        filterAndPrint(numbers, x -> x % 2 != 0);


       // Function<Integer, Integer> squareOfIntegers = number -> number * number;
       //
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
