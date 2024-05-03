package java11;

import java.util.List;
import java.util.function.Predicate;

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
