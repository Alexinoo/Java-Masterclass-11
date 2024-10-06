package java_networking._09_HttpURLConnection_alternatives;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
