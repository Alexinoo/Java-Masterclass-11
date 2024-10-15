package java_networking._09_HttpURLConnection_alternatives;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Alternatives to HttpUrlConnection
 *
 * We found out that some methods implicitly perform the connect() step
 *
 * Let's now try to read a web page that doesn't exist and see if we'd actually get 404 response back
 * Let's simulate a bogus page that does not exist as below
 *
 *      url = new URL("http://example.org/somepage.html");
 *
 *      - And now if we run this we get a response code of 404 error reading web page
 *      - And at this point, the program exits so that no more processing is done to retrieve or proceed to read contents of web page that
 *          doesn't exist
 *
 *
 * ///////  getResponseMessage() /////////
 *
 * We can also call HttpUrlConnection.getResponseMessage() and get more info about the error
 *
 *      if (responseCode != HttpURLConnection.HTTP_OK){
                System.out.println(connection.getResponseMessage());
                return;
         }
 *
 *      - And if we run this, we get "Not found" returned by getResponseMessage()
 * Depending on the response, you might get the information, that will help you figure out what went wrong
 *
 * /// POST request
 * IF we're doing a POST request, we'd call the HttpURLConnection.getOutputStream() to get an output stream which we'd use to write to the
 *  connection
 * And we can also wrap the output stream in DataOutputStream if we want to,
 * Will see some sample POST code in a minute
 *
 * But let's try passing parameters as part of url which is something you'll often want to do
 * We're going to use Flickr API service to fetch the most recently uploaded photos tagged with cats
 *
 * Check the link below
 *
 *      https://www.flickr.com/services/feeds/docs/photos_public/
 *
 * We can the URL in this site :
 *
 *      https://www.flickr.com/services/feeds/photos_public.gne
 *
 * And we can pass various parameters to this url
 * The query parameters you can include to specify which photos you'd like to be returned
 * We can use the query string parameters "tags" which is a comma delimited list of tags to filter the feedback as follows:
 *
 *      url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags=cats");
 *
 * And we can paste this to the browser just to see what we get from this API
 * And we get a feed here, which is an xml feed of various photos which is updated in real time and you'll be getting different content
 * It's also a good idea to try testing out the URL that you're working with using a browser before using it in the code and you can be
 *  sure that the URL is correctly functioning and returning the right data
 *
 *      - Check the title : "Recent Uploads tagged cats"
 *      - Check under the entry tag
 *      - copy the href/link to a browser and see the photo returned
 *
 * Since flickr is an image site that retrieves or receives tens of thousands of images a day, you'll find when you're running this, you'll
 *  be getting different images showing there
 *
 *
 *
 * /////
 * If we were to read this URL into an application, you could display the photos or display a list of links or literally whatever else you
 *  want to do within your application
 * Now that we know that the URL works, and takes us to something valid, let's now write the code to get the most recently uploaded photos
 *  tagged with cats at Flickr
 *
 * So, all we need to do is change the url
 *
 *      https://www.flickr.com/services/feeds/photos_public.gne
 *
 * And add a query to that
 *
 *      https://www.flickr.com/services/feeds/photos_public.gne?tags=cats
 *
 * To actually get specific data related to recently uploaded photos that are tagged with the word cats
 * SO, let's update that in our code and use the above link instead
 *
 *      url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags=cats");
 *
 * And now if we run this, we're able to see the xml returned from that link printed to the console with also a response code of 200
 *
 * We can also change this as well to dogs
 *
 *      url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags=cats");
 *
 *      - run the same program and we'll get some data back related to dogs in this case
 *
 *
 *
 * //////
 * At the moment, we have a URL here that is "https" but it's actually using HttpUrlConnection and not the HttpsUrlConnection
 * What is happening is that we're not truly using the https protocol which is becoming very common and so you'd need to use the
 *  HttpsUrlConnection instance to actually achieve that
 * And out of interest, HttpsUrlConnection isn't in the java.net package but rather in javarx.net.ssl
 *
 *
 * ///// Code Sample for POST - refer to the slide ////
 * Steps:
 *  - Create a new URL
 *      URL url = new URL("http://somewebpage//processform.php");
 *
 *  - Then create a HttpURLConnection
 *      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
 *
 *  - We set the request method to POST
 *      connection.setRequestMethod("POST");
 *
 *  - We set the user agent to chrome
 *      connection.setRequestProperty("User-Agent","Chrome");
 *
 *  - Then set the content-type to "application/x-www=form-urlencoded" and the content-length
 *      connection.setRequestProperty("Content-Type","application/x-www=form-urlencoded");
 *
 *  - We could pass the following parameters as part of the URL or separately as well
 *  - The parameter string is the part of the URL that appears after the question mark
 *
 *      String parameters = "parameter1=25&parameter2=hello";
 *
 *  - we're basically telling the server the content that we'll be sending as well as the length of the content
 *      connection.setRequestProperty("Content-Length",Integer.toString(parameters.getBytes().length));
 *
 *  - we then set the output to be true
 *      connection.setDoOutput(true);
 *      connection.setDoInput(true);
 *
 *      - and that's where the system will ultimately know to write the code out
 *
 *
 *  - Then we're writing out bytes related to the parameters, which is wrapped around the DataOutputStream
 *      DataOutputStream output = new DataOutputStream(connection.getOutputStream());
 *      output.writeBytes(parameters);
 *      output.flush()
 *      output.close()
 *
 *  - Then reading with a BufferedReader and printing the contents of the URL
 *      InputStream input = connection.getInputStream();
 *      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 *
 *      String line;
 *      while( (line.readLine()) != null){
 *          System.out.println(line);
 *      }
 *
 * There are other ways to write this code depending on the server and the request, you might have to set more properties when you're
 *  configuring the connection
 * In this particular case, the URL is a PHP script which could be the action that's performed when a user submits a form for example
 *
 *
 *
 * //////
 * At this point we have now seen how to use the HttpUrlConnection and UrlConnection classes to read web pages and retrieve specific data
 *  from web pages (by passing parameters) which is great
 * But here's the thing, many Java developers don't use classes in the java.net.package when they want to work with URLs
 *
 *
 * The java.net package has been in the JDK, all the way back since java 1.0, and it hasn't changed much since then
 * Under the hood, it's using an older version of the HTTP Protocol
 *
 *
 * Classes that use the newer Http/2.0 protocol were proposed and worked on Java 9, and the proposal listed the following problems with the
 *  current API( taken from http://openjdk.java.net/jeps/110):
 *
 *  - The base URLConnection API was designed with multiple protocols in mind, nearly all of which are now defunct (ftp, gopher etc)
 *  - The API predates HTTP/1.1 and is too abstract
 *  - It is hard to use, with many undocumented behaviors
 *  - It works in blocking mode only ( one thread per request/response)
 *  - It is very hard to maintain
 *
 * Unfortunately, the behaviour of the java.net Http classes can be frustrating
 * Try sending parameters using the POST method, as in the sample code, and you be tearing your hair out
 * It doesn't always work and when developers go to the web and try to find out why, other developers will usually say "Don't use the
 *  java.net. Use a third-party library"
 *
 * Unfortunately, the proposed Java 9 API has been placed in what's called the incubator, meaning based on the feedback from developers
 *  who tried the early access features, it's not ready for prime time
 * The API won't be in the base Java 9 JDK, but developers will still be able to try it out by adding it to their projects
 *
 * Based on the feedback the API team expects to get from even more developers, the API may be revised and then added to the JDK, or it
 *  may be deleted, so it's not a good idea to use the new API in any applications you plan to release to users in future
 *
 * In the meantime, many developers are using 3rd party libraries when they want to work with the HTTP protocol
 * Two popular ones are
 *  1. Jetty - Available from : https://www.eclipse.org/jetty/
 *  2. Apache HTTPClient - Available from : http://hc.apache.org/httpcomponents-client-ga/
 * Even though the Apache library only supports HTTP/1.1 , developers like it because it's much easier to use than the java.net classes
 *
 * It's important that you got a taste of the high-level API in the java.net classes, so you can read code that uses them, but when
 *  you want to work with the HTTP protocol, you might want to consider using jetty or the Apache HTTPClient libraries
 *
 * To give us a taste of the Apache Http Client library, let's use it to read the example site: https://example.org/
 *
 *
 * /////
 * So, let's go ahead and download what is the current latest release
 * Follow the link below and download the latest release : HttpClient 5.4
 *
 *      https://hc.apache.org/downloads.cgi
 *
 *      - Download the zipped (Binary version)
 *      - Extract to the following location : "C:\Users\alex.wakanyi.STL-HORIZON\Documents\lib\httpcomponents-client-4.5.14-bin"
 *
 * Then go back to IntelliJ and add the jar files inside "httpcomponents-client-4.5.14-bin\lib" folder to this project
 *      - File >
 *              Project Structure >
 *                                  Libraries >
 *                                              Click Add >
 *                                                          Java >
 *                                                                 Navigate to "C:\Users\alex.wakanyi.STL-HORIZON\Documents\lib\httpcomponents-client-4.5.14-bin"
 *
 *                                                                  > Click lib folder
 *                                                                      > Select all the jar files
 *                                                                          > Click OK
 *
 * ANd we have successfully added the jr files to our project
 *
 *
 *
 *
 * /////
 * Create a new class ApacheTest.java
 *
 * Let's now see what the equivalent code is to retrieve the contents of the web page,
 * Instead of java.net, we'll be using Apache Http Client
 * Steps:
 *  - Call createDefault() from HttpClients class which returns a CloseableHttpClient instance
 *
 *      CloseableHttpClient httpClient = HttpClients.createDefault();
 *
 *  - Create a Request obj by Pass our example.org url to HttpGet constructor
 *
        HttpGet request = new HttpGet("http://example.org/");
 *
 *  - Add User-Agent header properties to our request obj

        request.addHeader("User-Agent","Chrome");
 *
 *  - Call execute(request) and pass the request obj from CloseableHttpClient instance

        CloseableHttpResponse response = httpClient.execute(request);

        - Returns CloseableHttpResponse obj

        - Print the response code also by calling getStatusLine().getStatusCode() on the response obj


 *  - Call printWebsiteContent() to print the contents of this web page
 *      - Pass response.getEntity().getContent() to this method which is an InputStream
 *      printWebsiteContent(response.getEntity().getContent())
 *
 *  - Catch IOException and print out the error message to the console

 *  - httpClient.execute(request) throws java.io.IOException which we need to handle in the finally block

 *  - check that the response obj is not null and call close() on this obj to close the resources
 *
 *
 * Since we're not using try-with-resources, we need to make sure that the resources are closed
 *
 *
 * //// Summary
 * The code is quite similar to the one we had except that we're
 *  - creating CloseableHttpClient instance
 *  - then we're getting a HttpGet instance for the URL
 *  - then we configure the request obj and not the httpclient
 *  - and set our header obj to use Chrome as the User-Agent
 *  - Then calling CloseableHttpClient.execute() and that sends off the request
 *  - We then get the response code
 *  - and the rest of the code is just printing the contents of the web page
 *      - we're using our printWebsiteContent() which uses a BufferedReader to do just that
 *
 * When using CloseableHttpResponse , it is highly recommended that you use the close() in the finally block
 * And the reason is that , system resources may not be released correctly
 *
 *
 * N/B
 * We can also throw IOException directly from the main() , instead of catching it from the finally block
 *  - Check the commented code in the ApacheTest.java
 *
 * ////
 * And now if run our ApacheTest.java, we're now able to retrieve our example.org page with no problems and got our response code
 *  correctly which is 200
 *
 * So this is a taste of one of the popular 3rd-party libraries and also the end of how to work with high-level API and the end of the
 *  section on java network programming
 *
 */

public class Main {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://example.org/");

            //check with a page that does not exist
            url = new URL("http://example.org/somepage.html");

            // Use Flickr - read the html returned from below link - link in the resource section
            url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags=cats");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Chrome");
            connection.setReadTimeout(10000);

            long responseCode = connection.getResponseCode();
            System.out.println("Response code: "+responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK){
                // more info
                System.out.println(connection.getResponseMessage());
                return;
            }

            printWebsiteContent(connection);
        } catch (IOException e) {
            System.out.println("IOException: "+e.getMessage());
        }

    }

    private static void printWebsiteContent(HttpURLConnection connection) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
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
