package standalone.javatojson;

import lombok.Data;

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
    private YourClass yourClass;
    private OurClass ourClass;
}
