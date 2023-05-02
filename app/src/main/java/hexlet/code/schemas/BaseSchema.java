package hexlet.code.schemas;

public abstract class BaseSchema {
    public boolean isValid(Object checkObject) {
        return checkObject != null;
    }
}
