package java_networking._03_multithreaded_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Multi Threaded Server
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

            while (true){
                new Echoer(serverSocket.accept()).start();

                //Above is Equivalent to
                /*
                Socket socket = serverSocket.accept();
                Echoer echoer = new Echoer(socket);
                echoer.start();
                */
            }
        }catch (IOException ioe){
            System.out.println("Server Exception "+ioe.getMessage());
        }
    }
}
