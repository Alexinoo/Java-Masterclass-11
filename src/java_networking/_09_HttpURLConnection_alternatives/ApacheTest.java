package java_networking._09_HttpURLConnection_alternatives;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;

public class ApacheTest {

    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://example.org/");
        request.addHeader("User-Agent","Chrome");

        CloseableHttpResponse response = null;

        try{

            response = httpClient.execute(request);
            System.out.println("Response code = "+ response.getStatusLine().getStatusCode());

            printWebsiteContent(response.getEntity().getContent());

        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }finally {
            try{
                if (response != null)
                    response.close();
            }catch (IOException e){
                System.out.println("IOException "+e.getMessage());
            }

        }

    }

    private static void printWebsiteContent(InputStream inputStream) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
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
