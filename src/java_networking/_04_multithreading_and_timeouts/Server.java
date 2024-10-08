package java_networking._04_multithreading_and_timeouts;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Multi Threaded Server And Timeouts
 *
 * Run the Server.Main.java
 *
 * Run the Client.Main.java - Client-1
 * Run the Client.Main.java - Client-2
 *
 * Test that both are working,
 * If we recall, each client could only type one message and see it echoed.
 * Now that each client is handled in its own thread, the Server blocking on the accept call doesn't result in all the clients being blocked
 *  as well
 * And the same is true actually when a thread blocks on the readLine call, only that client thread will be blocked
 * The other client threads are still able to service the clients they're connected to
 *
 * Type exit on both clients and terminate the server manually
 *
 *
 *
 * Let's now add a code that puts each thread to sleep for 15 seconds in the Echoer class
 *
 *       try{
                Thread.sleep(15000);
             }catch(InterruptedException ie){

               System.out.println("Thread Interrupted: "+ie.getMessage());
           }
 *
 * And so that we can see that there's no delay for client connections, we're also going to add a println statement to the run() itself, just
 *  to let us know that the server has received it
 *
 *      System.out.println("Received client input: "+echoString);
 *
 *
 * //////
 * And now if we run this:
 *
 * Start the server
 * Start Client.Main.java - Client-1
 *      - Type Tim
 *          - We can see the Server has received the input
 *      - Then go to Client-2
 *          - Start Client.Main.java - Client-2
 *              - Type Bob
 *
 *      - Then Go back to Client-1 and just wait for the echo to come back after the 15 sec delay
 *          - And we get our echo back
 *      - Then Go to Client-2 and wait for remaining seconds
 *          - We get Bob echoed to us as well
 *
 * The point here is 1 thread taking a while will not result in every client being blocked until the long-running client is finished which is
 *  pretty cool
 * We can actually test this and run as many clients as we'd want, and we can actually do that, but let's disconnect our clients first and our
 *  server as well
 *
 * But before we do that let's get rid of our sleep code - comment it out
 *
 *
 * ////////
 * Let's now try testing this with let's say 4 clients to make sure that it's still working
 *  - Test with all the 4 clients and ensure they're working as expected
 *  - The Server is able to cope with all of these clients without any problems
 * Close down all the clients
 *
 * And now hopefully, I have understood why the server doesn't terminate automatically, just because one client wants out doesn't mean
 *  they all do and even if all the clients exit, other clients may well want to connect in the future
 * Therefore the Server remains alive and listening for that reason
 *
 * Note that when a client exits, the thread for that client terminates
 *  - Confirm that by running the server in debug mode
 *  - But first, add a breakpoint on the socket.close()
 *
 * Then Run the Server.Main.java in debug mode
 *  - Then run 2 clients
 *  - Chat with the server
 *  - If we type exit in one of them (Client-1), notice that we hit the breakpoint in the Server.Main.Echoer.java
 *
 * So that confirms that the code is being executed, on the server side to close the socket when the client exits/terminates
 *
 * Then let's click continue from the Server console , and the server is still running and we can still chat with the other client (Client-2)
 *
 *
 * //////   Timeouts  ////////
 * That's the basics of working with sockets
 * Before we proceed to UDP, let's take a look at timeouts briefly
 * When a Client is communicating with a server, it doesn't want to block forever
 * If the server goes down or it isn't sending a response for some other reason, then the client would want to report back to the user
 * Otherwise, the user is going to think the application is frozen or has just died for some other reason
 *
 * So we can set a timeout period on a socket, so that if a socket doesn't respond within the timeout, then we get a
 *  java.net.SocketTimeoutException thrown
 *
 * We'll set a timeout for the client
 *
 * Add the line below before reading from the Server using BufferedReader to the Client.java
 *
 *      socket.setSoTimeout(5000);
 *
 * We'll need to handle the SocketTimeoutException, and in this case, we're going to terminate the application but in a real world it
 *  will depend on the situation
 *  - We may want to for example try sending the request again
 *  - We might want to abort that particular action and inform the user
 *  - Or perhaps, we might want to terminate the application
 *
 * We might want to give the user a choice and there's a couple of options up here
 * But for this demo app, we'll just add a catch block to the existing try catch block and we're going to catch the exception outside
 *  the loop and then terminate the client application
 *
 *
 * ////// Testing
 *
 * To test this, we're going to simulate a delay for 15 sec so that the Client.Main.java actually times out before the server can respond
 * We're going to uncomment the sleep code from the Echoer.java to achieve that
 *
 * We have re-enabled the server sleep code so that the server thread sleeps for 15 seconds
 *
 * We're going to start the server and then restart the client and we've also set the client socket time out to 5 seconds
 * And the server sleeps for 15 seconds each time the client sends a request
 * So consequently, the socket should time out, and the SocketTimeoutException should be thrown
 *
 *
 * ////
 * Run the server and client
 * Type something from the client side and wait for a response
 * We can see that the client socket times out before the server could respond
 *
 * Note that the client also terminated
 *
 * Using timeouts is a very important tool and it prevents the client from blocking forever
 *
 */
public class Server {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){

            System.out.println("Server started..");
            while (true){
                Socket socket = serverSocket.accept();
                Echoer echoer = new Echoer(socket);
                echoer.start();
                System.out.println("Client connected");
            }
        }catch (IOException ioe){
            System.out.println("Server Exception "+ioe.getMessage());
        }
    }
}
