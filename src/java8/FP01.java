package java8;

import java.util.List;
import java.util.function.Predicate;

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




}
