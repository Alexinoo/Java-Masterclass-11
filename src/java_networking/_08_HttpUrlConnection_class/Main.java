package java_networking._08_HttpUrlConnection_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

/*
 * HttpUrlConnection
 *
 * We can do more with the URL connects than just reading a web page
 *
 * We can get a head of information from the web page without having to read it in it's entirety
 *
 *
 * /// getHeaderFields() - Retrieve headers for the web page
 *
 * Let's take a look at how we can retrieve headers for a web page : http://example.org
 *
 * We'll use a Map with a Key as a String and value as a List<String>
 *
 *      Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
 *
 * This retrieves all the headers for the web page
 * And now we can use a for loop to print the contents of our map
 *
 *      for(Map.Entry<String, List<String>> entry : headerFields.entrySet()){
                String key = entry.getKey();            -- retrieve key
                List<String> value = entry.getValue();  -- retrieve value
                System.out.println("-----key = "+ key);

                for (String stringValue : values) {
                    System.out.println("value = "+stringValue);

              }
        }

        - Print the key
        - loop through the values
 *
 *
 * ////// getHeaderField() - Retrieving single header
 *
 * We can use urlConnection.getHeaderField() to retrieve each of these values individually
 *
 *      String contentType = urlConnection.getHeaderField("Content-Type");
        System.out.println(contentType);
 *
 *
 * /// ///////////////////////
 *  HttpURLConnection class
 * ///////////////////////////
 *
 * We can't actually write to the example.org page because of the line below where we have set this to true
 *
 *      urlConnection.setDoOutput(true);
 *
 * When you want to write to a URL, you could use the URLConnection class, but as mentioned, the URLConnection is a generic connection
 *  class
 * When reading web pages or content created in feeds essentially anything that can be reached over the internet using URL, then you'll
 *  want to use the HttpURLConnection class which is a subclass of URLConnection class
 *
 * The reason why you would want to use HttpURLConnection class, is because it contains support for http specific features
 *
 *
 *
 * ///////// HttpUrlConnection ///////////
 *
 * Let's take a look briefly of what happens when your browser requests a web page or when you press submit button on a form
 * When your browser asks for a web page. it does what's called a GET request.
 * The server that hosts the page will then respond, and the response will include a code
 * For example,
 *  - the code 200 means OK, which means the server found the web page and was able to return it to the browser with no problems
 *  - the code 404 is returned when the server can't find the requested web page
 * You might have seen this when surfing the web.
 * Some sites will return a nice error page, but others don't handle 404 error very well & you'll just see a standard message
 *  in the browser telling you the page can't be found
 * A code of 500 means that something critical went wrong
 * For example, the database the web page needs to access could be down
 * The return code is called the response code
 *
 * When you want to provide information to a URL, you use a POST operation
 * We won't demonstrate POST usage, because the most common use of POST is to post values to a form, and we don't have a form to
 *  practice on
 * It wouldn't be nice to use a form on an existing site, you'll see sample code about how to POST, but you won't be able to run an
 *  application that does
 *
 *
 * //////////
 * Let's us now start working with HttpUrlConnection class
 *
 *      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
 *
 * We're returning HttpURLConnection obj with a call to url.openConnection()
 * Basically the method is smart enough to return a HttpURLConnection
 * It was actually doing the same thing with the previous code
 * We just didn't use any of the methods that were specific to the HttpURLConnection class
 * In fact we only used methods from the parent URLConnection class
 *
 *
 * //// Reading jar files
 *
 * Another type of URL that the method recognizes is a jar URL
 * So if you provide a URL for a jar file, the method will return a jar URLConnection instance
 * You do this when you want to read or write to a jar file
 * And because the method recognizes the Http protocol and returns the appropriate connection instance, it's safe to cast the return
 *  value to a HttpURLConnection object in this case
 *
 * If we can recall from previous videos, Tim said that calling openConnection() doesn't actually open the connection to the destination
 * You can configure the connection before calling the connect() to actually connect
 * Now we want to read the web page so we're going to be using a "GET" request
 *
 *      connection.setRequestMethod("GET");
 *
 * Because the default method is GET, we don't actually have to call this method when we want to do a GET operation , but we've done this to
 *  make it abundantly clear with our code
 * In addition to GET and POST, you can also pass the following options to setRequestMethod()
 *      - HEAD
 *      - OPTIONS
 *      - PUT
 *      - DELETE
 *      - TRACE
 * Follow the link below to find out more on the above
 *
 *      https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html
 *
 * It's important to note that each HttpUrlConnection instance can only be used to make 1 request
 * However, the underlying connection may persist across connection objects
 * When working with high level API, you're dealing with abstractions, so when you call the connection method for the first time, java has to
 *  create a socket and do all the handshaking that we saw when working with low-level API
 * But because we're dealing with the high-level API, this is handled for us automatically
 * And because the application you're creating here might want to perform more requests, the underlying connection may persist even if you close
 *  the HttpUrlConnection connection
 * Having to create the socket for each request will actually be inefficient which is the reason that Java does that under the hood to keep the
 *  connection open
 * At this point, you may wan to set some request properties
 * We won't go through to set every property, but what we will do is to set the header fields which contain information about a request or
 *  a response
 *
 * As we saw earlier when getting a web page, the header fields contain information about that web page
 * Check the link below to get more info on what web page header fields are and what they actually do and an example of usage of them as well
 *  as what they'll come back as
 *
 *       https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
 *
 * In this case, we're setting the header fields for the request we're making, which will provide the server with information about the request
 * We'll set the User-Agent property as an example, which tells the server what the browser or script you're using, and we'll set it to
 *  a random browser, say Chrome for example
 *
 *      connection.setRequestProperty("User-Agent","Chrome");
 *
 * The setRequestProperty(String key, String value) is in the URLConnection parent class
 * The first parameter is the key and the second one is the value
 * If the property you're setting can accept multiple values, you can only use 1 setRequestProperty() call
 * You use commas to separate the values you pass as a second parameter
 *
 * If we wanted to do a POST on the other hand at this point, you'd want to call connection.setDoOutput(true) , so that we can actually write to
 *  the connection though we won't be doing that in this case
 *
 * ///// Response code
 *
 * We'll then call getResponseCode() which will tell us whether we were able to successfully read the page
 *
 *      int responseCode = connection.getResponseCode();
 *      System.out.println("Response code: "+ responseCode);
 *
 * ////// Timeout
 * Just as with sockets, we can also set timeout value
 * If a website is down, or the server is slow for some reason, our application won't hang waiting for a response
 *
 *      connection.setReadTimeout(10000);
 *
 * We can set this to 30000ms which is 30 seconds, though far too generous and normally, we wouldn't want to wait for 30 sec though
 *  probably ok in this case
 * And certainly you'd expect a server to respond more quickly than within 30 sec
 * But don't make the timeout too small though or you're constantly talking about 10 or 15 seconds would probably be a reasonable number
 *  there
 *
 *
 * //////
 *
 * Let's now check whether the response code is the correct value
 * If the response code is not 200, then print something to the user, that the was some sought of an error reading from that web page
 * We can also check whether the response code matches the HTTP_OK constant defined in HttpUrlConnection class which has a value of 200
 * Otherwise, print contents of the web page if the response code is positive
 *
 *
 * ////
 * I have used a private method to do the printing that takes HttpUrlConnection instance as the parameter
 *
 *      printWebsiteContent(HttpUrlConnection connection)
 *
 * The we use a BufferedReader that takes InputStreamReader instance
 *
 * Then we pass connection.getInputStream() as the input stream
 *
 * Call readLine() on BufferedReader instance, print through the entire web page content
 *
 *
 * //////
 * IF we run this, we can see that we've successfully read the web page and we've got a response code of 200 as well
 *
 * We might be thinking, wait a minute Tim, we didn't call the connect() , so why is this all working ?
 * The thing to keep in mind is that some methods implicitly perform the connection step
 *
 * When we actually call the getResponseCode(), it performed the connection
 * In fact, HttpUrlConnection.getInputStream() also implicitly connects and therefore all the calls to connect are unnecessary
 * If an operation depends on being connected to work, it'll actually perform the connection where necessary
 * If we call connect() when a connection has already been established, the method doesn't do anything and there's no performance
 *  hit
 * In other words, explicitly calling connect() can make the code clearer for anyone reading it but fell free to leave it when the code
 *  calls another method that implicitly performs a connection
 *
 */
