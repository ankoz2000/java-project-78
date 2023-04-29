package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommonTest {
    private StringSchema stringSchema;
    private NumberSchema numberSchema;

    @BeforeEach
    public void getValidatorAndLoadSchemas() {
        Validator v = new Validator();
        this.stringSchema = v.string();
        this.numberSchema = v.number();
    }

    @Test
    public void stringSchemaTest() {
        Assertions.assertTrue(stringSchema.notNull(""));
        Assertions.assertTrue(stringSchema.notNull(null));

        stringSchema.required();

        Assertions.assertFalse(stringSchema.notNull(""));
        Assertions.assertFalse(stringSchema.notNull(null));
        Assertions.assertFalse(stringSchema.notNull(5));

        Assertions.assertTrue(stringSchema.notNull("what does the fox say"));
        Assertions.assertTrue(stringSchema.notNull("hexlet"));

        Assertions.assertTrue(stringSchema.contains("wh").notNull("what does the fox say"));
        Assertions.assertTrue(stringSchema.contains("what").notNull("what does the fox say"));

        Assertions.assertFalse(stringSchema.contains("whatthe").notNull("what does the fox say"));
    }

    @Test
    public void numberSchemaTest() {
        Assertions.assertTrue(numberSchema.notNull(null));
        Assertions.assertTrue(numberSchema.positive().notNull(null));

        numberSchema.required();

        Assertions.assertFalse(numberSchema.notNull(null));
        Assertions.assertFalse(numberSchema.notNull("5"));
        Assertions.assertTrue(numberSchema.notNull(10));

// Потому что ранее мы вызвали метод positive()
        Assertions.assertFalse(numberSchema.notNull(-10));
//  Ноль — не положительное число
        Assertions.assertFalse(numberSchema.notNull(0));

        numberSchema.range(5, 10);

        Assertions.assertTrue(numberSchema.notNull(5));
        Assertions.assertTrue(numberSchema.notNull(10));

        Assertions.assertFalse(numberSchema.notNull(4));
        Assertions.assertFalse(numberSchema.notNull(11));
    }
}
