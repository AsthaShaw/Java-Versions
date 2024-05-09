package java8;

import java.util.List;
import java.util.function.Supplier;

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
