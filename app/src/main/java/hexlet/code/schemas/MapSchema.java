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
            if (size != null) {
                result = result && size == checkMap.size();
            }
            if (checkShapes != null) {
                result = checkEntries(checkMap, result);
            }
            return result;
        }
        return false;
    }

    private boolean checkEntries(Map<String, Object> checkMap, boolean result) {
        for (Map.Entry<String, Object> entry : checkMap.entrySet()) {
            for (Map.Entry<String, BaseSchema> shape : checkShapes.entrySet()) {
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

    public void sizeof(int requiredSize) {
        this.size = requiredSize;
    }

    public void shape(Map<String, BaseSchema> shapes) {
        this.checkShapes = shapes;
    }
}
