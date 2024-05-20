package test12;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class as {
    public static void main(String[] args) throws Exception {
        String apiUrl = "https://iot-api.heclouds.com/thingmodel/query-device-property";
        String queryParameters = "?product_id=4aS7AhlV8X&device_name=circlecheck";
        String authorizationValue = "version=2022-05-01&res=userid%2F390250&et=1716217013&method=sha1&sign=RcydZVJ7puCgZHbkTpRVNpCsHIk%3D";

        // 组合url
        URL url = new URL(apiUrl + queryParameters);

        // 创建HTTP连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 添加Authorization头
        connection.setRequestProperty("Authorization", authorizationValue);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();

            System.out.println(content.toString());
        } else {
            System.out.println("error" + connection.getResponseCode());
        }

        connection.disconnect();
    }
}