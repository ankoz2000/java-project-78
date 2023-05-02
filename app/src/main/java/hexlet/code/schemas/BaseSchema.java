package hexlet.code.schemas;

public abstract class BaseSchema {
    public abstract boolean isValid(Object checkObject);

    public final boolean notNull(Object checkObject) {
        return checkObject != null;
    }
}
