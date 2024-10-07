package java_networking._03_multithreaded_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Multi Threaded Server
 *
 * We've now written our first client and server, but the server that we've written so far isn't very useful and that's because it can only
 *  connect to one client
 *
 * So let's now modify it so that it can now accept connections from multiple clients
 *
 * So, for the server we need to change the call to accept, so that it takes place within the loop and that's because if it's called once, it
 *  can only ever accept 1 connection
 * But if it's inside the loop, the server will accept the connection,
 *  - create a new socket for the client,
 *  - create the input and output streams for that client socket
 * And then loop around and call accept() again , and that's what we want to do so that we can support multiple clients
 *
 * Therefore, we'll do some changes to our code as follows:
 *
 *       while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream() , true);

                String echoString = input.readLine();
                if (echoString.equals("exit"))
                    break;

                output.println("Echo from server: "+echoString);
          }
 * So basically, what we've done is moved everything inside the while loop , so that the server can loop back and accept more client
 *  connections
 *
 *
 * /////
 * And now if we run the server, and run our Client.java
 *  - We get "Client Connected" printed to the console and we can test by typing some random text and confirm that's working correctly
 *
 * We can run our Client.Main.java again while the first one is running, and we can still see "Client Connected" again and if we type some
 *  random texts, we get a response back which confirms that everything is working correctly as well
 *
 * So now we've got 2 clients connecting to our server
 *
 * ///
 *
 * Alright at this point we've now got multiple clients connected to the server, but the server code still got or has problems.
 * Can you see what those problems are ?
 *
 *
 *
 * ///// Potential Problems with our Current Setup//////////
 *
 * Let's demonstrate the problem - Exit from both clients first
 *  - Depending on the order that Tim shuts both Client.java sessions, the Server may not shut down
 *  - Tim has actually closed down both clients, but the server hasn't closed down
 * In a real-world situation, that wouldn't be a problem because usually you would want the server to be running 24/7
 * But if you ever wanted to terminate the server, you'd then do it manually in that scenario and we don't worry so much about that
 *  problem and if necessary, we can terminate the server manually
 *
 *
 * Let's run this again, in the following sequence
 *  - Run the Server.Main.Java
 *  - Run Client.Main.java again but this time, we're not going to type anything to the console
 *  - Re-Run Client.Main.java again (might need to edit configurations to enable this) - run a second copy of it
 * // Problem
 * We should have seen 2 "Client Connected" messages printed to the console but there's only one,
 * Because we didn't type anything in the first client, the Server code is actually blocked waiting for input, at the input.readLine below
 *
 *      String echoString = input.readLine();
 *
 * So basically, the first iteration of the loop hasn't completed, and so the Server hasn't called accept again
 *
 * IF we go back to Client-1, and type something, we can now see "Client Connected" which means that Client-2 has now connected
 * Also note that each client will only ever see 1 message echoed back to it
 *
 * IF we close both the clients : Client-1 and Client-2 and start the sessions again
 * IF we type something to the console, we get an echo back from the server but if we do the second time, note that we don't get that
 *  response back
 *  - In other words, the second message wasn't echoed back and that's because the server is now blocked on the accept()
 *
 * IF we run , the second Client - Client-2, we get the same problems as well that it only echoes back the first message but fails to echo
 *  the second one,  because the server is now blocked on the accept() again
 *
 * Terminate al the sessions , both the Server and the Client sessions
 *
 *
 * //////
 *
 * Let us now make a change to the Server code, to simulate a long running task and now because we need time to switch to a second client and
 *  type something into it's console, we're going to have the Server sleep for 15 sec everytime it receives input from the client
 * THe server will sleep for 15 sec before responding to us
 *
 *              String echoString = input.readLine();
                try{
                    Thread.sleep(15000);
                }catch (InterruptedException ie){
                    System.out.println("Thread Interrupted "+ie.getMessage());
                }
 *
 *
 *  //// Testing ////
 *
 * We'll now run the server, then run our first client but then we're not going to type anything in the console from the first client
 * We'll then run the second client and find that the second client won't be able to respond or connect rather until the server reads data
 *  from the first client
 *
 * So, we're going to go back to the first client and type in a String and then switch back and see what happens
 *
 * /// Results
 * So what we have added is a delay before we get our response back
 *
 *
 *
 * ///
 * So what is the cause of all these problems ?
 * Well if you guess that, then it's because the server is doing everything on a single thread, Tim is going to give us a Gold Star
 * Servers that can connect to multiple clients will run multiple threads
 * Let's modify the code so that the server handles each client on a separate thread
 * And this is going to do 2 things
 *  1. The server will be more responsive to client connection requests
 *  2. Will prevent 1 client from hogging the server for too long
 *
 * On the Server's main thread, we're going to call the accept() but whenever it accepts a connection, we'll kick of a new thread for the
 *  client and then call the accept() again
 * So each Server thread will be responsible for creating the input and output streams, listening for requests from the client and responding
 *  to those requests
 *
 *
 * //////
 * Create a new class : Echoer
 * Extends Thread class
 *
 * Echoer : class
 *
 * Fields:
 *      socket : Socket
 *
 * Constructor: Initialize the socket
 *      public Echoer(Socket socket){
 *          this.socket = socket
 *      }
 *
 * Override run() which will be called when the thread is started
 *
 *      public void run(){
 *
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),  true);

            while (true){
                String echoString = reader.readLine();

                if (echoString.equals("exit"))
                    break;

                writer.println(echoString);
            }
        }catch (IOException ioe){
            System.out.println("Oops: "+ioe.getMessage());
        }finally {
            try{
                socket.close();
            }catch (IOException ioe){

            }
        }
    }
 *
 * The code is very similar to what we're currently doing in the main()
 * What is going to be different is we're not going to use try-with-resources statement and that's because the method doesn't create the
 *  Socket when the server connects to a client
 * So we have to explicitly close the socket when we finish with it
 *
 *
 * /////
 * We now need to call accept() inside the while loop
 *
 *      Socket socket = serverSocket.accept();
 *
 * Then pass the Socket instance to the Echoer constructor
 *
 *      Echoer echoer = new Echoer(socket);
 *
 * And finally, start off the thread
 *
 *      echoer.start();
 *
 * ///// Summary /////
 *
 * Basically, we're creating a ServerSocket as before but this time, we're kicking off a new thread everytime we accept a connection
 * And we can recall that serverSocket.accept() which returns a Socket obj for the client, and we're sending that to the Echoer constructor and
 *  then calling the start()
 *
 */
public class Server {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){
           /* while(true){

                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream() , true);

                String echoString = input.readLine();
                try{
                    Thread.sleep(15000);
                }catch (InterruptedException ie){
                    System.out.println("Thread Interrupted "+ie.getMessage());
                }
                if (echoString.equals("exit"))
                    break;

                output.println("Echo from server: "+echoString);
            } */

            /* Using Threads */

            while (true){
                Socket socket = serverSocket.accept();
                Echoer echoer = new Echoer(socket);
                echoer.start();

                System.out.println("Client connected");

                //Above is Equivalent to
                /*
                 new Echoer(serverSocket.accept()).start();
                */
            }
        }catch (IOException ioe){
            System.out.println("Server Exception "+ioe.getMessage());
        }
    }
}
