package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonTest {
    private Validator v;
    private StringSchema stringSchema;

    @BeforeEach
    public void getValidatorAndLoadSchemas() {
        this.v = new Validator();
        this.stringSchema = v.string();
    }

    @Test
    public void test1() {
        Assertions.assertTrue(stringSchema.isValid(""));
        Assertions.assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        Assertions.assertFalse(stringSchema.isValid(""));
        Assertions.assertFalse(stringSchema.isValid(null));

        Assertions.assertTrue(stringSchema.isValid("what does the fox say"));
        Assertions.assertTrue(stringSchema.isValid("hexlet"));

        Assertions.assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        Assertions.assertTrue(stringSchema.contains("what").isValid("what does the fox say"));

        Assertions.assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
    }
}
