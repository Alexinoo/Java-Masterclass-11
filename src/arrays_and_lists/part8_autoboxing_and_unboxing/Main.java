package arrays_and_lists.part8_autoboxing_and_unboxing;

import java.util.ArrayList;

/*
 * Autoboxing and Unboxing
 * .......................
 * - We've looked at how we can create array
 *      - An array of Strings
 *
 *      - An array of ints
 *
 * - And also how to create an ArrayList and add "Tim" to the ArrayList
 *
 * - But interestingly enough,we can't do as below
 *      ArrayList<int> intArrayList = new ArrayList<int>();
 * - We'll actually get an error "type argument cannot be of primitive type" and as it turns out, is an issue because
 *   a primitive type is not a class
 * - And we actually need a class reference here, to use that to be able to save something to an ArrayList
 * - So if we had a list of integers , we can't save them, based on what we know so far in Java in an ArrayList
 *
 * - However,
 *  - we can still create a class "IntClass" as below
 *  - and then create an ArrayList<IntClass> as shown below and actually add an object of type IntClass with the value
 *    of 54
 * - It's a little bit messy because of the constructor code and there's getters and setters and so forth that we
 *   need to create to do that
 * - So it would be good if there's another easier way to do this and for all the primitive types, the variables,
 *   the data types, if there was a way to actually use those as objects
 *
 * Autoboxing
 *  - Well that's what Autoboxing is all about because Java provides us with that functionality
 *  - Java supports some primitive types by using a class, an object wrapper if you will for the class
 *  - So what we've done by creating IntClass, we've created a wrapper, we sort of wrapped and created integer value
 *     "myValue" is the variable that's going to contain the value for this class
 *  - We wrapped it in a class and made sure it wasn't publicly available
 *  - we can only update the variable using a constructor initially and then a setter
 *  - and we can only retrieve the value only using the getter
 *
 *  - So that's basically the concept of a Wrapper, we're wrapping this functionality and the way to access this is
 *    through the access modifier, so that not everyone can access it
 *
 *  - We have Wrapper classes for the int
 *          Integer integer = new Integer(54);
 *          Double doubleValue = new Double(12.25);
 *
 * - So this helps us in the sense that we can now use it an ArrayList as below
 *          ArrayList<Integer> intArrayList = new ArrayList<Integer>()
 * - and Java is quite happy with that because Integer is a valid class ,  unlike what we were trying to do that
 *   earlier with primitive types
 *
 * - Next, populate intArrayList with some values and call Integer.valueOf(i)
 *
 * - Integer.valueOf(i) takes the value of i as a primitive type and converts it automatically into the Integer class
 *   so that we can use it as a class
 *
 * - And now we can create another for loop to output the contents of the intArrayList by calling Integer.get(i).intValue()
 *
 * - So essentially we're dealing with 2 parts here :-
 *      Integer.valueOf(2)
 *          - is called autoboxing
 *          - is where we're converting a primitive type int to an Integer obj
 *
 *      intArrayList.get(i).intValue()
 *          - is called unboxing
 *          - is where were converting the Integer obj back to a primitive type
 *
 * - But it turns out that Java actually even makes things easy for you, we've looked at the long way of doing it
 *   first but there's actually a shorter way
 *
 * - We can do something like this
 *      Integer myIntValue = 56;
 *
 * - But what happens is that at runtime, Java will convert above to below
 *      Integer myIntValue = Integer.valueOf(56)
 *
 * - In other words, Java is doing the hard work for you, it recognizes and say, OK, that is an Integer object
 *   you have assigned an integer, and will actually use the reference to create the proper class based on the
 *    proper object "myIntValue"
 *
 * - However, if you typed in something like
 *      Integer myIntValue = 56.56;
 * - This is different because this is now a double, and it's looking for an integer but you've typed a double
 * - For this to work, you'd actually have to use a Double class, the Object wrapper Double to get that to work
 *
 * - And now we can do something like this
 *      int myInt = myIntValue ; // myIntValue.intValue()
 *
 * - And Java is doing something like this "myIntValue.intValue()" for us automatically
 *
 * - This is because the method "intValue()" returns an int and that's why it's working for us because Java is able
 *   to change the code at compile time to the correct code for it to work preventing compile time errors that might
 *   come up
 *
 * - Use an ArrayList with a Double
 *      - add double elements
 *
 * - Optionally, we can remove
 *      Double.valueOf(dbl) to dbl
 * - and
 *      myDoubleValues.get(i)
 * - and the code will still compile and run
 *
 * - And the reason for that is that Java is adding those commands behind the scenes using the .valueOf() and
 *   doubleValue() to convert them for us automatically
 *
 * - That's Autoboxing and unboxing, a good concept if you need to understand to actually store the common primitive
 *   types, that are part of Java in array lists or pass them as parameters using objects
 *
 *
 *
 */

class IntClass {
    private int myValue;
    public IntClass(int myValue) {
        this.myValue = myValue;
    }
    public int getMyValue() {
        return myValue;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }
}
public class Main {

    public static void main(String[] args) {
        String[] strArray = new String[10];
        int[] intArray = new int[10];

        ArrayList<String> strArrayList = new ArrayList<>();
        strArrayList.add("Tim");

       // ArrayList<int> intArrayList = new ArrayList<int>();
        ArrayList<IntClass> intClassArrayList = new ArrayList<IntClass>();
        intClassArrayList.add(new IntClass(54));

        //Wrapper classes for primitive types
        Integer integer = new Integer(54);
        Double doubleValue = new Double(12.25);

        // Now we can use the primitives Wrapper classes to do something similar with an ArrayList
        ArrayList<Integer> intArrayList = new ArrayList<>();

        // use a for loop to add some entries to "intArrayList" : intArrayList.add(Integer e)
        // Integer.valueOf(i) converts an int to an Integer obj
        for (int i = 0; i <= 10; i++) {
            intArrayList.add(Integer.valueOf(i));
        }

        //output and convert it back from an Integer object to an int type (primitive type)
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " --> "+ intArrayList.get(i).intValue());
        }

        Integer myIntValue = 56;   // Integer.valueOf(56);
        int myInt = myIntValue.intValue();

        System.out.println(myInt);     // 56
        System.out.println(myIntValue); // 56

        // Use double
        ArrayList<Double> myDoubleValues = new ArrayList<Double>();
        for (double dbl = 0.0; dbl <= 10.0; dbl += 0.5) {
            myDoubleValues.add(Double.valueOf(dbl));               // Autoboxing
        }
        
        // loop
        for (int i = 0; i < myDoubleValues.size(); i++) {
            double value = myDoubleValues.get(i).doubleValue();    // Unboxing - convert from the Object Wrapper
            System.out.println(i +" --> "+ value);                  // back to primitive type of double
        }
    }
}
