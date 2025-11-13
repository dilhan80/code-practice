// File: ResilientWeatherApp.java
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class StructuredConcurrency_ResilientWeatherApp_21{

    // Simulate 3 APIs
    static String getWeatherA() {
        sleep(1000);
        return "API-A: 28°C Sunny";
    }

    static String getWeatherB() {
        sleep(1500);
        throw new RuntimeException("API-B timeout!");
    }

    static String getWeatherC() {
        sleep(2000);
        return "API-C: 27°C Cloudy";
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Fetching weather from 3 APIs (resilient mode)...\n");

        var results = fetchAllWeatherResiliently();

        results.forEach((api, result) ->
                System.out.println(api + " → " + result)
        );
    }

    // Run ALL tasks — even if one fails
    static java.util.Map<String, String> fetchAllWeatherResiliently() throws InterruptedException {
        /****
         * THIS CODE HAS NOT BEEN TESTED DUE TO 'STRUCTURED CONCURRENCY' IS A PREVIEW FEATURE
         ****/
        // Use plain StructuredTaskScope (no auto-shutdown)
//        try (var scope = new StructuredTaskScope<String>()) {

            // Fork all tasks
//            Future<String> futureA = scope.fork(StructuredConcurrency_ResilientWeatherApp_21::getWeatherA);
//            Future<String> futureB = scope.fork(StructuredConcurrency_ResilientWeatherApp_21::getWeatherB);
//            Future<String> futureC = scope.fork(StructuredConcurrency_ResilientWeatherApp_21::getWeatherC);
//
//            scope.join();  // Wait for ALL to finish (success or fail)

            // Collect results — handle success/failure per task
//            return java.util.Map.of(
//                    "API-A", getResultOrError(futureA),
//                    "API-B", getResultOrError(futureB),
//                    "API-C", getResultOrError(futureC)
//            );
//        }
        return null;
    }

    // Helper: Get result or error message
    static String getResultOrError(Future<String> future) {
        try {
            return future.resultNow();  // Success
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
