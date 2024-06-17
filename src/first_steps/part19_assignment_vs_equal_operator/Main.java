package first_steps.part19_assignment_vs_equal_operator;
/*
 * Assignment Operator vs Equal to Operator
 * ........................................
 * - Define an integer variable "newValue" and initialize it to 50 using the assignment operator (=) - 1 equal sign
 * - Test if "newValue" is equal to 50 using the assignment operator (=)
 *      - This doesn't work , with int
 *      - We get the error, incompatible types " Required boolean, Found int"
 *
 *    if (newValue = 50){
 *           System.out.println("This is an error");
 *       }
 * - The above returns the value 50 and that's why we got that error, found an int but required a boolean
 * - The assignment operator (=) returns the value, that has been assigned to the variable
 *
 * - Solution
 *      - Use the equal to operator (==) - 2 equals signs in the if condition instead
 * - The equal to (==) returns true/false on whether the 2 values are the same
 *
 *
 * - Define a boolean variable "isCar" and initialize it to false
 * - Test if "isCar" is true using the assignment operator (=)
 *      - Note that we now don't get an error unlike the code above where we used an (=) with the int
 *      - And if we run this, it's printing the statement that follows
 *
 *   if (isCar = true){
 *              System.out.println("This is not supposed to happen");
 *         }
 * - In this case, even though this is an assignment operator, this returns true because the "isCar" variable is a
 *   boolean and has been assigned to the value true
 * - Fix
 *  - use (==) instead
 *  - Now if we run this, we don't get the output because the condition is now false
 *
 *
 * Next,
 * /// Checking True ////////////////////////////////////
 *
 * - We can simplify below
        if (isCar == true){
              System.out.println("This is not supposed to happen");
          }
 *
 * - further to
       if (isCar){
              System.out.println("This is not supposed to happen");
          }
 *
 *
 *
 *
 * /// Checking False ////////////////////////////////////
 *
 * - We can also use below to check the opposite
 *       if (!isCar){
              System.out.println("This is not supposed to happen");
          }
 *
 * - the above is the same as below
      if (isCar == false){
              System.out.println("This is not supposed to happen");
          }
          *
 * - the above is also similar to below
      if (isCar != true){
              System.out.println("This is not supposed to happen");
          }
 *
 *
 * The NOT Operator
 * ................
 * - The ! or NOT Operator is also known as Logical Complement Operator
 * - It's used with booleans & it tests the alternate value
 *      - if(isCar){} - tests for true
 *      - if(!isCar){} - tests for the opposite value, false in this case
 *
 * Summary
 * .......
 * - below statement test the same thing
 * - "if(isCar == false)" is the same as "if(!isCar)" which is also the same as "if(isCar != true)"
 *
 * Recommendations
 * ...............
 * - Our instructor recommends we use the abbreviated form
 *  if(isCar){
 *      // statements
 *   }
 *
 * - and
 *  if (!isCar){
 *      // statements
 *   }
 *
 * - for 2 reasons
 *     1. avoid the potential for error by accidentally using an assignment operator
 *     2. the code is more concise
 */
public class Main {

    public static void main(String[] args) {

        int newValue = 50;

      //  if (newValue = 50){
      //      System.out.println("This is an error");
      //  }

          if (newValue == 50){
              System.out.println("This is true");
          }

          boolean isCar = false;
//          if (isCar = true){
//              System.out.println("This is not supposed to happen");
//          }

          if (isCar == true){
              System.out.println("This is not supposed to happen");
          }

    }
}
