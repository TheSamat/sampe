package com.samat.money.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert an object to its JSON representation.
     *
     * @param object The object to be converted to JSON.
     * @return A JSON string representing the object.
     * @throws JsonProcessingException If there's an error during JSON serialization.
     */
    public String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Convert an object to its JSON representation with pretty formatting.
     *
     * @param object The object to be converted to JSON.
     * @return A pretty-printed JSON string representing the object.
     * @throws JsonProcessingException If there's an error during JSON serialization.
     */
    public String toPrettyJson(Object object) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}