package java_networking._07_urlconnections_and_inputstreamreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/*
 * URL Connections and Input Stream Reader
 *
 * Continuing on with relative URIs example, we can add more relative URIs located on the same host and resolve them using the same base
 *  URI
 *
 * At the moment we've got our base URI
 *
 *      URI baseUri = new URI("http://username:password@myserver.com:5000");
 *
 * And we've got our relative uri below, for a relative path
 *
 *      URI uri1 = new URI("/catalogue/phones?os=android#samsung");
 *
 * Let's add 2 other ones
 *
 *      URI uri2 = new URI("/catalogue/tvs?manufacturer=samsung");
 *
 *      URI uri3 = new URI("/stores/locations?zip=12345");
 *
 * Then we can resolve our uri1 as follows:
 *
 *      URI resolveUri1 = baseUri.resolve(uri1);
 *
 * And we can also resolve the relative URIs located on the same host as follows:
 *
 *      URI resolveUri2 = baseUri.resolve(uri2);
 *
 *      URI resolveUri3 = baseUri.resolve(uri3);
 *
 * Next is to convert the resolvedUri's to URLs
 *
 *      URL url1 = resolveUri1.toURL();
 *      URL url2 = resolveUri2.toURL();
 *      URL url3 = resolveUri3.toURL();
 *
 * Then print the 3 URLs as follows
 *
 *      System.out.println("URL 1 = "+url1);
 *      System.out.println("URL 2 = "+url2);
 *      System.out.println("URL 3 = "+url3);
 *
 * And if we run this, we get the 3 URLs without exceptions as follows
 *
 * /////
 * As earlier said in the last video, we can now change the server and updating only 1 line of code rather than updating every line that
 *  specifies a URI
 *
 * We can now go back to our baseUri
 *
 *      URI baseUri = new URI("http://username:password@myserver.com:5000");
 *
 * And update to the following
 *
 *      URI baseUri = new URI("http://username:password@mynewsserver.com:5000");
 *
 * And now if we run this, we can see the 3 URLs have now been successfully changed to newserver.com and we've only changed a single line
 *  of code
 * In a real world application , you'd probably define the base URI as a CONSTANT
 *
 *
 * ////////////////
 * Relativized URI
 * ////////////////
 *
 * The other thing we can also determine, is a relative URI, when you know the base URI and the absolute URI using a relativize()
 *
 * Let's see how that works
 *
 * Given our base URI : "http://username:password@mynewsserver.com:5000"
 * And the absolute URI : "http://username:password@mynewserver.com:5000/catalogue/tvs?manufacturer=samsung"
 *
 * We can get the relative URI as follows
 *
 *      URI relativizedURI = baseUri.relativize(absoluteUri_2);
        System.out.println("Relativized URI = " + relativizedURI);
 *
 *      - This prints: "Relativized URI = catalogue/tvs?manufacturer=samsung"
 *
 *
 * ///////////////// Reading From Web pages
 *
 * Accessing the location on internet
 *
 * Tim said that we use a URI until we actually want to access a resource and you then convert it to a URL
 * When working with web locations, you'll mainly use URIs to build absolute URIs from relative ones
 *
 * For our example, moving forward, we'll use a URL right away since we won't be doing queries or building URIs based on user input
 * There are a few ways to read a web page using java, let's read the web page located at example.org
 *
 * Visit http://example.org
 * It's a very simple example of a web page and it's actually set up by Ayanna, which is the internet assigned numbers authority
 *
 * Let's create URL instance for this web page as follows:
 *
 *      URL exampleOrgUrl = new URL("https://example.org/");
 *
 * We are only required to use the constructor and pass the web address as shown above
 * There are versions of the URL constructor that accept a protocol, a host, a port, file and other information
 * But when reading web pages, an API, or a service on the internet, you usually just have to provide a http web address and include
 *  any required parameters that form part of the URL
 *
 * Since all URLs are also URIs, you can actually convert the URL to a URI and call the methods that return the components of a URI
 *
 * Let's convert our exampleOrgUrl to a URI using toURI() as follows
 *
 *      URI exampleOrgUri = exampleOrgUrl.toURI();

        // Fetch component parts of https://example.org/ URI
        System.out.println("Scheme = " + exampleOrgUri.getScheme());     -- "https"
        System.out.println("Scheme-specific-part = " + exampleOrgUri.getSchemeSpecificPart()); -- "//example.org/"
        System.out.println("Authority = " + exampleOrgUri.getAuthority()); -- "example.org"
        System.out.println("User Info = " + exampleOrgUri.getUserInfo());   -- null
        System.out.println("Host = " + exampleOrgUri.getHost());    -- "example.org"
        System.out.println("Port = " + exampleOrgUri.getPort());    -- -1
        System.out.println("Path = " + exampleOrgUri.getPath());    -- "/"
        System.out.println("Query = " + exampleOrgUri.getQuery());  -- null
        System.out.println("Fragment = " + exampleOrgUri.getFragment());    -- null
 *
 *
 *
 * /////////
 *
 * We can read and print contents of a web page in 2 ways
 *
 *
 * /////////////////////
 * 1. url.openStream()
 * /////////////////////
 *
 * Let's now take a look at how we can read directly from the URL using an InputStreamReader
 * We can use BufferedReader to read from a web page by passing url.openStream() to InputStreamReader constructor
 * And then catch IOException thrown
 *
 * Then read the contents the same way we would, similar to reading any contents of a file
 * I have created a separate method printWebsiteContent that takes a URL instance as the parameter
 * Then we call openStream() on the URL instance and pass that to InputStreamReader constructor
 * And then proceed to printing out the contents of the stream
 *
 * Think of url.openStream() as a convenient method that's performing 2 steps that we can do individually
 *      1. open a connection to a URL and then use url.openConnection() and that returns a URLConnection
 *      2. get an input stream from the URLConnection
 *
 * IF we run this now, we get the html and stylesheet content printed to the console for the http://example.org web page
 *
 * Depending on our previous experience with html, this may or may not be what we expected to see
 * Maybe we expected to see what we saw from the webpage
 * But when we're using the browser, it's reading the html data, or the file that's returned that's printed to the console here
 * So the browser is rendering that based on that input - reading the file and rendering the styling information metadata and other info
 * In other words, it's building a more user friendly view of what we can see from the console here
 *
 * But when we're reading the web page pragmatically, as we;ve done here, we're just getting the raw html
 * So your application will then need to parse the html and pick out specific tags or other information depending on what we're trying to
 *  achieve
 *
 * So, this is one way to read a web page
 *
 *
 * ////////////////////////
 * 2. URLConnection class
 * ////////////////////////
 *
 *
 * The second way is to use a URLConnection class
 *
 * Create a URLConnection obj by calling openConnection() on the URL instance
 *
 *      URLConnection urlConnection = exampleOrgUrl.openConnection();
 *
 * We then call URLConnection.connect() to connect to the URL
 *
 * Catch MalformedURLException
 *
 *      urlConnection.connect();
 *
 * We might be wondering here, why are the 2 steps required ?
 *  - Why do we have to call openConnection and the connect()
 *
 * This is because the openConnection() doesn't connect to the URL,  but rather returns a URLConnection instance
 * Then we can use the instance to configure the connection
 * We can say whether we want to read/write to the connection , whether the caching will be used, if that's available for the protocol
 *  you're using, and so on
 * In other words, it gives you a chance to set any configuration settings that will ultimately influence how the connection's made
 *
 * So we might call in the methods between the openConnection() and connect() method calls
 * By default, you can only read from a connection,
 * And if you to write to a connection, we have to call URLConnection.setDoOutput() before you can call the connect()
 * So let's call URLConnection.setDoOutput() right after we have opened the connection just to pretend that we're configuring something
 *  and pass true
 *
 *      URLConnection.setDoOutput(true);
 *
 * One thing to keep in mind is that once, you've called the connect() , any attempts to set values on the connection after that call will
 *  result in an error
 * The point here is to make sure, you do any configuration after opening the connection but before calling the connect()
 *
 * When using URLConnection class, the URL you're connecting to doesn't have to be a web page or a web location and you don't have to be
 *  using http scheme
 *
 * But many of the methods in the class are only applicable to HttpConnections
 *
 * As we'll see, we'll actually use a subclass of URLConnection when connecting to a web URL rather than using the URLConnection directly
 * But it's also good to use a more generic class to read a web page
 *
 * Finally, we need to call urlConnection.getInputStream() and pass this to InputStreamReader constructor
 *
 * I have created a separate method: printWebsiteContent_2(URLConnection urlConnection)
 *  - Which creates a BufferedReader instance but then we need to call urlConnection.getInputStream() and pass that to InputStreamReader
 *   constructor
 *  - Reading the webpage would be similar to reading a file
 *
 *
 * /////
 * So, basically the code is very similar than when we read directly using an InputStreamReader except for URLConnection.getInputStream()
 *
 * The rest of the code is pretty identical and if we run this code,
 *
 * We can see we get the same results of the html correctly downloaded
 *
 */
