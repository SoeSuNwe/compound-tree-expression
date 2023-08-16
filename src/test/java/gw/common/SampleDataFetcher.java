package gw.common;

import graphql.schema.DataFetchingEnvironment;


import gw.client.ExpressionFormat;
import gw.client.FilterExpression;
import org.springframework.data.jpa.domain.Specification;
public class SampleDataFetcher {
    private String expression;

    public SampleDataFetcher() {

    }

    public graphql.schema.DataFetcher searchEmployees() {

        return new graphql.schema.DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
                FilterExpression.FilterExpressionBuilder builder = FilterExpression.newFilterExpressionBuilder();
                FilterExpression filterExpression = builder.field(dataFetchingEnvironment.getField())
                        .args(dataFetchingEnvironment.getArguments())
                        .build();
                expression = filterExpression.getExpression(ExpressionFormat.INFIX);
                return null;
            }
        };
    }

    public String getExpression() {
        return expression;
    }

}
