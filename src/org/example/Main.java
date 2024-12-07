package org.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] apiEndpoints = {
                "https://fake-json-api.mock.beeceptor.com/companies",
                "https://fake-json-api.mock.beeceptor.com/users",
                "https://dummy-json.mock.beeceptor.com/todos",
                "https://dummy-json.mock.beeceptor.com/posts",
                "https://dummy-json.mock.beeceptor.com/continents"
        };

        try {
            Parser dataParser = new Parser(apiEndpoints[0]);

            for (String endpoint : apiEndpoints) {
                dataParser.updateURL(endpoint);
                List<Map<String, Object>> responseData = dataParser.fetchParsedData();

                System.out.println("=== Data retrieved from: " + endpoint + " ===");

                for (Map<String, Object> record : responseData) {
                    record.forEach((key, value) -> System.out.println(key + ": " + value));
                    System.out.println("-".repeat(100));
                }
                System.out.println();
            }
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL provided: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        }
    }
}
