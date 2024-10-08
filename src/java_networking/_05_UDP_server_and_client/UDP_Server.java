package java_networking._05_UDP_server_and_client;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * UDP Server and Client
 *
 * UDP stands for User Datagram Protocol
 * When using TCP, some handshaking has to take place between the server and the client
 * In other words, the client has to connect to the server and the server has to accept that connection
 * So the client sends a request and the server sends a response
 * It's a 2-way connection, and there's tight coupling between the client and the server
 * The TCP protocol also performs Error Checking and will resend messages that don't make it to the server
 * And so it's quite a reliable protocol, but that reliability requires overhead which can ultimately affect performance
 *
 *
 * UDP - User Datagram Protocol
 * When using UDP, there's no handshaking at all
 * And the destination host which may or may not be a server doesn't actually send any responses to the message sender
 * So you use it when you don't need a reliable connection or a two-way connection or when speed is essential
 * So if you require a response to a message that you send over the network, then you wouldn't use UDP
 *
 * UDP uses datagrams
 * A Datagram is a self-contained message and is not guaranteed to arrive at it's destination
 * UDP is often used for time-sensitive communication and when losing the odd message or packet here and there won't matter
 *
 * For example
 *  - Voice over IP applications like skype and video streaming usually use UDP because speed is more important than ensuring absolutely
 *     every packet arrives
 * Our eyes won't notice if the occasional package isn't received, for example when we're watching a video
 * Also the data arriving at the client is immediately replaced by more data, so it's not critical that all messages reach the client
 * UDP is used for time-sensitive communication because the overhead involved with TCP is actually significant and so data can be sent much
 *  more quickly using UDP
 * As usual, which protocol to use depend on the application that you're looking to write
 *
 *
 * ///////////
 * We're going to rewrite the clients and the server applications to use UDP
 * We'll create
 *  - UDP_Server.java - to act as a server app
 *  - UDP_Client.java - to act as the client app
 * We're not going to worry about threads this time since we know how to do that from the TCP code that we worked on
 * For this demo app, Tim will show us how to send data using a Datagram
 * In a real-world app, we'll definitely use threads like we did in the last videos
 *
 *
 * ///// UDP_Server.java
 *
 * We'll start with the server first - UDP_Server.java
 *
 * Steps:
 *  - Create DatagramSocket
 *      - A DatagramSocket is created on port 5000 to receive and send UDP packets
 *
 *  - Infinite Loop ( while(true) )
 *      - The server continuously listens for incoming UDP packets
 *
 * - Receive DatagramPacket:
 *      - A DatagramPacket is created with a buffer of 50 bytes to store incoming data
 *      - The socket.receive(packet) method blocks the thread until a packet is received
 *      - The received data is extracted and printed to the console
 *
 * It is important to note that socket.receive(packet) doesn't create an end-to-end connection between the server and the client
 * So, it doesn't return anything that the server can use to send data back to the client
 *
 * When using TCP, the accept() did create an end-to-end connection
 * So, it returned a socket that the server could use to communicate with the client, but that's not the case here
 *
 *
 *
 * //// UDP_Client.java
 *
 * Steps:
 *
 *  - Get the address of the host via  InetAddress.getLocalHost()
 *      - Returns address of type InetAddress for the host that we'd like to send data to
 *      - We're getting localhost because we're going to be running the server on this machine
 *      - There's also an InetAddress.getByName() which let's you pass a hostname to it and returns the InetAddress of the host
 *
 *
 *  - Create DatagramSocket
 *      - We're creating a DatagramSocket that this client is going to use and note that the socket isn't associated with a port no
 *
 *  - Use a do while loop
 *
 *      - Use a Scanner to get user input
 *      - Convert the input to a byte[]
 *
 *      - Create a DatagramPacket and pass
 *          - the buffer
 *          - the length of the buffer
 *          - the InetAddress which is the localhost and the port
 *
 *      - Then send the packet via socket
 *
 *  - Exit the loop, when the user enters "exit"
 *
 *
 * Recall that the datagram is a self contained message
 * The message contains not only the data but also the address and port no
 * So, if the server wants to respond, it can get all the information it needs from the datagram packet
 *
 * In the TCP case, once the end-to-end connection was established, the socket had all the information required to communicate with
 *  the client
 * So the server didn't have to get the information from each message
 *
 *
 * ///////
 * Test this out
 *
 * Start the server
 * Run the client
 *  - Type something "Tim was here" for example
 *
 * We can see our text printed to the UDP_Server.Main.java console
 * And note we're not getting an echo back from the server to the client this time, because it's no longer sending anything back to the
 *  client
 *
 * And we can also type in some more strings as well
 * Also note that we're not at the moment, seeing the complete buffer showing there and our text is appended with null characters
 * The problem is that at the moment, we've bot a byte[] buffer with a size of 50 bytes , and we're receiving the packet into that and
 *  we're not trying to extract out only the characters that aren't null there
 * We're just dumping everything and creating a new String called buffer
 *
 * So if we wanted to only output the information relating to the number of characters received, or the actual message , we can change
 *  below when we're creating a new String buffer to only return the actual amount as follows:
 *
 *      System.out.println("Text received is: "+ new String(buffer,0, packet.getLength()));
 *
 * And this should now give us a String of just the actual size that was retrieved, instead of dumping the entire byte array
 *
 * ///////
 * Run both the server and the client
 * Test and confirm we're getting the actual message from the server
 *
 *
 * Also, Tim insist that in a real-world application, we would be using threads here,  but we're not doing that because we've done that using
 *  the previous videos in this section
 *
 * Before we move onto High Level API, let's see how the server would get the information it needs from a packet to be able to send data back
 *  to the client
 *
 *      String returnString = "echo: "+new String(buffer,0, packet.getLength());
        byte[] buffer2 = returnString.getBytes();
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buffer2, buffer2.length,address,port);
        socket.send(packet);
 *
 * Once the server has received a packet, it uses th address and port in the received packet to create a new DatagramPacket which it then sends
 *  back to the client
 * We could have used the same byte[] for both input and output, but we're 2 different ones here for clarity
 *
 * We get the following details on the packet it received
 *      - InetAddress via getAddress()
 *      - port no via getPort()
 *
 * And that's because there's no permanent end-to-end connection between the server and the client
 * In fact UDP is preferred to as a Connectionless protocol for that reason
 * We have retrieved the address and the port from the packet that was sent to us and then we've constructed a new DatagramPacket with all the
 *  information and the String which is going to be basically an echo of what was sent to us
 *      - This is basically the content of the message that was sent to us prefixed as "echo :"
 * And then we're sending it using socket.send(packet) the socket
 *
 *
 * /////
 * Update the UDP_Client code to expect a response and output it
 * However , keep in mind that when using UDP, you'd often don't want a response
 * We're setting up the client at the moment now waiting for a response purely for demonstration purposes, but we wouldn't necessarily do that
 *  in a real-world application
 *
 *  - Create a byte[] so that we can store the data coming back to us and allocate size of 50
 *  - Create a DatagramPacket based on the size of the byte[], so that it can be used to receive the data
 *  - Receive data from the socket and write to the packet
 *      -  The  datagramSocket.receive(packet) method blocks the thread until it receives or gets the server's message
 *  - print the extracted data text from the buffer , 0 - packet.getLength()
 *
 *
 * //////
 * Run the server and the client
 * Test and confirm that the text we send to the server is echoed back to us by the server
 *
 *
 *
 *
 * ///////
 * We're done looking at the low-level API
 * We can see that even if it's quite low-level, we're still working with abstractions here
 * All the complexity of handshaking and constructing packages is largely taken care of by Java
 *
 * So we're free to focus on the data that you want to send, but as we'll start seeing in the next video, the High-level API hides even
 *  more of these details for us
 *
 */

public class UDP_Server {

    public static void main(String[] args) {

        try{
            DatagramSocket socket = new DatagramSocket(5000);

            while (true){
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Text received is: "+ new String(buffer,0, packet.getLength()));

                //Sending back to the client
                String returnString = "echo: "+new String(buffer,0, packet.getLength());
                byte[] buffer2 = returnString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length,address,port);
                socket.send(packet);

            }
        }catch (SocketException e){
            System.out.println("SocketException: "+e.getMessage());
        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }
    }
}
