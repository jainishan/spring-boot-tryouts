package standalone.javatojson;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
class MyClass {
    private int intValue;
    private Integer integerValue;
    private long longValue;
    private Long longWrapperValue;
    private double doubleValue;
    private Double doubleWrapperValue;
    private float floatValue;
    private Float floatWrapperValue;
    private boolean booleanValue;
    private Boolean booleanWrapperValue;
    private String stringValue;
    private char charValue;
    private Character characterValue;
    private List<YourClass> yourClass;
    private List<SampleEnum> alphabets;
    private Map<String, String> someMap;
    private OurClass ourClass;
}
