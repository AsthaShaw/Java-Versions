package java11;

import java.util.Optional;

public class StringNewApiAddition {

    public static void main(String[] args) {

       //Java 11
        System.out.println((" ").isBlank());
        System.out.println((" LR ").strip().replace(" ", "@"));
        System.out.println((" LR ").stripLeading().replace(" ", "@"));
        System.out.println((" LR ").stripTrailing().replace(" ", "@"));
        //split between different lines
        "Line1\nLine2\nLine3\nLine4".lines().forEach(System.out::println);

        //Java 12

        System.out.println((String) "UPPER".transform(s -> s.substring(2)));

        //Java 15
        System.out.println("My name is %s. My age is %d.".formatted("Ranga", 25));

        //Java 14
        //Explicit error messages like pointing out to the specific variable str or whatever is null and hence
        //easy to debug for developers. As an example:
        String str=null;
        System.out.println(str.isBlank());


    }
}
