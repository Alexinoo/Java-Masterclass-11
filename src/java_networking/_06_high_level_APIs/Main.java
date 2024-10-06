package java_networking._06_high_level_APIs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/*
 * High Level APIs
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
