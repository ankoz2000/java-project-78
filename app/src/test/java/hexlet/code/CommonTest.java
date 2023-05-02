package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CommonTest {
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private MapSchema mapSchema;

    @BeforeEach
    public void getValidatorAndLoadSchemas() {
        Validator v = new Validator();
        this.stringSchema = v.string();
        this.numberSchema = v.number();
        this.mapSchema = v.map();
    }

    @Test
    public void stringSchemaTest() {
        Assertions.assertTrue(stringSchema.isValid(""));
        Assertions.assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        Assertions.assertFalse(stringSchema.isValid(""));
        Assertions.assertFalse(stringSchema.isValid(null));
        Assertions.assertFalse(stringSchema.isValid(5));

        Assertions.assertTrue(stringSchema.isValid("what does the fox say"));
        Assertions.assertTrue(stringSchema.isValid("hexlet"));

        Assertions.assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        Assertions.assertTrue(stringSchema.contains("what").isValid("what does the fox say"));

        Assertions.assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
    }

    @Test
    public void numberSchemaTest() {
        Assertions.assertTrue(numberSchema.isValid(null));
        Assertions.assertTrue(numberSchema.positive().isValid(null));

        numberSchema.required();

        Assertions.assertFalse(numberSchema.isValid(null));
        Assertions.assertFalse(numberSchema.isValid("5"));
        Assertions.assertTrue(numberSchema.isValid(10));

        // Потому что ранее мы вызвали метод positive()
        Assertions.assertFalse(numberSchema.isValid(-10));
        //  Ноль — не положительное число
        Assertions.assertFalse(numberSchema.isValid(0));

        numberSchema.range(5, 10);

        Assertions.assertTrue(numberSchema.isValid(5));
        Assertions.assertTrue(numberSchema.isValid(10));

        Assertions.assertFalse(numberSchema.isValid(4));
        Assertions.assertFalse(numberSchema.isValid(11));
    }

    @Test
    public void mapSchemaTest() {
        Assertions.assertTrue(mapSchema.isValid(null));

        mapSchema.required();

        Assertions.assertFalse(mapSchema.isValid(null));
        Assertions.assertTrue(mapSchema.isValid(new HashMap()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Assertions.assertTrue(mapSchema.isValid(data));

        mapSchema.sizeof(2);

        Assertions.assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        Assertions.assertTrue(mapSchema.isValid(data));
    }
}
