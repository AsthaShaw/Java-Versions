package java10;

import java.util.ArrayList;
import java.util.List;

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
