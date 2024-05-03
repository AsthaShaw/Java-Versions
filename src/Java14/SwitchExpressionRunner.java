package Java14;

public class SwitchExpressionRunner {

    public static void main(String[] args) {



    }


    public static String findDayOfTheWeek(int day){
 //       String dayOfWeek="";
      //Before
//        switch(day){
//            case 0: dayOfWeek="Sunday";break;
//            case 1: dayOfWeek="Monday";break;
//            case 2: dayOfWeek="Tuesday";break;
//            case 3: dayOfWeek="Wednesday";break;
//             default:throw new IllegalArgumentException("Invalid Option"+ day);
//        }


        //After JDK 14
        //In the switch expression you don't have fallthrough and hence no break is needed.
        // Fallthrough is when from case 0 it goes to case 1 and so on in absence of breaks in previous type of switch expression
        String dayOfWeek=switch(day){
            case 0-> {
                System.out.println("Do some complex logic here");
                //return is basically written as yield
                yield "Sunday";
            }
            case 1-> "Monday";
            case 2-> "Tuesday";
            case 3-> "Wednesday";
            default->throw new IllegalArgumentException("Invalid Option"+ day);
        };
       return dayOfWeek;
    }
}
