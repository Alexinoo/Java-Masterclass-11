package java_networking._02_first_client_server_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/*
 * Client App
 *
 * Steps
 *
 * - We're going to create a Socket and not a ServerSocket using try-with-resources statement
 *      - The address is going to be localhost and port no 5000
 *      - The difference here is that for our Client app, we need to specify 2 parameters
 *          - The address of the host we want to connect to
 *          - The port no
 *      - We've used "localhost" here because we want to connect to a server running on the same host, which in this case is my computer
 *      - We can also try passing the ip address "127.0.0.1" if "localhost" doesn't work
 *      - We have to specify the same port no for our client app because that's the port no that the server is listening on
 *
 *          Socket socket = new Socket("localhost",5000)
 *
 *      - How do we know which port no to use ?
 *          - If we're writing both the client and the server , you'll know
 *          - If we're writing a client that wants to connect to a server that you didn't write, the documentation for the API you're using
 *             or the server application you're trying to connect to should tell you the port no that your code has to use
 *
 * - Create the Input and Output streams in the same way we did for the server
 *
 *           BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream() , true);

             - Quite similar to our PrintWriter, flushes the output automatically, instead of us having to manually do that every time we send
                the data


 * - We could hard code Strings to send to the server, but let's actually ask the user input instead
 *      - We'll use a Scanner class to read the input from the console and once we have the String, we're goint to write it to the socket using
 *          PrintWriter
 *      - We'll also have to add a check here for the "exit" String
 *      - When the user types in exit, that means that they want to quit and therefor we'll exit from the loop
 *
 *          Scanner scanner = new Scanner(System.in);
 *          String echoString;
            String response;

        - We'll use a do-while loop,

            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);

                if (!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }

             }while (!echoString.equals("exit"));

         - We're using a do while loop here because we want the loop to execute at least once, because we don't have any idea how many
            times it'll execute
         - First, we're asking the user for a String, and then we're sending the string to the server using the PrintWriter obj

                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();
                stringToEcho.println(echoString);

         - Then we're just checking whether the String entered equals exit, and if it does, the loop's going to terminate
         - And if the String is not equal to "exit" then we'll continue reading the data coming back from the server using BufferedReader
            & then we're outputting what we got back from the server

              if (!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }

         - The while loop continues until we receive an exit
 *
 * - Catch IOException
 *      - Print error message
 *
 *
 *
 * ///////
 *
 * We've got now our server and our client applications, it's now time to run both applications
 */

public class Client {

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",5000)){

            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream() , true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                // Send the String to the server
                stringToEcho.println(echoString);

                if (!echoString.equals("exit")) {

                    // receive the string from the server
                    response = echoes.readLine();
                    System.out.println(response);
                }

            }while (!echoString.equals("exit"));
        }catch (IOException exc){
            System.out.println("Client Error "+exc.getMessage());
        }
    }
}
