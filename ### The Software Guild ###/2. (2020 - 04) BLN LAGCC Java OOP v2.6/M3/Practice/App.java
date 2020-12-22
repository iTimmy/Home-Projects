import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main (String [] args) {
        List<Person> people = new ArrayList<>();

        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();
        Person person5 = new Person();

        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);

        people.stream()
            .filter((p) -> p.getAge() >= 18);

        people.stream()
            .map((p) -> p.getAge() >= 18);

        OptionalDouble averageAge = people.stream()
            .mapToInt((p) -> p.getAge()).average();

        people.stream()
            .filter((p) -> p.getAge() >= 18)
            .map((p) -> p.getName());

    // So far we've only looked at intermediate operations. Now we'll now look at our first terminal operation: the collect method.
    // The filter and map methods both return a stream, but we can't really do much with a stream because it's a temporary thing. We need to change the stream back into a List to pass it around and use it in our code properly. The collect method lets us do that.
    // When we use the collect method, we pass into it a Collector type, which we can generate using static methods in the Collectors class.

    // First let's look at collecting our stream into a List:

        List<Person> overEighteen = people.stream()
            .filter((p) -> p.getAge() >= 18)
            .collect(Collectors.toList());

    // We first create the stream with the stream method and then use filter to keep only Person objects over the age of 18 in the stream. We then finally use collect to put them into a List. The Collectors.toList() call specifically puts the remaining things in the Stream into a List.
    // An important thing to note is that when we assign the filtered stream back into a List, the original List people will not change.
    // The act of streaming a List will not change the original List.
    // Another way we might want to collect our Stream is into a Map.

    // Let's say we want to organize our Person List into sublists based on age:
        Map<Integer, List<Person>> peopleAges = people.stream()
            .collect(Collectors.groupingBy((p) -> p.getAge()));
    // In the call Collectors.groupingBy((p) -> p.getAge()), we specify what we want the key for the Map to be and how we are organizing our List: in this case, by age. So for each distinct age, we will have a List of Person objects.

    // Another terminal operation we will use is the forEach method.
    // This method doesn't actually return anything. Instead, it allows us to run code against each and every object in the Stream. The lambda we pass into this method can also be called a Consumer.
    // One use for the forEach method is if we want to print out everything in the List:
        people.stream()
            .forEach((p) -> System.out.println(p.getName() + " : " + p.getAge()));
    // We start the Stream as usual with the stream method and then call the forEach on the Stream. Inside the forEach, our lambda takes in each object and puts it into a println.
    // Let's say we want to print the name on one line and age on the next; we can make a multi-line lambda using curly braces:

        people.stream().forEach((p) -> {
            System.out.println(p.getName());
            System.out.println(p.getAge());
        });
        // When we use a multi-line lambda, we need to include a semicolon at the end of lines. 
        // If we use a multi-line lambda in a filter or map, we also need to make sure to return a value from the lambda. 
        // The return is assumed on the single-line lambdas, but is required in multi-line lambdas.
    }
}