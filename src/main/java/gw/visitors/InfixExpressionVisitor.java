package gw.visitors;

import gw.ast.*;
import gw.client.FieldValuePair;
import gw.client.FieldValueTransformer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class InfixExpressionVisitor implements ExpressionVisitor<String>{
    private Map<String, String> fieldMap;
    private Deque<ExpressionField> fieldStack;
    private FieldValueTransformer fieldValueTransformer;

    public InfixExpressionVisitor(Map<String,String> fieldMap, FieldValueTransformer fieldValueTransformer) {
        this.fieldMap = fieldMap;
        this.fieldStack = new ArrayDeque<>();
        this.fieldValueTransformer = fieldValueTransformer;
    }
    @Override
    public String expression(Expression expression) {
        String expressionString = "";
        if (expression != null) {
            expressionString = expression.accept(this, expressionString);
        }
        return expressionString;
    }

    @Override
    public String visitCompoundExpression(CompoundExpression compoundExpression, String data) {
        StringBuilder expressionBuilder = new StringBuilder(data);
        expressionBuilder.append("(")
                .append(compoundExpression.getLeftOperand().accept(this, ""))
                .append(" ").append(compoundExpression.getOperator().getName()).append(" ")
                .append(compoundExpression.getRightOperand().accept(this, ""))
                .append(")");
        return expressionBuilder.toString();
    }

    @Override
    public String visitBinaryExpression(BinaryExpression binaryExpression, String data) {
        StringBuilder expressionBuilder = new StringBuilder(data);
        expressionBuilder.append("(")
                .append(binaryExpression.getLeftOperand().accept(this, ""))
                .append(" ").append(binaryExpression.getOperator().getName()).append(" ")
                .append(binaryExpression.getRightOperand().accept(this, ""))
                .append(")");
        return expressionBuilder.toString();
    }

    @Override
    public String visitUnaryExpression(UnaryExpression unaryExpression, String data) {
        StringBuilder expressionBuilder = new StringBuilder(data);
        expressionBuilder.append("(")
                .append(" ").append(unaryExpression.getOperator()).append(" ")
                .append(unaryExpression.getLeftOperand().accept(this, ""))
                .append(")");
        return expressionBuilder.toString();
    }

    @Override
    public String visitExpressionField(ExpressionField field, String data) {
        StringBuilder expressionBuilder = new StringBuilder(data);
        if (fieldMap != null && fieldMap.get(field.infix()) != null) {
            expressionBuilder.append(fieldMap.get(field.infix()));
        } else if (fieldValueTransformer != null && fieldValueTransformer.transformField(field.infix()) != null) {
            expressionBuilder.append(fieldValueTransformer.transformField(field.infix()));
            fieldStack.push(field); //pushing the field for lookup while visiting value.
        } else {
            expressionBuilder.append(field.infix());
        }
        return expressionBuilder.toString();
    }

    @Override
    public String visitExpressionValue(ExpressionValue<? extends Comparable> value, String data) {
        if (!fieldStack.isEmpty() && fieldValueTransformer != null) {
            ExpressionField field  = fieldStack.pop(); // pop the field associated with this value.
            FieldValuePair fieldValuePair = fieldValueTransformer.transformValue(field.infix(),value.value());
            if (fieldValuePair != null && fieldValuePair.getValue() != null) {
                value = new ExpressionValue(fieldValuePair.getValue());
            }
        }

        StringBuilder expressionBuilder = new StringBuilder(data);
        expressionBuilder.append(value.value());
        return expressionBuilder.toString();
    }
}
