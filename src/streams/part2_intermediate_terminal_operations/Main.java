package streams.part2_intermediate_terminal_operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 * Streams - Intermediate and Terminal Operations
 *
 * map()
 * - We wouldn't be able to use map() with a method that expects 2 arguments, because that method can can be mapped to a function
 * - The map() wants a Function<T,R> and not a BiFunction<T,U,R> and we don't have to pass a method reference
 *      - We can pass a lambda that matches the function pattern, accept 1 argument and return 1 value
 *          e.g. Function <T , R>
 * - In this case, it happens to be a method, in the String class that does what we want to do
 *      - Obviously, the method that we have to use has to work with the item types in the stream
 *      - We can't use String::toUpperCase , if the items in the Stream weren't strings
 *
 * - The map() ultimately returns a Stream , that contains all the Uppercase bingo numbers
 *      - It will contain all the results of calling the toUpperCase() on the input stream
 *
 * - It's called map , because it's essentially mapping each item in the input stream to the result returned by the Function<T,R> argument
 * - The resulting stream will have the same number of items as the source of the stream which is "someBingoNumbers"
 *
 * - When we chain functions together, a function in the chain would operate on the result from the last function in the chain
 *
 * - Essentially, this is what is happening here
 *      - Each stream operation operates on a stream result from the last step
 *
 * Discussion
 *  - We've created a stream from our collection and passed it to the next operation in the chain - which is map()
 *  - map() takes a Function<T,R> and produces a stream containing uppercase bingo numbers
 *  - filter() takes a Predicate<T> and not a Function<T,R> and we're passing a lambda expression that takes 1 parameter and returns true/false
 *      - The resulting stream will then contain only those items for which the predicate return true
 *      - That's 5 numbers starting with G
 *  - sort() - sorts based on the natural ordering of the items in the stream
 *      - there's an overloaded version of sort() that takes a comparator but, we don't need that one here
 *  -  forEach(Consumer<? super T> action) - prints the results
 *
 * However, forEach() in this case isn't the same one we previously used with Iterable interface
 *
 * - In this case we're using forEach() from the stream class, and in fact , all the steps have used methods from the Stream class, which makes sense
 *   because the object type returned from the stream() is a stream
 * - Therefore, from that point forward, every method we call operates on a stream namely, the stream returned from the previous step
 *
 * - the stream forEach() does what the iterator one does , and essentially, it accepts a Consumer<? extends T> and evaluates the consumer for
 *   each item in the stream
 *      - Since System.out.println() accepts an argument and doesn't return a value, we can map that to a consumer and pass it to forEach, which
 *        we have done using a method reference
 *
 * - the iterator forEach() takes a Consumer<? super T> argument
 *
 * - Since forEach() doesn't return a value, the chain has to end here because we're on the last line and there's nothing to pass on to another
 *   step at this point
 *
 * Terminal Operation
 *  - for that reason , forEach() is called a terminal operation
 *  - a terminal operation returns void or a non stream result
 *  - since every operation in a chain requires a source stream, ultimately, the chain has to end when we use a terminal operation
 *
 * Intermediate Operations
 *  - operations that return a stream are called intermediate operations, because they don't force the end of the chain
 *
 *
 * Overview
 *  - When a chain is evaluated, a stream pipeline is created
 *  - The stream pipeline consists of a source, zero or more intermediate operations, and a terminal operation
 *  - We've used a collection as the source, but we could also be an array or an I/O channel, and we can build streams from scratch
 *
 * Discussion
 *  - The items in the source enter the pipeline, and the chain result emerges at the other end of the pipe
 *  - Elements may be removed from the stream as a result of an operation, so the set of elements that comes out at the other end of the pipe
 *    doesn't have to match the number that entered the pipe
 *
 *  - We noticed that when we weren't using streams, our "g64" number wasn't printed with a lowercase 'g'
 *  - But when we used streams, it was printed with an uppercase 'G'
 *  - In the non-stream case, we didn't use the result of the toUpperCase() call
 *  - When an item passed the test , we assigned the original string with the lower-cased 'g' to the new list
 *
 *  - In the stream case, the map() method maps each source string to the Function<T,R> result, therefore the upper-cased string is added to the
 *    resulting stream and passed to the next step in the chain
 *  - That's why the non-stream case prints a lower-cased 'g' and the stream case prints an upper-cased 'G'
 *
 *
 * Let's look at what else we can do with streams
 *  - Rather than creating a Stream based on a collection, we can create 1 from scratch
 *  - We can use Stream.of() to create a stream comprised of some numbers from our bingo numbers list
 *
 *       Stream<String> ioNumberStream = Stream.of("I26","I17","I29","I71");
 *
 *  - The above gives us a Stream<String> "a stream of objects"
 *  - We can also create streams composed of any type of object, but we can't create a stream of mixed types
 *  - For example:
 *      - We can't have a Stream of String and Employee obj(s)
 *
 *  - Let's create another stream that contains the in and bingo numbers
 *
 *      Stream<String> inNumberStream = Stream.of("N40","N36","I26","I17","I29","O71");
 *
 *  - Let's concatenate these streams together
 *      - We can achieve this by using Stream.concat()
 *      e.g  Stream.concat(Stream<? extends T> a , Stream<? extends T> b)
 *
 *  - The Stream.concat() is a static method and can't be used in the chain, but we can use a result as a source for chain
 *      - Let's print out the number of items in the concatenated stream
 *          e.g.
 *
 *           Stream<String> concatStream = Stream.concat(ioNumberStream , inNumberStream);

             System.out.println("Number of Items : "+concatStream.count());
 *
 * - Since count() returns a long value, it's a terminal operation
 *
 * - Right now , a stream contains duplicate numbers, but we can remove them using distinct()
 *
 * distinct()
 *  - uses equals() to determine which objects are duplicates
 *  - so our stream contains string objects and so the method is going to use a String and equals() for comparison purposes
 *      - we'll chain the distinct and account operations so that we print the number of items that remain after the duplicates are removed
 *
 * Can we print out the items in a stream, but without entering the chain?
 *  - Suppose we're trying to debug a long chain and we want to print the results of each operation in the chain
 *  - We can use the forEach() though it's a terminal operation
 *  - instead we can use the peek(), which accepts a consumer argument , it evaluates the consumer for each item in the source stream, and then adds
 *    the item to a new stream, which then returns
 *  - since it returns a stream value , it's an intermediate operation, and the chain doesn't have to end
 *
 * Right now we're printing out the number of distinct items, let's actually print the items out before we can call count so that we can verify
 *  that the items in the stream are actually distinct
 *
 *        System.out.println("Number of unique items : "+concatStream
                    .distinct()
                    .peek(System.out::println)
                    .count()
 *
 * - We've now bot the 6 distinct items in the stream that was return from distinct, followed by the count
 *
 * The documentation states that peek() mainly exists to support debugging, which is why we're using it here, to be able to print out that as
 *  we go through and show the elements that actually remain after the distinct was called
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

        ///// Streams //////////////

        Function<String,String> convertToUpperCase = myString -> myString.toUpperCase();
        Predicate<String> predicateFunction = myString -> myString.startsWith("G");
        Comparator<String> comparatorFunction = new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                return o2.compareTo(o1);
                            }
                        };
        Consumer<String> consumerFunction = myString -> System.out.println(myString);

        System.out.println("////////////////// Using Function<T,R> , Predicate<T> , Comparator<T> - sort desc //////////////////////////////");
        someBingoNumbers
                .stream()
                .map(convertToUpperCase)
                .filter(predicateFunction)
                .sorted(comparatorFunction)
                .forEach(consumerFunction);

        System.out.println("///////// Using lambda expressions as method reference - sort asc /////////////////");
        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);


        ///////////// Creating Stream from Scratch /////////////////////////
        Stream<String> ioNumberStream = Stream.of("I26","I17","I29","O71");
        Stream<String> inNumberStream = Stream.of("N40","N36","I26","I17","I29","O71");

        // concatenate "ioNumberStream" and "inNumberStream"
        Stream<String> concatStream = Stream.concat(ioNumberStream , inNumberStream);

       // System.out.println("Number of Items : "+concatStream.count()); // 10

        System.out.println("-------------------------------");

       // System.out.println("Number of unique items : "+concatStream.distinct().count()); // 6

        System.out.println("Number of unique items : "+concatStream
                    .distinct()
                    .peek(System.out::println)
                    .count()
        );

    }
}
