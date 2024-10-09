package java_networking._06_high_level_APIs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/*
 * High Level APIs
 * ...............
 *
 *
 * We've looked at the Low-level API, and we saw that they involve ports and sockets
 *
 * While it's important to understand low-level API concepts, most modern apps are mainly interested in accessing the internet
 *  and they'll normally use the high level API to do so
 *
 * We'll look at the high level API which abstracts networking concepts even further
 * Using high level API, we don't actually even need to know about ports and sockets
 *
 * Instead, we use URI's (Universal Resource Identifier) and URL's (Universal Resource Locator)
 *
 *
 * What is the Difference between URL and URI ?
 *  - Follow the link below from w3c (World wide Web standards Consortium) to get clarity : https://www.w3.org/TR/uri-clarification/
 *
 * Might be confusing but let us stick to what we need to know to write java networking code
 *
 * When working with the java.net package :
 *  - a URI is an identifier that might not provide enough information to access resource it identifies
 *  - a URL is an identifier that includes information about how to access the resource it identifies
 *
 * Another way to state this is :
 *  - A URI can specify a relative path
 *  - A URL has to be an absolute path, because when we use the URL, it has to contain enough information to locate and access the resource
 *     it identifies
 *
 * ////
 *
 * It's easy to convert between URLs and URIs, and all we have to do is to provide what the method you want to use requires (a URL or a URI)
 * The recommended practice when working with the java.net classes is to use a URI until you actually want to access a resource, and then to
 *  convert the URI to a URL
 *
 * Sometimes there's no need to start with a URI because the method you'll use to open or access a resource accepts a URL right off the bat
 *
 *
 * /////
 *
 * A scheme is part of a URI or URL that appears before the colon
 * For example:
 *  - "http", "file" and "ftp" are all schemes
 * Another way to define a URL is it's an http URI
 * This terminology can be confusing and developers often use the terms URI and URL interchangeably, even though it's technically wrong to do
 * so
 * Or they use URI when they mean URL
 *
 * ///////
 *
 * According to some developers and documentation, the term URL is now outdated and shouldn't be used, but many developers and APIs still use it
 * Understanding sometimes razor-thin difference between a URI and a URL isn't necessary to get your feet wet with writing Networking code
 *
 * When working with low-level API, we used the following classes:
 *  - Socket
 *  - ServerSocket
 *  - DatagramSocket
 *
 * When working with high-level API, we'll use the following classes:
 *  - URI
 *  - URL
 *  - URLConnection
 *  - HttpURLConnection
 *
 * ////
 * We'll start on URI's but won't spend too much time on them because you will use most of the times URLs when accessing the Internet
 * A URI can contain 9 components:
 *  1. scheme
 *  2. scheme-specific-part
 *  3. authority
 *  4. user-info
 *  5. host
 *  6. port
 *  7. path
 *  8. query
 *  9. fragment
 *
 * //////
 * The generic form of a URI is as follows (taken from Wikipedia:
 *
 *      https://en.wikipedia.org/wiki/Uniform_Resource_identifier ):
 *
 *      scheme:[//[user[:password]@]host[:port]][/path][?query][#fragment]
 *
 * URIs that specify scheme are called absolute URIs
 * When a URI doesn't provide the scheme, it's called a relative URI
 *
 * ///////
 *
 * Create a URI below
 *
 *      URI uri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
 *
 * Print the individual components that we talked about that form the URI
 *
 *      System.out.println("Scheme = "+ uri.getScheme());
 *          - prints "Scheme = db"
 *
        System.out.println("Scheme-specific-part = "+ uri.getSchemeSpecificPart());
            - prints "Scheme-specific-part = //username:password@myserver.com:5000/catalogue/phones?os=android"

        System.out.println("Authority = "+ uri.getAuthority());
            - prints "Authority = username:password@myserver.com"

        System.out.println("User Info = "+ uri.getUserInfo());
            - prints "User Info = username:password"

        System.out.println("Host = "+ uri.getHost());
            - prints "Host = myserver.com"

        System.out.println("Port = "+ uri.getPort());
            - prints "Port = 5000"

        System.out.println("Path = "+ uri.getPath());
            - prints "Path = /catalogue/phones"

        System.out.println("Query = "+uri.getQuery());
            - prints "Query = os=android"

        System.out.println("Fragment = "+uri.getFragment());
            - prints "Fragment = samsung"
 *
 * Catch URISyntaxException
 *  - Print some message to the user in case we get one
 *
 *
 * Explanation
 * ///////////
 *
 * Scheme
 * //////
 * The scheme equals db , the part that actually occurs before the colon in the URI
 *
 * We can write our own schemes, but we're encouraged not to do so to avoid name conflicts
 * Usually when we want to find out our own schemes, there's alternative ways to accomplish what we want to do by using namespace for
 *  example
 *
 * Scheme-specific-part
 * ////////////////////
 * This is everything after the colon before the # (hash)
 * It's the identifier for the resource that you want to use - in this case, it's a query for the phones table of the phones table in the
 *  catalogue database
 *
 *
 * Authority
 * /////////
 * The authority contains the host and optionally a password as well as the username and port no
 * The host can be a registered name, for example, or an IPv4/IPv6 address
 *
 *
 * User-info
 * /////////
 * Contains the username and password - basically any credentials required to access the resource
 *
 *
 * Host
 * ////
 * This is the host name or an IPv4 or IPv6 address
 *
 *
 * Port
 * ////
 * Is obviously the port number
 *
 *
 * Path
 * ////
 * The path is /catalog/phones and that's the path to the resource on the host
 * In this case the resource is the database and the path points to a specific table within the database
 *
 *
 * Query
 * /////
 * The query is always separated from the path that comes before it by a question mark
 * The syntax can be in any format, because the standard doesn't strictly define it, but it's often a set of key value pairs
 *
 *
 * Fragment
 * ////////
 * specifies a secondary resource or location
 * so, in this case it's saying that only Samsung phones should be returned
 * If the URI point to a web page, the fragment could be an id for a section heading for example in the html itself
 *
 *
 * As noticed, there's overlap between the contents of some of these components and also many of the components are OPTIONAL
 *
 * Let's change our URI to something completely different to test the above
 *
 *      URI uri = new URI("hello");
 *
 * IF we run this, notice that there's no exceptions thrown,
 *      - We only get Scheme-specific-part and path which prints "hello"
 * The rest of the components are null except the Port which is printed as -1
 * The point here is that it's still a well-formed URI, it doesn't have a scheme part because there's no colon but it has a path and a
 *  scheme-specific-part
 * Now of course, this resource doesn't exist but that doesn't really matter , just as with files, until you actually try to access the
 *  resource , it doesn't have to exist
 * And remember that you'll use a URL arther than a URI when you actually access a resource
 *
 * /////////////////////
 * Convert URIs to URLs uri.toURL()
 *
 * We call toURL() on the URI instance to convert it to a URL
 * This returns a URL instance
 *
 * Let's try to convert URI below to a URL
 *
 *      URI uri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
 *
 *      URL url = uri.toURL()
 *
 * uri.toURL() throws MalformedURLException which we need to catch and throw an error if one is thrown
 *
 * However, if we run this, we get an MalformedURLException on the above URI conversion to URL - unknown protocol db
 * This demonstrates that the URI doesn't have to be valid to work with it
 * It only has to be valid if you want to convert it into an absolute URL as we're trying to do here
 *
 *
 * ////
 * Let's change this scheme to something that we're much familiar with
 *
 *      URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
 *
 * And now if we try to convert this
 *
 *      URL url = uri.toURL()
 *
 *      System.out.println("URL = "+url);
 *
 * We get the following URL printed to the console , which is now equal to the URI, because the URI is an absolute path
 *
 *      http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung
 *
 *
 *
 * //////////
 * Relative URI
 *
 * Let's now look at a relative URI,
 *
 *      URI relativeUri = new URI("/catalogue/phones?os=android#samsung");
 *
 * This is a relative URI because it doesn't completely identify the resource
 * In other words, it doesn't contain the schema and we can't tell if the URI points to a web page or a database or a file or perhaps
 *  something else, it doesn't contain the root location
 * So, if this was a web page, the URL doesn't contain the server or any credentials required to access the location
 * It also doesn't specify a database table or a disk driver location
 * In other words, there isn't enough information here to really access the resource
 *
 * ////
 * Quiz - Can we convert the above relative URI to a URL ?
 *
 *      URL url = relativeUri.toURL();
 *
 * Well, let's find out and see what happens
 * We actually get an IllegalArgumentException which we are not handling at the moment
 * And so URI is not absolute
 *
 * Tim said earlier that URIs can be relative, but URLs have to be absolute and so the exception makes sense here
 * Because we're converting to a URL, it's not going to be a valid , unless the URL ends up being an absolute URL
 *
 * So, we can't get an absolute URL form a relative URI because there isn't enough information in the URI to determine the
 *  absolute location of the resource
 *
 * When you want to access a resource, you'll use a URL
 * At that point, the location of the resource has to be absolute
 * Otherwise, Java runtime won't have enough information ultimately to access it
 * There will be times, where you want to use relative URIs though but usually you'll use them along with a base URI
 *
 * ////////
 * Base URI
 *
 * The base URI will specify the root of the relative path which can be quite handy
 * If you're accessing lots of pages on a website and instead of working with absolute URIs, it's probably better to have a base
 *  URI that contains the host information and a bunch of relative URIs for the specific pages
 *
 * Therefore, in that scenario, if the host location changes, you really only have to update the baseURI
 *
 * Let's say for example, say that the host is located at http.example.com but later on it changes to http.example.org
 * If you've used absolute URIs throughout your code, you'll have to go through your code and change each and every URI instance
 *
 * But if you've relative URIs with a base URI, then you really only have to change the 1 instance of the base URI
 * Let's add the base URI for our relative URL
 *
 *      URI baseUri = new URI("http://username:password@myserver.com:5000");
 *
 * Now that we've done that , we can make a change
 * We'll create a 3rd URI and call it absoluteUri
 *
 *      URI absoluteUri = baseUri.resolve(relativeUri);
 *
 * And then convert absoluteUri into URL
 *
 *      URL absoluteUrl = absoluteUri.toURL();
 *
 * And print the resolved URL as follows
        System.out.println("URL = "+ absoluteUrl);

        http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung
 *
 * Basically when you want to access a web page on the myserver.com host , you'll combine the base URI with the relative URI for the
 *  web page or API or service you want to access using uri.resolve()
 *
 * The resolve() in this case appends relativeURI to the baseURI to form a valid absolute URI
 * And then later convert it into a URL
 *
 */
