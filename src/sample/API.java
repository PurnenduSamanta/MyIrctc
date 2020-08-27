package sample;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class API {
    private HttpURLConnection connection;
    public String fetchDataFromUrl(String webURL)
    {
        StringBuffer sb=new StringBuffer();
        try {
            URL url=new URL(webURL);
            try {
                connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int status=connection.getResponseCode();
                if(status==200)
                {

                    Scanner fetch=new Scanner(new InputStreamReader(connection.getInputStream()));
                    while (fetch.hasNextLine())
                    {
                        sb.append(fetch.nextLine());
                    }
                    connection.disconnect();
                    return  sb.toString();
                }
                else
                {
                    return null;
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }






}
