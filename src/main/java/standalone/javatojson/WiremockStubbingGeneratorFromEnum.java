package standalone.javatojson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum UrlPathEnum {
    TSY1,
    TSY2
}

public class WiremockStubbingGeneratorFromEnum {

    public static JsonObject generateMappingsJson(UrlPathEnum[] urlPaths) {
        JsonObject mappingsJson = new JsonObject();
        JsonArray mappingsArray = new JsonArray();

        for (UrlPathEnum endpoint : urlPaths) {
            JsonObject mappingObject = new JsonObject();

            JsonObject requestObject = new JsonObject();
            requestObject.addProperty("method", endpoint.toString());
            String url = replaceWithRegex(endpoint.toString());
            System.out.println(url);
            requestObject.addProperty("urlPathPattern", url);

            JsonObject responseObject = new JsonObject();
            responseObject.addProperty("status", 200);
            responseObject.addProperty("bodyFileName", endpoint.name().toLowerCase() + "_response.json");

            JsonArray transformersArray = new JsonArray();
            transformersArray.add("response-template");
            responseObject.add("transformers", transformersArray);

            JsonObject headersObject = new JsonObject();
            headersObject.addProperty("Content-Type", "application/json");
            responseObject.add("headers", headersObject);

            mappingObject.add("request", requestObject);
            mappingObject.add("response", responseObject);

            mappingsArray.add(mappingObject);
        }

        mappingsJson.add("mappings", mappingsArray);

        return mappingsJson;
    }

    public static String replaceWithRegex(final String input) {
        String regex = "\\{.*?\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, ".*");
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public static void main(String[] args) {
        JsonObject mappingsJson = generateMappingsJson(UrlPathEnum.values());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(mappingsJson);
        System.out.println(jsonOutput);
    }
}

