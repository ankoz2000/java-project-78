package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {
    public MapSchema required() {
        addCheck("required", Map.class::isInstance);
        return this;
    }

    public void sizeof(int requiredSize) {
        addCheck("sizeof", o -> requiredSize == ((Map<String, Object>) o).size());
    }

    public void shape(Map<String, BaseSchema> shapes) {
        Predicate predicate = getPredicate(shapes);
        addCheck("shape", predicate);
    }

    private Predicate getPredicate(Map<String, BaseSchema> shapes) {
        return o -> ((Map<String, Object>) o).entrySet().stream()
                .allMatch(e -> shapes.entrySet().stream()
                        .allMatch(c -> checkKeysAndValidValues(e, c)));
    }

    private boolean checkKeysAndValidValues(Map.Entry<String, Object> e, Map.Entry<String, BaseSchema> c) {
        if (c.getKey().equals(e.getKey())) {
            return c.getValue().isValid(e.getValue());
        }
        return true;
    }
}
