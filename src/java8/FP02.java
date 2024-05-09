package java8;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

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
