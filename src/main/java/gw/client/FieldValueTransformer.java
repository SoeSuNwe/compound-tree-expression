package gw.client;

public interface FieldValueTransformer {
    public String transformField(String fieldName);
    public FieldValuePair<? extends Object> transformValue(String fieldName, Object value);
}
