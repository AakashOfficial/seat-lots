package net.devwurm.seatlots.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;

/**
 * Class for deserializing an JSON String into an StringProperty
 */
public class StringPropertyDeserializer extends JsonDeserializer<StringProperty> {
    @Override
    public StringProperty deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String stringValue = jsonParser.getValueAsString();

        return new SimpleStringProperty(stringValue);
    }
}
