package java_networking._04_multithreading_and_timeouts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread{
    private Socket socket;
    public Echoer(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),  true);

            while (true){
                String echoString = reader.readLine();
                System.out.println("Received client input: "+echoString);

                if (echoString.equals("exit"))
                    break;
                
                try{
                    Thread.sleep(15000);
                }catch(InterruptedException ie){
                    System.out.println("Thread Interrupted: "+ie.getMessage());
                }

                writer.println(echoString);
            }
        }catch (IOException ioe){
            System.out.println("Oops: "+ioe.getMessage());
        }finally {
            try{
                socket.close();
            }catch (IOException ioe){
                // Ooh, well!
            }
        }
    }
}
