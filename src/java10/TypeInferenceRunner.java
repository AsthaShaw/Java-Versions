package java10;

import java.util.List;

public class TypeInferenceRunner {


    public static void main(String[] args) {

        List<String> names1=List.of("Ranga", "Ravi");
        List<String> names2=List.of("John", "Adam");

        //List<List<String>> names= List.of(names1, names2);
        //Now the above is getting complex and hence Java 10 introduced TypeInference so the above statement gets replaced as follows
        //So Java figures out the type and we do not need to bother about it. Java compiler infers the type of the variable at compile time.
        //You can also use var in loops and can also final before var too.
        //Cannot assign var to null. Type Inference is not applicable to Member Variables

        var names=List.of(names1,names2);
        names.stream().forEach(System.out::println);

        for(var name:names1){
            System.out.println(name);
        }
        //var is not a keyword
       var var="no";

    }
}
