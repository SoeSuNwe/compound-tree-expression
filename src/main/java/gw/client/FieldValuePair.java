package gw.client;

public class FieldValuePair<V> {
    private String fieldName;
    private V value;

    public FieldValuePair(String fieldName, V value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Returns the field name.
     *
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Returns the field value.
     * @return
     */
    public V getValue() {
        return value;
    }
}
