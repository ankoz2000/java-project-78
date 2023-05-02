package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean notNull = false;
    private Integer size;

    public boolean isValid(Object o) {
        if (o instanceof Map || o == null) {
            Map checkMap = (Map) o;
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
            return result;
        }
        return false;
    }

    public void required() {
        this.notNull = true;
    }

    public void sizeof(int size) {
        this.size = size;
    }
}
