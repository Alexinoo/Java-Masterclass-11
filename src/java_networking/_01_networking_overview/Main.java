package java_networking._01_networking_overview;

/*
 * Networking Overview
 *
 * A Network is a system of computers connected together so that they can share resources and communicate with each other
 * Networking refers to how the connected computers communicate
 *
 * When using Java, the java.net package contains the classes that we can use to establish connections between computers and then send
 *  messages between them
 * When writing networking code, we need to be familiar with threads and input/output streams covered in other sections
 *
 * Nowadays, networking is discussed in the context of the internet, but computers may also communicate across a private network
 *  (called intranets)
 * In fact, that's where networking began, and intranets are still common today in business
 * For example, office workers don't usually have a printer at their desks. They share a Printer
 * When they print a document, the document is sent to the printer over the business's intranet
 *
 * Sometimes a single business wil; have multiple intranets, one for each department
 * Most software teams use version control, meaning that developers have to check files into a central repository, and other developers can
 *  check out the files and work on them
 * Some teams use version control applications available in the cloud, such as GitHub.
 * But others use version control that's in house, running on the business's intranet
 *
 * We can also use networking when we want applications running on the same machine to communicate with each other
 * But let's stop saying machine
 * When discussing networking, a machine is usually referred to as a host
 *
 * A common network configuration that you've probably heard of is clinet/server, meaning that one (or more) hosts on the network are acting as
 *  servers, and the other hosts are clients that connect to the server
 * At a high level, that's how the Internet operates
 *
 * The Browser is the Client, and when you type in a web address, it connects to the server that has the files for the website at that address
 * That's overly simplified, but essentially that's how the Internet works
 *
 * As mentioned, you can have a client/server interaction on the same Host
 * For example, the MySQL database comes with a workbench you can use to perform database operations
 * The workbench is the client, and it connects to the MySQL database server on that localhost - But of course it can also connect remotely
 *  to another server if needed
 *
 * Another example, is local web development
 * It's possible to run an Apache or IIS server on your computer and connect to it using a browser on the computer
 * That's often what web developers do when they're working on a website for a client
 *
 * Computers on a network (which includes the Internet) communicate using a Transport Protocol
 * We'll use the TCP and UDP protocols in this lecture
 *
 * Generally, a computer will have one physical connection to a network, and anything sent to the computer from other hosts on the network will
 *  arrive through that connection
 * But sometimes the same computer will be running multiple applications that want data from the network
 * For example, at any one time, you might have a browser open, a chat application open, be streaming music
 *
 *
 * When data arrives at one physical connection to the network, how does it get routed to the target application ?
 * That's where ports come in
 * Each application that needs data from the network is assigned a port (this includes clients connecting to a server that's on the same
 *  machine).
 * When data arrives, the port number is used to route the data to the application that's waiting for it
 *
 * So every host connected to the internet has a unique IP address including mine
 * To find out what your ip address is , you can run a command from your cmd or just type "what is my ip" on your browser
 * Print: 197.232.1.50
 *
 * You may see 4 numbers in the form : X.X.X.X, or you may see a series of 8 segments , separated by colons
 * That's because there are Ipv4 addresses and IPv6 addresses.
 * IPv4 stands for Internet Protocol Version 4, and IPv6 stands for Internet Protocol Version 6
 *
 * Once upon a time, there were only IPv4 addresses.
 * IPv4 uses a 32-bit address scheme that allows for over 4 billion unique addresses
 * But now we have computers, tablets, game consoles, smart TVs, smart phones, smart appliances, etc
 * All connect to the internet, and each device has to have a unique IP address
 *
 * As a result 4 billion IP addresses weren't enough, and so IPv6 was born
 * It uses a 128-bit address scheme, which allows for many, many more IP addresses than IPv4 does
 * IPv4 addresses are written as 4 integers , separated by dots
 * IPv6 addresses are written in hexadecimal and separated by colons
 *
 * Right now we're in a transition period, so when you asj for IP addresses, you may see the IPv4 address returned, or you may see the
 *  IPv6 address
 *
 * IP stands for Internet Protocol.
 * You may have heard the term TCP/IP
 * This refers to using the TCP protocol with IP addresses, which doesn't necessarily mean the host is connected to the Internet
 * Two applications running on the same host can use TCP/IP to communicate with each other
 * When the Client and Server are on the same host, usually the IP address 127.0.0.1, which is referred to as localhost, is used to identify
 *  the host
 *
 *
 * With that brief introduction to networking terms out of the way, let's start our tour of the java.net package
 * The package contains 2 sets of APIs:
 *      1.) Low-level API
 *      2.) High-level API
 *
 * When using low-level API, you'll have to be more aware of networking concepts.
 * Still, when using Java to do network coding, you're always working with abstractions, regardless of whether you use the high or low
 *  level API
 *
 * Java makes network coding much easier than it could have been, by letting developers write code using abstract concepts and taking care
 *  of the implementation details under the hood
 * Because it's so common, we're going to use client/server examples in the networking lectures
 * You'll often want a reliable, two-way communication link between the client and the server
 * This is where TCP protocol comes in
 *
 *
 * TCP - Transmission Control Protocol
 *
 * TCP establishes a 2-way connection between hosts, and this connection is reliable in the sense that the 2 hosts talk to each other
 * When used with Internet addresses, you get TCP/IP, which uses the client/server model
 * When communicating using TCP/IP, the sequence of events is as follows:
 *
 *  1. The Client opens a connection to the server
 *  2. The Client sends a request to the server
 *  3. The Server sends a response to the client
 *  4. The Client closes the connection to the Server
 *
 * Steps#2 and Step#3 may be repeated multiple times before the connection is closed
 *
 *
 * When using low-level networking API, you'll use sockets to establish connections, send requests, and receive responses
 * A socket is one end-point of the two-way connection
 * The client will have a socket, and server will have a socket
 *
 * When you have multiple clients connecting to the same server, they'll use the same port number, but each client will have its own socket
 * You'll use the Socket class for the client socket, and the ServerSocket class for the server's socket
 *
 * What's wonderful about Java is that all you have to do is provide the IP address and port when you create the socket.
 * You don't have to understand how TCP/IP works
 * Underneath the covers, specific messages have to be sent to establish a connection between the client and the server ( a process that's
 *  called handshaking), and the data has to be sent as packets, which MUST be in specific format
 *
 * But you don't have to take a course in computer networking when you write network code using Java.
 * It takes care of all the details under the covers
 *
 * Since we're talking about networking, we'll have to write 2 applications to demonstrate how to do network coding
 * One application will be the server, and the other client will be the client
 *
 * We'll start by writing a server application that just echoes back to the client whatever text the client sends to it
 *
 *
 *
 *
 */





