package java8;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Course{
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", reviewScore=" + reviewScore +
                ", noOfStudents=" + noOfStudents +
                '}';
    }
}
public class FP04CustomClass {

    public static void main(String[] args) {
        List<Course> courses=List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 98, 18000),
                new Course("API", "Microservices", 97, 20000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker","Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000)

        );

        //allMatch, noneMatch, anyMatch

        System.out.println(courses.stream().allMatch(course->course.getReviewScore()>95));

        System.out.println(courses.stream().noneMatch(course->course.getReviewScore()<90));

        System.out.println(courses.stream().anyMatch(course->course.getReviewScore()<90));

        //sorted using comparator
        //comparing by number of students
        Comparator<Course> comparingByNoOfStudentsIncreasing= Comparator.comparing((Course::getNoOfStudents));
        Comparator<Course> comparingByNoOfStudentsDecreasing= Comparator.comparing((Course::getNoOfStudents)).reversed();

        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsIncreasing)
                .collect(Collectors.toList()));

        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsDecreasing)
                .collect(Collectors.toList()));

      //comparing by number of reviews and number of students
        Comparator<Course> comparingByNoOfStudentsAndNoOfReviews= Comparator.comparingInt((Course::getNoOfStudents))
                .thenComparingInt(Course::getReviewScore).reversed();

//you can use comparingInt or thencomparingInt as that is more efficient than comparing as boxing and unboxing is not involved
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                .collect(Collectors.toList()));


        //skip, limit, takeWhile, dropWhile

        //limit-pick up till some results
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .limit(5)
                .collect(Collectors.toList()));

        //skip-skip first few courses
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                .skip(2)
                .collect(Collectors.toList()));


//skip 3 and get rest 5
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNoOfReviews)
                .skip(2)
                .limit(5)
                .collect(Collectors.toList()));

        //takeWhile-loop through till it meets the criteria

        System.out.println(courses.stream().
                takeWhile(course->course.getReviewScore()>=95)
                .collect(Collectors.toList()));


        //dropWhile-opposite of takewhile. It will keep dropping the elements that meet the criteria

        System.out.println(courses.stream().
                dropWhile(course->course.getReviewScore()>=95)
                .collect(Collectors.toList()));


        //top, max, min, findFirst, findAny
//max-returns the last element in the list
        System.out.println(courses.stream()
                .max(comparingByNoOfStudentsAndNoOfReviews));

        //min-returns the first element in the list
        System.out.println(courses.stream()
                .min(comparingByNoOfStudentsAndNoOfReviews));


        //Below will give Optional.empty instead of a null
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()<90)
                .min(comparingByNoOfStudentsAndNoOfReviews));

        //Optional-helps to specify a default value
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()<90)
                .min(comparingByNoOfStudentsAndNoOfReviews)
                .orElse(new Course("Kubernetes", "Cloud", 91, 20000)));

        //findFirst-finds the first element that meets the criteria

        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()<90)
                .findFirst());

        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .findFirst());

        //findAny-returns any of the value from the list and which value that is nondeterministic
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .findAny());


        //sum, average and count
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .sum());

        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>90)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .average());
//count the number of students that meet the criteria
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>95)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .count());
//max
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>95)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .max());

        //min
        System.out.println(courses.stream()
                .filter(course->course.getReviewScore()>95)
                .mapToInt(Course::getNoOfStudents) //you can also use map
                .min());


        //grouping
        System.out.println(
        courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory)));

        //count the number of groups
        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));

        //highest review score from the group
        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.maxBy(Comparator.comparing(Course :: getReviewScore)))));

        //map just with names of the course

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList()))));
    }
}
