package gw.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetchingEnvironment;
import gw.client.ExpressionFormat;
import gw.client.FilterExpression;

import java.io.IOException;
import java.util.Map;

public class SampleDataWithoutGraphql {
    private String expression;
    public SampleDataWithoutGraphql() { }

    public String searchEmployeesWithoutGraphql() throws IOException {
        String jsonString = "{\"filter\":{\"firstName\":{\"contains\":\"Saurabh\"}}}";
        Map mapper = new ObjectMapper().readValue(jsonString, Map.class);
        System.out.println(" mapper " + mapper);
        FilterExpression.FilterExpressionBuilder builder = FilterExpression.newFilterExpressionBuilder();
        FilterExpression filterExpression = builder.args(mapper)
                .build();
        expression = filterExpression.getExpression(ExpressionFormat.INFIX);
        System.out.println(expression + "expression");
        return expression;
    }
    public String getExpression() {
        return expression;
    }
}
