package gw.visitors;

import gw.ast.*;

public interface ExpressionVisitor<T> {

    public T expression(Expression expression);
    public T visitCompoundExpression(CompoundExpression compoundExpression, T data);
    public T visitBinaryExpression(BinaryExpression binaryExpression, T data);
    public T visitUnaryExpression(UnaryExpression unaryExpression, T data);
    public T visitExpressionField(ExpressionField field, T data);
    public T visitExpressionValue(ExpressionValue<? extends Comparable>  value, T data);
}
