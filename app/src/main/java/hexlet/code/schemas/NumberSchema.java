package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        addCheck("required", o -> (o instanceof Integer));
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", o -> o == null || (o instanceof Integer val) && val > 0);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        addCheck("range", o -> ((Integer) o) >= begin && ((Integer) o) <= end);
        return this;
    }
}
