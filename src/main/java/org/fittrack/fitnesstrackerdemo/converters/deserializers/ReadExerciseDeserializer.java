package org.fittrack.fitnesstrackerdemo.converters.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.fittrack.fitnesstrackerdemo.models.dtos.displaydtos.ReadExerciseDto;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ReadExerciseDeserializer extends StdDeserializer<ReadExerciseDto> {

    public ReadExerciseDeserializer() {
        this(null);
    }

    public ReadExerciseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ReadExerciseDto deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        String name = node.get("name").asText();

        Set<String> trainingCategories = new HashSet<>();
        Set<String> muscleGroups = new HashSet<>();

        ArrayNode arr = (ArrayNode) node.get("muscleGroups");
        for (JsonNode jsonNode : arr) {
            if (!jsonNode.has("name")) {
                muscleGroups.add(jsonNode.textValue());
            } else {
                muscleGroups.add(jsonNode.get("name").textValue());

            }
        }

//        if (!node.get("muscleGroups").get(0).has("name")) {
//            ArrayNode arr = (ArrayNode) node.get("muscleGroups");
//            for (JsonNode jsonNode : arr) {
//                muscleGroups.add(jsonNode.textValue());
//            }
//        } else {
//            ArrayNode arr = (ArrayNode) node.get("muscleGroups");
//            for (JsonNode jsonNode : arr) {
//                muscleGroups.add(jsonNode.get("name").textValue());
//            }
//        }

        arr = (ArrayNode) node.get("trainingCategories");
        for (JsonNode jsonNode : arr) {
            if (!jsonNode.has("name")) {
                trainingCategories.add(jsonNode.textValue());
            } else {
                trainingCategories.add(jsonNode.get("name").textValue());
            }
        }


//        if (!node.get("trainingCategories").get(0).has("name")) {
//            ArrayNode arr = (ArrayNode) node.get("trainingCategories");
//            for (JsonNode jsonNode : arr) {
//                trainingCategories.add(jsonNode.textValue());
//            }
//        } else {
//            ArrayNode arr = (ArrayNode) node.get("trainingCategories");
//            for (JsonNode jsonNode : arr) {
//                trainingCategories.add(jsonNode.get("name").textValue());
//            }
//        }

        return new ReadExerciseDto(name, muscleGroups, trainingCategories);
    }

}