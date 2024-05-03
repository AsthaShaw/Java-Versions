package Java14;

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
