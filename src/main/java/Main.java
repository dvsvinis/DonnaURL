//https://jsonplaceholder.typicode.com
//https://jsonplaceholder.typicode.com/albums
//http://zipcode.rocks:8085/ids

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String url = "http://zipcode.rocks:8085/ids";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int responseCode = con.getResponseCode();
//        System.out.println("Sending GET Request: " + url);
//        System.out.println("Response code " + responseCode);
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

//        System.out.println(response.toString());
        ObjectMapper mapper = new ObjectMapper();
        List<Id> idList = mapper.readValue(response.toString(), new TypeReference<List<Id>>(){});
        for (Id each:idList){
            System.out.println(each.toString());
        }

    }
}
