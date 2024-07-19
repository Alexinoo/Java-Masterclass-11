package interfaces_inner_and_abstract_classes.part5_local_classes;

import java.util.Scanner;

/*
 * Local class
 *  - local classes are declared inside a block, such as a method or an if statement, and their scope is restricted completely to that particular
 *      block
 *  - unlike anonymous class , local classes are used less often
 *  - theoretically, you could argue that they increase encapsulation because they're only declared within the block that uses them, but in practice,
 *    you, probably not often that you'll need to or want to use one
 *  - so, one example, local classes might be useful is as a comparator, when trying to sort objects
 *
 * Use case
 *  - Let's create our own Button class, and attach a listener to it, so the code will execute when the button is clicked
 *
 *
 * - Add a scanner variable
 * - Create a Button instance
 *
 * Next
 *  - Let's create a local class that implements OnClickListener interface and assign an interface of it to our button
 *
 * - Create ClickListener class which is our local class that implements Button.OnClickListener interface - defined in the main()
 *      - Added ClickListener constructor
 *          - print something to the user
 *      - Implement handleOnClick() from the Button.OnClickListener interface
 *
 * - Next
 *      - call setOnClickListener() on the button instance  "btnPrint" and pass an instance of the ClickListener local class
 *
 * - Next
 *      - add listen() : void
 *      - gets the input from the user
 *          - prints "Print was clicked " anytime a user presses 1
 *
 * - Call listen() from the main class
 *
 *
 *
 *
 *
 * - The main thing to notice here is with the local class, how it's actually been defined, is applicable just for that block only
 * - So this particular method using a local class is useful, if you want to assign exactly the same object, say to several buttons
 * - The class is not used anywhere, so making it local, in this case makes sense because we're not using it in a shared environment or anywhere
 *   else, implementing it as a local class within this method does actually make sense
 *
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Button btnPrint = new Button("Print");

    public static void main(String[] args) {
        class ClickListener implements Button.OnClickListener{
            public ClickListener() {
                System.out.println("I've been attached");
            }
            @Override
            public void handleOnClick(String title) {
                System.out.println(title + " was clicked");
            }
        }

        btnPrint.setOnClickListener(new ClickListener());
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
