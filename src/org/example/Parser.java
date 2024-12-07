package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;

public class Parser {
    private URL resourceURL;
    private final Gson jsonConverter;

    public Parser(String initialUrl) throws MalformedURLException {
        this.resourceURL = new URL(initialUrl);
        this.jsonConverter = new Gson();
    }

    public List<Map<String, Object>> fetchParsedData() throws IOException {
        StringBuilder rawResponse = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceURL.openStream()))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                rawResponse.append(currentLine);
            }
        }

        String cleanedResponse = StringEscapeUtils.unescapeHtml4(rawResponse.toString());

        Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
        return jsonConverter.fromJson(cleanedResponse, listType);
    }

    public void updateURL(String newUrl) throws MalformedURLException {
        this.resourceURL = new URL(newUrl);
    }
}