public class Main {


    public static void main(String[] args) {
        try {
            //URI uri = new URI("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
            // URI uri = new URI("hello");
            //URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");

            // Relative URI
            URI relativeUri = new URI("/catalogue/phones?os=android#samsung");
            URI baseUri = new URI("http://username:password@myserver.com:5000");

            URI absoluteUri = baseUri.resolve(relativeUri);

            //URL url = uri.toURL();
            URL absoluteUrl = absoluteUri.toURL();
            System.out.println("URL = "+ absoluteUrl);

            /*

            System.out.println("Scheme = "+ uri.getScheme());
            System.out.println("Scheme-specific-part = "+ uri.getSchemeSpecificPart());
            System.out.println("Authority = "+ uri.getAuthority());
            System.out.println("User Info = "+ uri.getUserInfo());
            System.out.println("Host = "+ uri.getHost());
            System.out.println("Port = "+ uri.getPort());
            System.out.println("Path = "+ uri.getPath());
            System.out.println("Query = "+uri.getQuery());
            System.out.println("Fragment = "+uri.getFragment());

             */
        } catch (URISyntaxException e) {
            System.out.println("URI Bad Syntax: "+e.getMessage());
        }catch (MalformedURLException e){
            System.out.println("URL Malformed "+e.getMessage());
        }
    }
}
