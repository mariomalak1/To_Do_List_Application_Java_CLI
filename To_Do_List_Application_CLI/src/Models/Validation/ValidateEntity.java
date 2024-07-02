package Models.Validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateEntity {

    public static List<String> validate(Object obj, String ...requiredFields) {
        List<String> fieldsNotFilled = new ArrayList<>();

        List<Field> fields = Arrays.asList(obj.getClass().getDeclaredFields());

        for (String fieldName :requiredFields){
            Field field = fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
            if (field == null) {
                continue;
            }

            field.setAccessible(true); // Ensure we can access private fields
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                System.out.println("Can't access this field");
            }
            if (value == null) {
                fieldsNotFilled.add(field.getName());
                continue;
            }
            if (value.toString().equals("")){
                fieldsNotFilled.add(field.getName());
            }
        }
        return fieldsNotFilled;
    }


    public static String RequiredFieldsInString(List<String> requiredFields){
        String requiredFieldsStr = "";
        if (requiredFields == null){
            return requiredFieldsStr;
        }
        for(String field: requiredFields){
            requiredFieldsStr += field + " this field can't be empty.\n";
        }
        return requiredFieldsStr;
    }
}
