package java8;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FP03FunctionalInterfaces {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);



        /*
        so the lambda simplifies the below method

        boolean isEven(int x){
        return x%2==0;
        }
         */

        //Predicate, Consumer, Function are all functional interface which means they have just one abstract method

        Predicate<Integer> isEvenPredicate = x -> x % 2 == 0;//Instance of a predicate class created

        Predicate<Integer> isEvenPredicate2= new Predicate<Integer>() {
            @Override
            public boolean test(Integer x) {
                return x%2==0;
            }
        };


        Function<Integer, Integer> squareFunction = x -> x * x;//Instance of function class created

        Function<Integer, Integer> squareFunction2=new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x*x;
            }
        };


        Consumer<Integer> sysOutConsumer = System.out::println;//Instance of consumer class created

        Consumer<Integer> sysOutConsumer2=new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };



        numbers.stream()
                .filter(isEvenPredicate)
                .map(squareFunction)
                .forEach(sysOutConsumer);

        //No input > Return Something
        // Supplier<Integer> randomIntegerSupplier=()->2;
        Supplier<Integer> randomIntegerSupplier=()->{
            Random random=new Random();
            return random.nextInt(1000);
        };
        System.out.println(randomIntegerSupplier.get());

        //Unary operator takes an argument of one type and returns the output of same type

        UnaryOperator<Integer> unaryOperator=(x)->3 * x;

        System.out.println(unaryOperator.apply(10));



        //BiPredicate
        BiPredicate<Integer, String> biPredicate=(number,str)->{
            return number<10 && str.length()>5;
        };

        System.out.println(biPredicate.test(15, "in28minutes"));


        //BiFunction-specify the output type
        BiFunction<Integer, String, String> biFunction=(number, str) ->{
            return number+ " "+str;
        };

        System.out.println(biFunction.apply(15, "in28minutes"));

        //BiConsumer-takes 2 inputs

        BiConsumer<Integer, String> biConsumer=(s1,s2)->{
            System.out.println(s1);
            System.out.println(s2);
        };

        biConsumer.accept(25, "in28minutes" );

        //If you want to use lambdas with primitives then below can be used so you do not need to do autoboxing and unboxing

        //IntBinaryOperator
        //InConsumer
        //IntFunction
        //IntPredicate
        //IntSupplier
        //IntToDoubleFunction
        //IntToLongFunction

    }}
