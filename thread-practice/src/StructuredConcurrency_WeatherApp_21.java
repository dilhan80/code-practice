// File: WeatherApp.java
import java.time.Duration;
import java.util.concurrent.*;

public class StructuredConcurrency_WeatherApp_21 {

    // Simulate 3 weather APIs
    static String getWeatherFromApiA() {
        sleep(2000);
        return "API-A: 28°C, Sunny";
    }

    static String getWeatherFromApiB() {
        sleep(1000);
        return "API-B: 27°C, Cloudy";
    }

    static String getWeatherFromApiC() {
        sleep(3000);
        throw new RuntimeException("API-C down!");
    }

    // Main method
    public static void main(String[] args) throws Exception {
        System.out.println("Fetching weather from 3 APIs...");

        String result = fetchFastestWeather();

        System.out.println("Result: " + result);
    }

    // Structured Concurrency – Return FASTEST result
    static String fetchFastestWeather() throws Exception {
        /****
         * THIS CODE HAS NOT BEEN TESTED DUE TO 'STRUCTURED CONCURRENCY' IS A PREVIEW FEATURE
         ****/
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {

            // Fork 3 tasks
            scope.fork(StructuredConcurrency_WeatherApp_21::getWeatherFromApiA);
            scope.fork(StructuredConcurrency_WeatherApp_21::getWeatherFromApiB);
            scope.fork(StructuredConcurrency_WeatherApp_21::getWeatherFromApiC);

            scope.join();  // Wait until one succeeds

            // Return the first successful result
            return scope.result();  // Throws if all failed
        }
    }

    // Helper
    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
