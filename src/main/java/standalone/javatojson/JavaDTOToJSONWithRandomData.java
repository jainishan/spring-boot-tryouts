package standalone.javatojson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class JavaDTOToJSONWithRandomData {
    private static final Random random = new Random();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    static {
        // Set JVM option to allow reflection access to private fields of java.lang.String
        System.setProperty("illegal-access", "permit");
    }

    public static <T> T initialize(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        if (clazz.isEnum()) {
            T[] enumConstants = clazz.getEnumConstants();
            return enumConstants[new Random().nextInt(enumConstants.length)];
        }

        if (clazz == String.class) {
            return (T) generateRandomString();
        }

        T instance = clazz.newInstance();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();

                if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
                    field.set(instance, random.nextInt(100));
                } else if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
                    field.set(instance, random.nextLong(100));
                } else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
                    field.set(instance, random.nextDouble(100));
                } else if (fieldType.equals(float.class) || fieldType.equals(Float.class)) {
                    field.set(instance, random.nextFloat(100));
                } else if (fieldType.equals(BigDecimal.class) || fieldType.equals(BigDecimal.class)) {
                    field.set(instance, BigDecimal.valueOf(random.nextLong(10000)));
                } else if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
                    field.set(instance, random.nextBoolean());
                } else if (fieldType.equals(String.class)) {
                    field.set(instance, generateRandomString());
                } else if (fieldType.equals(Character.class) || fieldType.equals(char.class)) {
                    field.set(instance, generateRandomCharacter());
                } else if (fieldType.isEnum()) {
                    Object[] enumConstants = fieldType.getEnumConstants();
                    if (enumConstants.length > 0) {
                        field.set(instance, enumConstants[new Random().nextInt(enumConstants.length)]);
                    }
                } else if (fieldType == LocalDate.class) {
                    field.set(instance, LocalDate.now());
                } else if (fieldType == List.class) {
                    Type genericType = field.getGenericType();
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) genericType;
                        Type[] typeArguments = parameterizedType.getActualTypeArguments();
                        if (typeArguments.length == 1 && typeArguments[0] instanceof Class) {
                            Class<?> listElementType = (Class<?>) typeArguments[0];
                            List<Object> list = new ArrayList<>();
                            for (int i = 0; i < 3; i++) {
                                list.add(initialize(listElementType));
                            }
                            field.set(instance, list);
                        }
                    }
                } else if (fieldType == Map.class) {
                    Type genericType = field.getGenericType();
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) genericType;
                        Type[] typeArguments = parameterizedType.getActualTypeArguments();
                        Object key = initialize((Class<?>) typeArguments[0]);
                        Object value = initialize((Class<?>) typeArguments[1]);
                        Map<Object, Object> map = new HashMap<>();
                        map.put(key, value);
                        field.set(instance, map);
                    }
                } else {
                    Object nestedInstance = initialize(fieldType);
                    field.set(instance, nestedInstance);
                }
            }
        }

        return instance;
    }

    private static String generateRandomString() {
        StringBuilder sb = new StringBuilder();

        int length = random.nextInt(10) + 1;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    private static char generateRandomCharacter() {
        char randomChar = (char) (random.nextInt(26) + 'a'); // Generate a random lowercase letter
        return randomChar;
    }

    // Usage example
    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, JsonProcessingException {
        // Initialize an instance of the MyClass class with random data
        MyClass myObject = JavaDTOToJSONWithRandomData.initialize(MyClass.class);
        System.out.println(myObject);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(myObject));
    }
}

