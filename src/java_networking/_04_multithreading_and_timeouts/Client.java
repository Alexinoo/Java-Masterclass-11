package java_networking._04_multithreading_and_timeouts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",5000)){

            socket.setSoTimeout(5000);

            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream() , true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);

                if (!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println("Echo from server: "+response);
                }

            }while (!echoString.equals("exit"));
        }catch (SocketTimeoutException e){
            System.out.println("The socket timeout");
        }catch (IOException exc){
            System.out.println("Client Error "+exc.getMessage());
        }
    }
}
