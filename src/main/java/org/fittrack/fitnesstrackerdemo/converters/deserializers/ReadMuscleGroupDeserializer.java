package org.fittrack.fitnesstrackerdemo.converters.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadMuscleGroupDto;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ReadMuscleGroupDeserializer extends StdDeserializer<ReadMuscleGroupDto> {

    public ReadMuscleGroupDeserializer() {
        this(null);
    }

    public ReadMuscleGroupDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public ReadMuscleGroupDto deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();
        int size = node.get("size").asInt();
        Set<String> exercises = new HashSet<>();

        ArrayNode arr = (ArrayNode) node.get("exercises");
        for (JsonNode jsonNode : arr) {
            if (!jsonNode.has("name")) {
                exercises.add(jsonNode.textValue());
            } else {
                exercises.add(jsonNode.get("name").textValue());

            }
        }

        return new ReadMuscleGroupDto(name, size, exercises);
    }
}
