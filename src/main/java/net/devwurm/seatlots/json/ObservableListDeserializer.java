package net.devwurm.seatlots.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.devwurm.seatlots.location.configuration.RoomConfiguration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for deserializing an JSON Array into an observable list
 */
public class ObservableListDeserializer extends JsonDeserializer<ObservableList<RoomConfiguration>> {
    @Override
    public ObservableList<RoomConfiguration> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        ObjectMapper mapper = new ObjectMapper();

        CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, RoomConfiguration.class);
        ArrayList<RoomConfiguration> arrayListResult =  mapper.readValue(node.toString(), type);

        ObservableList<RoomConfiguration> result = FXCollections.observableArrayList();
        result.setAll(arrayListResult);

        return result;
    }
}
