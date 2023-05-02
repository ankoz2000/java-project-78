package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private boolean notNull = false;
    private String substring;
    private Integer minLength;
    private Integer maxLength;

    public boolean isValid(Object o) {
        if (o instanceof String || o == null) {
            String checkString = (String) o;
            boolean result = true;
            if (notNull) {
                if (!super.notNull(checkString)) {
                    return false;
                }
                result = !checkString.isEmpty();
            } else {
                if (checkString == null) {
                    return true;
                }
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
        return false;
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
