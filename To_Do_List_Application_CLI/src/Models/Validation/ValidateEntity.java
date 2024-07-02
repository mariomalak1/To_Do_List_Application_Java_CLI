package Models.Validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ValidateEntity {

    public static List<String> validate(Object obj) {
        List<String> fieldsNotFilled = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(RequiredField.class)) {
                field.setAccessible(true); // Ensure we can access private fields
                Object value = null;
                try {
                    value = field.get(obj);
                } catch (IllegalAccessException e) {
                    System.out.println("Can't access this field");
                }
                if (value == null) {
                    fieldsNotFilled.add(field.getName());
                }
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
            requiredFieldsStr += field + " this field can't be empty.";
        }
        return requiredFieldsStr;
    }
}
