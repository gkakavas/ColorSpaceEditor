package duth.dip.cse;

import duth.dip.cse.util.property.Property;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

public class TestCLass123 {
    @Property(name = "test.property.value.string")
    String stringValue;

    @Property(name = "test.property.value.int")
    int intValue;

    @Property(name = "test.property.value.float")
    float longValue;

    @Property(name = "test.property.value.boolean")
    boolean booleanValue;


    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public float getLongValue() {
        return longValue;
    }

    public void setLongValue(float longValue) {
        this.longValue = longValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    @Override
    public String toString() {
        return "TestCLass123{" +
                "stringValue='" + stringValue + '\'' +
                ", intValue='Long" + intValue + '\'' +
                ", longValue='" + longValue + '\'' +
                ", booleanValue='" + booleanValue + '\'' +
                '}';
    }


}