public class Main {

    public static void main(String[] args) {

        try {
            URI baseUri = new URI("http://username:password@myserver.com:5000");

            // change to mynewserver.com
            baseUri = new URI("http://username:password@mynewserver.com:5000");

            URI relativeUri_1 = new URI("/catalogue/phones?os=android#samsung");
            URI relativeUri_2 = new URI("/catalogue/tvs?manufacturer=samsung");
            URI relativeUri_3 = new URI("/stores/locations?zip=12345");

            URI absoluteUri_1 = baseUri.resolve(relativeUri_1);
            URI absoluteUri_2 = baseUri.resolve(relativeUri_2);
            URI absoluteUri_3 = baseUri.resolve(relativeUri_3);

            URL absoluteUrl_1 = absoluteUri_1.toURL();
            URL absoluteUrl_2 = absoluteUri_2.toURL();
            URL absoluteUrl_3 = absoluteUri_3.toURL();

            System.out.println("URL 1 = " + absoluteUrl_1);
            System.out.println("URL 2 = " + absoluteUrl_2);
            System.out.println("URL 3 = " + absoluteUrl_3);

            // Relativized URI
            // Determine a relative URI when you know the base URI and the absolute URI using uri.relativized()

            URI relativizedURI = baseUri.relativize(absoluteUri_2);
            System.out.println("Relativized URI = " + relativizedURI);


            // Get contents of a WebPage : https://example.org/
            URL exampleOrgUrl = new URL("https://example.org/");

            // convert URL to URI
            URI exampleOrgUri = exampleOrgUrl.toURI();

            // Fetch parts of https://example.org/ URI
            System.out.println("Scheme = " + exampleOrgUri.getScheme());
            System.out.println("Scheme-specific-part = " + exampleOrgUri.getSchemeSpecificPart());
            System.out.println("Authority = " + exampleOrgUri.getAuthority());
            System.out.println("User Info = " + exampleOrgUri.getUserInfo());
            System.out.println("Host = " + exampleOrgUri.getHost());
            System.out.println("Port = " + exampleOrgUri.getPort());
            System.out.println("Path = " + exampleOrgUri.getPath());
            System.out.println("Query = " + exampleOrgUri.getQuery());
            System.out.println("Fragment = " + exampleOrgUri.getFragment());


            System.out.println("_".repeat(50));

            // Read Directly from the https://example.org/ using InputStreamReader
            // Using openStream()
            printWebsiteContent(exampleOrgUrl);

            // Reading via URLConnection class
            System.out.println("_".repeat(50));
            URLConnection urlConnection = exampleOrgUrl.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            printWebsiteContent_2(urlConnection);

        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printWebsiteContent(URL exampleOrgUrl) throws IOException{
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(exampleOrgUrl.openStream()))) {
            String line;
            while (true){
                line = reader.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
        }
    }

    private static void printWebsiteContent_2(URLConnection urlConnection) throws IOException{
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
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
