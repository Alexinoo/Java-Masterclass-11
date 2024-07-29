package streams.part1_streams_intro;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * Streams
 *
 * - Introduced in Java 8
 * - You might think streams as IO stream, Input and Output stream , but those aren't the type of streams we'll looking
 *   at here
 * - The streams in this context are completely unrelated to input and output
 * - We refer to the stream here as a sequence of computations
 *      - a set of computational steps chained together
 *      - we can chain, Predicate<T> , Function<T,R> and Consumer<T> and do something similar with streams
 *
 * Example
 *  - Create a List<Strings> : someBingoNumbers , convert it into an Arrays, more specifically, to a list of String
 *    obj(s)
 *      - We've used Arrays.asList() to populate the List
 *
 * - Suppose we want to print someBingoNumbers that starts with letter "G"
 *      - we can use a for loop to iterate through the list and print those strings that starts with a "G"
 *      - use forEach iterator which takes a consumer
 *
 * - Next
 *      - add "g64" with lowercase "g" to the list
 * - The print-out doesn't include "g64" because our test condition is looking for a string that starts with capital
 *  "G"
 *      - convert numbers to uppercase first to include "g64"
 *
 * - Suppose we want to sort the string and still print the strings that starts with a "G"
 *      - we can pass each item that pass the condition to a new list and once we have all the list, we can sort
 *        the new list
 *      - we have an issue here because, we're using forEach which operates on each element at a time
 * - Can we declare the list before the forEach call and use it with a lambda expression argument ?
 *      - Let's try that
 *      - Create a new List : List<String> gNumbers
 *          - add strings that starts with a "G" to gNumbers
 *
 * - Note that intelliJ is not complaining when we use gNumbers inside the forEach lambda expression
 * - Remember the scoping rules when using lambda's
 *      - the scope of this lambda is within the scope of the main body
 *      - we can use variables declared in main, in lambda expressions as long as those variables are effectively final
 *      - we're changing the contents of our gNumbers list, but the object reference stored in the gNumbers variable
 *        doesn't change, and so gNumbers is effectively final and hence we can use it inside lambda
 *
 * Next,
 *  - sort the contents of gNumbers
 *
 * Next
 *  - call forEach and print them out
 *
 * Note
 *  - To create a new list ,  gather all the gNumbers , sort and print them we're using 4 statements
 *
 * - We can do all that with 1 line of statement and that's where streams comes in
 *      - Let's restructure the code to use stream instead and comment out on the code before
 *
 * Output
 *  - This works with all the Strings now printed in uppercase and sorted
 *
 * Discussion
 *  - The first method we call is stream()
 *      - Oracle defines stream as a sequence of elements supporting sequential and parallel aggregate operations
 *      - Means that a stream is a set of objects references
 *      - stream() added in Collections class in Java 8 creates a stream from a collection
 *      - Each object referenced in the stream, corresponds to an object in the collection, and the ordering of the
 *        object reference matches the ordering of the collection
 *
 *  - When we want to use a stream that uses a collection, a source, as its source, the stream(), will be the first
 *    call we make
 *      - our collection in this case is "someBingoNumbers" and won't be changed when we work with streams
 *  - Any stream operations that we use have to meet 2 requirements
 *      - Non-interfering (don't change the stream source in any way)
 *      - Stateless - result of an operation can't depend on any state outside the operation)
 *          - can't depend on variable values from a previous step
 *          - each operation is an independent step operating on a stream argument
 *
 * Discussion
 *  - The result of the stream() call is a Stream<String> which is a stream that contains all the items in the
 *      someBingoNumbers list, in the same order that they occur in the list
 *  - Then we call map() , which takes String::toUpperCase , also known as method reference
 *      - we can use them since all a lambda does is call an existing method
 *      - map() accepts a Function<T,R> which accepts 1 arg and returns a value
 *      - Here we're mapping the String::toUpperCase method to Function<T,R>
        - The method itself doesn't accept a parameter but there is a source String and it's the String obj, we're
            using to invoke the method
            - is mapped to the argument function and the return value is a string returned from toUpperCase()
 *      - We could have also written it as below, which is also valid as well
 *          s -> s.toUpperCase()
 *  - We use a method reference for a method that takes parameters, the compiler has to be able to infer what the
 *      arguments are
 *  - Though we can't use a method reference with startsWith(), as there is no way a compiler should know that the
 *    argument is "G"
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "N40","N36",
                "B12","B6",
                "G53","G49","G60","G50","g64",
                "I26","I17","I29",
                "O71");

        List<String> gNumbers = new ArrayList<>();

        someBingoNumbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")){
                gNumbers.add(number);
               // System.out.println(number);
            }
        });

        //Sort the gNumbers
        Collections.sort(gNumbers, (s1, s2) -> s1.compareTo(s2));
        // gNumbers.sort((s1, s2) -> s1.compareTo(s2));
        // print
        gNumbers.forEach( myString -> System.out.println(myString));


        //////////////////  Streams //////////////////
        System.out.println("////////// Streams  ////////////////////");
        Function<String,String> convertToUpperCase = myString -> myString.toUpperCase();
        Predicate<String> stringsStartWithG = myString -> myString.startsWith("G");

        // Using Predicate - Long way
        System.out.println("////////   Using anonymous ///////");
        someBingoNumbers
                .stream()
                .map(s -> convertToUpperCase.apply(s))
                .filter(s -> stringsStartWithG.test(s))
                .sorted()
                .forEach(System.out::println);

        System.out.println("////////// Method reference  ///////////////");
        // concise way
        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);
    }
}
