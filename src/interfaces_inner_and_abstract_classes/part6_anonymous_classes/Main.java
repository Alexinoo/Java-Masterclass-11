package interfaces_inner_and_abstract_classes.part6_anonymous_classes;

import java.util.Scanner;

/*
 * Anonymous class
 * ...............
 *
 * - An anonymous class is a local class but with no name
 * - They have to be declared and instantiated at the same time because they haven't got a name
 * - They are used when a local class is required only once
 *
 * - Might sound very less useful, but they are common for attaching event handlers to buttons in a user interface
 * - FOr example, used extensively when you're programming Android apps
 *
 * - It's quite likely that if we do have several buttons that each would require a different on-click method, so using a local class might not be the
 *    best solution
 * - For example, if you've got 4 or 5 buttons on the screen, chances are you don't want the exact same functionality to be called each time that button
 *   is clicked
 * - For example:
 *      - 1 button might be to quit out of the program
 *      - another might be to print something
 * - Therefore the code to actually execute, it might be different depending on the button
 *
 * - This is where an anonymous class can come into use or can become quite handy, coz what we can do in that example is to define a class for each
 *   button, we could an anonymous inner class to declare an object and assign it to a button in the 1 expression
 *
 *
 * Use Case
 *  - Use Button class from the previous example
 *      - Create a Button instance variable "btnPrint"
 *
 *  - call setOnClickListener() on the button instance and pass an instance of the anonymous class interfaced with the Button
 *      - also create the override, and also creates handleOnClick() itself which means we have to implement for this onClickListener interface
 *
 * - The main difference rather , was we're not creating a separate class as we did with the local class example
 *
 * - With this example , we're using an anonymous class because we used new Button.onClickListener() which was added by intelliJ and there's no name
 *   there because it's been implemented there and then within the brackets
 * - You can see we also overrode the handleOnClick() which we needed to do in order to actually ensure that we actually implemented the interface
 *   correctly
 *
 * - Mostly passed as an expression to a method parameter
 *
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static Button btnPrint = new Button("Print");
    public static void main(String[] args) {

        btnPrint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void handleOnClick(String title) {
                System.out.println(title + " was clicked");
            }
        });
        listen();
    }

    private static void listen(){
        boolean quit = false;
        while (!quit){
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    quit = true;
                    break;
                case 1:
                    btnPrint.onClick();
                    break;
            }
        }
    }
}
