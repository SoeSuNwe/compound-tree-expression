package gw.ast;

import gw.visitors.ExpressionVisitor;

public class ExpressionField implements Expression{
    private String fieldName;

    public ExpressionField(String fieldName) {
        this.fieldName = fieldName;
    }
    @Override
    public String infix() {
        return fieldName;
    }
    @Override
    public <T> T accept(ExpressionVisitor visitor, T data) {
        return (T)visitor.visitExpressionField(this, data);
    }
}
