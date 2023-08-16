package gw.client;

import gw.visitors.ExpressionVisitor;
import gw.visitors.InfixExpressionVisitor;

import java.util.Map;

public class ExpressionVisitorFactory {
    static ExpressionVisitor getExpressionVisitor(ExpressionFormat format,
                                                  Map<String, String> fieldMap,
                                                  FieldValueTransformer fieldValueTransformer) {
        ExpressionVisitor expressionVisitor = new InfixExpressionVisitor(fieldMap, fieldValueTransformer);
        if (format != null) {
            switch (format) {
                case INFIX:
                    expressionVisitor = new InfixExpressionVisitor(fieldMap, fieldValueTransformer);
                    break;
            }
        }
        return expressionVisitor;
    }
}
