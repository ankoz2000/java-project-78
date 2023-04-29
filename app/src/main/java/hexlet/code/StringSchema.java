package hexlet.code;

public class StringSchema {
    private boolean notNull = false;
    private String substring;
    private Integer minLength;
    private Integer maxLength;

    public boolean isValid(String checkString) {
        boolean result = true;
        if (notNull) {
            result = result && (checkString != null && !checkString.isEmpty());
        }
        if (minLength != null) {
            result = result && checkMinLength(checkString);
        }
        if (maxLength != null) {
            result = result && checkMaxLength(checkString);
        }
        if (substring != null) {
            result = result && checkString.contains(substring);
        }
        return result;
    }

    public StringSchema required() {
        this.notNull = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema maxLength(int length) {
        this.maxLength = length;
        return this;
    }

    private boolean checkMinLength(String string) {
        return string.length() >= minLength;
    }

    private boolean checkMaxLength(String string) {
        return string.length() <= maxLength;
    }

    public StringSchema contains(String string) {
        this.substring = string;
        return this;
    }
}
