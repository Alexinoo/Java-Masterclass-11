package lambda_expressions.part10_lambda_mini_challenges;
/*
 * Challenge #8
 * ............
 *
 * - There are many interfaces in Java SDK, and sometimes we can use a lambda expression instead of creating an instance that implements the interface
 *   we want to use
 *
 * - Given a specific interface, how can we tell whether we can map a lambda expression to it ?
 * - What's the criteria that has to be met ?
 *
 *
 * Answer :
 *  - The interface has to be a functional interface
 *  - It can only have a single method that must be implemented
 *
 * - A Functional Interface can contain more than 1 method, but all the method(s) but one must have default implementations
 *
 * - Most of the time, the documentation for an interface will state whether it's a Functional interface
 */
public class Challenge8 {

    public static void main(String[] args) {

    }
}
