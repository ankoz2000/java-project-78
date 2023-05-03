package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private boolean notNull = false;
    private Integer size;
    private Map<String, BaseSchema> checkShapes;

    public boolean isValid(Object o) {
        if (o instanceof Map || o == null) {
            Map<String, Object> checkMap = (Map<String, Object>) o;
            boolean result = true;
            if (notNull) {
                if (!super.notNull(checkMap)) {
                    return false;
                }
            } else {
                if (checkMap == null) {
                    return true;
                }
            }
            result = checkSize(checkMap, result);
            result = checkShapes(checkMap, result);
            return result;
        }
        return false;
    }

    private boolean checkShapes(Map<String, Object> checkMap, boolean result) {
        if (checkShapes != null) {
            result = checkEntries(checkMap, result);
        }
        return result;
    }

    private boolean checkSize(Map<String, Object> checkMap, boolean result) {
        if (size != null) {
            result = result && size == checkMap.size();
        }
        return result;
    }

    private boolean checkEntries(Map<String, Object> checkMap, boolean result) {
        for (Map.Entry<String, Object> entry : checkMap.entrySet()) {
            for (Map.Entry<String, BaseSchema> shape : checkShapes.entrySet()) {
                result = checkValidEntries(result, entry, shape);
            }
        }
        return result;
    }

    private boolean checkValidEntries(boolean result, Map.Entry<String, Object> entry,
                                                    Map.Entry<String, BaseSchema> shape) {
        if (entry.getKey().equals(shape.getKey())) {
            result = result && shape.getValue().isValid(entry.getValue());
        }
        return result;
    }

    public MapSchema required() {
        this.notNull = true;
        return this;
    }

    public void sizeof(int requiredSize) {
        this.size = requiredSize;
    }

    public void shape(Map<String, BaseSchema> shapes) {
        this.checkShapes = shapes;
    }
}
