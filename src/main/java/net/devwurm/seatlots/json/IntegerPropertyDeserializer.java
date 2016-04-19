package net.devwurm.seatlots.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;

/**
 * Class for deserializing an JSON integer into an IntegerProperty
 */
public class IntegerPropertyDeserializer extends JsonDeserializer<IntegerProperty> {
    @Override
    public IntegerProperty deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String stringValue = jsonParser.getValueAsString();
        ObjectMapper mapper = new ObjectMapper();

        Integer integer = mapper.readValue(stringValue, Integer.class);

        return new SimpleIntegerProperty(integer);
    }
}