public class Main {

    public static void main(String[] args) {
        try{
            URL url = new URL("http://example.org");

            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            // Retrieve headers for the web page
            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
            for(Map.Entry<String, List<String>> entry : headerFields.entrySet()){
                String key = entry.getKey();
                List<String> values = entry.getValue();
                System.out.println("-----key = "+ key);

                for (String value : values) {
                    System.out.println("value = "+value);
                }
            }

            System.out.println("/".repeat(50));

            //We can also use urlConnection.getHeaderField(String name) to retrieve individual values
            String contentType = urlConnection.getHeaderField("Content-Type");
            System.out.println(contentType);



            /// HttpURLConnection Class ///////
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Chrome");

            // Just as with sockets, we can also set timeout value
            // If a website is down, or the server is slow for some reason, our application won't hang
            // waiting for a response

            connection.setReadTimeout(10000);

            // check for response code, if we were able to read from the web page
            long responseCode = connection.getResponseCode();
            System.out.println("Response code: "+ responseCode);

            // check to see if the response code is the correct value
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Error reading web page");
                return;
            }

            // Print web page contents
            printWebsiteContent(connection);


        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }
    }

    private static void printWebsiteContent(HttpURLConnection connection) throws IOException{
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while (true){
                line = reader.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
        }
    }
}
