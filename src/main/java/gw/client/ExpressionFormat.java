package gw.client;

public enum ExpressionFormat {
    SQL("SQL"),
    INFIX("INFIX"),
    JPA("JPA"),
    MONGO("MONGO");

    private String type;
    ExpressionFormat(String type) {
        this.type = type;
    }

    public static ExpressionFormat getFormat(String type) {
        for (ExpressionFormat format : ExpressionFormat.values()) {
            if (format.type.equals(type)) {
                return format;
            }
        }
        return SQL;
    }
}
