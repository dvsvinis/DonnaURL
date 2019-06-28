import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/** Create a URL.
Retrieve the URLConnection object.
Set output capability on the URLConnection.
Open a connection to the resource.
Get an output stream from the connection.
Write to the output stream.**/

public class PostRequest {

    public static void main(String[] args) throws IOException {

        String jsonInputString = "{\n" +
                "\t\"userid\": \"\",\n" +
                "\t\"name\": \"Donna3Test\",\n" +
                "\t\"github\": \"DonnaTest\"\n" +
                "}";
        //      String jsonInputString = "{userid:- ,'name:testingDVtest', github:dvstestingtest'}";
        //       String jsonInputString = "{ "userid": "-", "name": "DVSTest", "github": "DVSTest" }";


        URL url = new URL("http://zipcode.rocks:8085/ids");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);// Triggers POST.
        con.setRequestProperty("Accept-Charset", "UTF-8");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        try (OutputStream output = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            output.write(input, 0, input.length);
            output.close();
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

//
//         OutputStreamWriter output = new OutputStreamWriter(
//                con.getOutputStream());
//        output.write(jsonInputString);
//        output.close();
//
//    }

    }

}
