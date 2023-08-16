package gw.ast;

import gw.visitors.ExpressionVisitor;

public class BinaryExpression extends AbstractExpression {
    public BinaryExpression(Expression leftOperand, Operator operator, Expression rightOperand) {
        super(leftOperand,operator,rightOperand);
    }
    public BinaryExpression() {
        super();
    }
    @Override
    public <T> T accept(ExpressionVisitor visitor, T data) {
        return (T)visitor.visitBinaryExpression(this, data);
    }
}
