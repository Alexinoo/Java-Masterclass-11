package java_networking._04_multithreading_and_timeouts;

import java.io.IOException;
import java.net.ServerSocket;

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

            System.out.println("Server started..");
            while (true){
                new Echoer(serverSocket.accept()).start();
                System.out.println("Client connected");

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
