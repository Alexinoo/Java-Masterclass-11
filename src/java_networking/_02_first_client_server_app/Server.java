package java_networking._02_first_client_server_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * First Client and Server Apps
 *
 * We'll write 2 applications to demonstrate how to do network coding
 * One application is going to be the server and the other will be the client
 *
 * We'll start to work on the server application and it's purpose is just going to echo back to the client whatever text the client
 *  sends it
 *
 * Steps
 *  - Create a ServerSocket using try-with-resources statement
 *
 *      ServerSocket serverSocket = new ServerSocket(5000);
 *
 *      - The port no that we've allocated here, that can be an integer between 0 and 65535 inclusive
 *      - Some port numbers are already reserved or may already be in use by other applications
 *      - The way to find out is to assign a port number and see if the socket instance is successfully created
 *      - Depending on the application, we can do some research on the internet to find out what specific applications or which port numbers
 *         specific applications use, but for now we'll just work with 5000
 *
 *  - We want the Server to listen for clients on the Port that we've assigned
 *      - To do that we call serverSocket.accept() and that returns a Socket Instance/obj
 *
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            - And notice that it is a Socket instance and not a ServerSocket
            - This will be the socket used to communicate with the client
            - Keep in mind that every client that connects to a server will do so using the same port but through it's own socket

        - The accept(), is going to block until a client connects to the server
        - To show that, we're going to add a println statement after the call to accept

        - Note that if we run this, we don't see the message, "Client Connected" and that's because the application in this case the Server,
           is waiting for a client to connect to it
        - We obviously, haven't got a client bit of code written yet, and we'll have to disconnect manually

 *  - When a Client connects, the Server will use input and output streams to send data to the client and receive data from the client
 *      - The socket.getInputStream() and socket.getOutputStream() methods will return the input and output streams associated with the
 *         ServerSocket instance
 *
 *          BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            - A common practice is to wrap the input stream with a BufferedReader and to wrap the output stream with the PrintWriter
            - The 2nd parameter to the PrintWriter constructor specifies whether you want to automatically flush the output stream the
               PrintWriter is using
            - If you don't specify true, then you'll have to call the flush() after every write to the stream to make sure data is sent

 *
 *  - Now it's time to read data from the client
 *      - At this point, we can use BufferedReader as we would - there's nothing special about a BufferedReader attached to a socket,
 *      - You want the server to remain alive until the client doesn't need it anymore, and so this is one time when you'll intentionally
 *         use an infinite loop
 *
 *          while(true){
 *
 *              String echoString = input.readLine();
 *              if(echoString.equals("exit"))
 *                  break;
 *
 *              output.println("Echo from server: "+ echoString);
 *
 *          }
 *
 *          - Read from the BufferedReader readLine() which reads from the client
 *          - When the server receives the String "exit" from the client, it's going to check that,terminate and exit from the infinite loop
 *          - But if it's not "exit", then we're going to echo the string back to the client with the prefix "Echo From Server"
 *
 *      - Then we write the String to the socket using PrintWriter println()
 *
 *  - Catch an IOException
 *      - Print "Server exception" and the error message
 *      - Print StackTrace
 *
 *
 *  That's all there is to writing the server, though we'll make some improvements to this server later
 *
 * /////////////////
 *
 * Now let's write the Client application
 *  - Create Client.java file
 *
 *
 * ////// Running the app
 *
 * Run the Server.java first
 *
 * Run the Client.java second
 *
 * IF we look at the Server.java console, we now get "Client connected" to the console
 * At this point, the client is connected to the server and so the accept() in the Server.Main.java is no longer blocked and the code
 *  continues executing
 *
 * So we're now executing while loop right now, and it's basically sitting there waiting on the readLine(), waiting to receive some input
 *  from the client
 *
 * If we type, "Hello From Server", we get an "Echo From Server: Hello, Server" response back
 * And we'll continue getting the response that we write to the server until we type "exit"
 *
 *
 * If we type in "exit", this disconnects both Server.Main.java and Client.Main.java sessions
 *
 * At this point we've now written my first client and also my first server
 *
 * But the Server we've written isn't very useful because it can only connect to 1 client
 *
 * We'll start modifying it so that we can accept connections for multiple clients
 *
 *
 *
 *
 *
 */
public class Server {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream() , true);

            while (true){
                String echoString = input.readLine();
                if (echoString.equals("exit"))
                    break;

                output.println("Echo from server: "+echoString);
            }
        }catch (IOException ioe){
            System.out.println("Server Exception "+ioe.getMessage());
        }
    }
}
