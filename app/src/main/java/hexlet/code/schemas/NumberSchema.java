package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean notNull = false;
    private boolean positive = false;
    private Integer[] range;

    public boolean isValid(Object o) {
        if (o instanceof Integer || o == null) {
            Integer checkNumber = (Integer) o;
            boolean result = true;
            if (notNull) {
                if (!super.isValid(checkNumber)) {
                    return false;
                }
            } else {
                if (checkNumber == null) {
                    return true;
                }
            }
            if (positive) {
                result = result && checkNumber > 0;
            }
            if (range != null) {
                Integer begin = range[0];
                Integer end = range[1];
                result = result && checkNumber >= begin && checkNumber <= end;
            }
            return result;
        }
        return false;
    }

    public NumberSchema required() {
        this.notNull = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int begin, int end) {
        this.range = new Integer[]{begin, end};
        return this;
    }
}
