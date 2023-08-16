package gw.ast;

import gw.visitors.ExpressionVisitor;

public interface Expression {
    public String infix();

    <T> T accept(ExpressionVisitor visitor, T data);
}
