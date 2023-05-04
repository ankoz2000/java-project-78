package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    public StringSchema required() {
        addCheck("required", o -> (o instanceof String str) && !str.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", o -> isLengthLessThanMax(length, (String) o));
        return this;
    }

    private boolean isLengthLessThanMax(int length, String o) {
        return o.length() >= length;
    }

    public StringSchema maxLength(int length) {
        addCheck("maxLength", o -> isLengthGreatThanMin(length, (String) o));
        return this;
    }

    private boolean isLengthGreatThanMin(int length, String o) {
        return o.length() >= length;
    }

    public StringSchema contains(String string) {
        addCheck("contains", o -> ((String) o).contains(string));
        return this;
    }
}
