package interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparableVSComparator {

    public static void main(String[] args) {

 // Sorting based on the values
        List<Integer> nums=new ArrayList<>();
        nums.add(10);
        nums.add(5);
        nums.add(11);
        nums.add(7);
        nums.add(1);

        Collections.sort(nums);

        System.out.println(nums);

// Sorting based on the last digit-for this you can use comparator
        List<Integer> nums1=new ArrayList<>();
        nums1.add(49);
        nums1.add(51);
        nums1.add(43);
        nums1.add(62);
        nums1.add(49);
        nums1.add(87);

        //Comparator is an interface using which you can use your own logic for sorting
        Comparator<Integer> com=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 % 10 > o2 % 10) {
                    return 1;
                } else {
                    return -1;
                }

            }
        };

        Collections.sort(nums1,com);
        System.out.println(nums1);

       //sort the values based on age
        List<Student> students=new ArrayList<>();
        students.add(new Student(21, "Navin"));
        students.add(new Student(12, "Biman"));
        students.add(new Student(46, "Krishna"));
        students.add(new Student(20, "Vinod"));


        for(Student s:students){
            System.out.println(s);
        }
        //Not accepting
       // Collections.sort(students);
        Comparator<Student> comStudent=new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.age > o2.age) {
                    return 1;
                } else {
                    return -1;
                }

            }
        };

        Collections.sort(students,comStudent);
        System.out.println(students);

        //Not accepting as student is not implementing Comparable like Integer class does. So we need to make Student implement comparable if we want to proceed natural sorting way
        // Collections.sort(students);

        //So after implementing comparable in student try below
        System.out.println("----AFTER COMPARABLE-----");
        Collections.sort(students);
        System.out.println(students);
    }
}

//class Student{
//
//    int age;
//    String name;
//
//    public Student(int age, String name){
//        this.age=age;
//        this.name=name;
//    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "age=" + age +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}

//Comparable is when you want to give the power to the class to compare its objects naturally
class Student implements Comparable<Student>{

    int age;
    String name;

    public Student(int age, String name){
        this.age=age;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student that) {
//        if (this.age > that.age) {
//            return 1;
//        } else {
//            return -1;
//        }
        return this.age>that.age?1:-1;
    }
}