package se.andresoderlund.example.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Component;
import se.andresoderlund.example.db.exception.BadRequestException;

@Component
public class JsonPatcher {

    private final ObjectMapper objectMapper;

    JsonPatcher(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T applyPatch(final JsonPatch patch, final Class<T> targetClass, final T target) {
        try {
            var patched = patch.apply(objectMapper.convertValue(target, JsonNode.class));
            return objectMapper.treeToValue(patched, targetClass);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new BadRequestException("Unable to patch entity with given patch");
        }
    }


}
