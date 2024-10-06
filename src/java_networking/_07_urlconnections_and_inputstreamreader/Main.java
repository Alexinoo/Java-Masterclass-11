package java_networking._07_urlconnections_and_inputstreamreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/*
 * URL Connections and Input Stream Reader
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
