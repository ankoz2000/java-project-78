package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected  Map<String, Predicate> predicates = new HashMap<>();

    public final boolean isValid(Object checkObject) {
        return predicates.entrySet().stream()
                .allMatch(p -> p.getValue().test(checkObject));
    }

    protected final void addCheck(String name, Predicate validate) {
        predicates.put(name, validate);
    }
}
