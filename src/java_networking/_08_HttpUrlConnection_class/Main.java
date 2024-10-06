package java_networking._08_HttpUrlConnection_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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
                List<String> value = entry.getValue();
                System.out.println("-----key = "+ key);

                for (String stringValue : value) {
                    System.out.println("value = "+stringValue);
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
