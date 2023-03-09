import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    public final static String apiKey = "ac6b2d9d5637408890e32801232802";

    // TODO: Write main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the city name :");
        String city = sc.next();

        String weatherData = getWeatherData(city);
        double temperature = getTemperature(weatherData);
        int humidity = getHumidity(weatherData);
        double windSpeed = getWindSpeed(weatherData);
        String windDirection = getWindDirection(weatherData);

        System.out.println("The temperature in " + city + " is " + temperature);
        System.out.println("The humidity percentage in " + city + " is " + humidity);
        System.out.println("The wind speed in " + city + " is " + windSpeed);
        System.out.println("The wind direction in " + city + " is " + windDirection);
    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){
        double answer = 0.0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getInt("humidity");
        return answer;
    }
    public static double getWindSpeed(String weatherJson){
        double answer = 0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getDouble("wind_kph");
        return answer;
    }
    public static String getWindDirection(String weatherJson){
        String answer = "";
        JSONObject data = new JSONObject(weatherJson);
        String direction = data.getJSONObject("current").getString("wind_dir");

        // convert short-form directions to long ones.
        if (direction.length() == 1) {
            if (direction.equals("N")) {
                answer = "north";
            }
            else if (direction.equals("S")) {
                answer = "south";
            }
            else if (direction.equals("W")) {
                answer = "west";
            }
            else if (direction.equals("E")) {
                answer = "east";
            }
        }
        else if (direction.length() == 2) {
            if (direction.equals("NE")) {
                answer = "northeast";
            }
            if (direction.equals("NW")) {
                answer = "northwest";
            }
            if (direction.equals("SE")) {
                answer = "southeast";
            }
            if (direction.equals("SW")) {
                answer = "southwest";
            }
        }
        else {
            if (direction.equals("NNE")) {
                answer = "north-northeast";
            }
            if (direction.equals("NNE")) {
                answer = "north-northeast";
            }
            if (direction.equals("ENE")) {
                answer = "east-northeast";
            }
            if (direction.equals("ESE")) {
                answer = "east-southeast";
            }
            if (direction.equals("SSE")) {
                answer = "south-southeast";
            }
            if (direction.equals("SSW")) {
                answer = "south-southwest";
            }
            if (direction.equals("WSW")) {
                answer = "west-southwest";
            }
            if (direction.equals("WNW")) {
                answer = "west-northwest";
            }
        }
        return answer;
    }
}
