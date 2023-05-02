package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean notNull = false;
    private Integer size;
    private Map<String, BaseSchema> shapes;

    public boolean isValid(Object o) {
        if (o instanceof Map || o == null) {
            Map<String, Object> checkMap = (Map<String, Object>) o;
            boolean result = true;
            if (notNull) {
                if (!super.isValid(checkMap)) {
                    return false;
                }
            } else {
                if (checkMap == null) {
                    return true;
                }
            }
            if (size != null) {
                result = result && size == checkMap.size();
            }
            if (shapes != null) {
                result = checkEntries(checkMap, result);
            }
            return result;
        }
        return false;
    }

    private boolean checkEntries(Map<String, Object> checkMap, boolean result) {
        for (Map.Entry<String, Object> entry : checkMap.entrySet()) {
            for (Map.Entry<String, BaseSchema> shape : shapes.entrySet()) {
                if (entry.getKey().equals(shape.getKey())) {
                    result = result && shape.getValue().isValid(entry.getValue());
                }
            }
        }
        return result;
    }

    public MapSchema required() {
        this.notNull = true;
        return this;
    }

    public void sizeof(int size) {
        this.size = size;
    }

    public void shape(Map<String, BaseSchema> shapes) {
        this.shapes = shapes;
    }
}
