package gw.common;

import graphql.schema.DataFetchingEnvironment;

import java.util.HashMap;
import java.util.Map;

import gw.client.ExpressionFormat;
import gw.client.FilterExpression;
import org.springframework.data.jpa.domain.Specification;
public class SampleDataFetcher {
    private String expression;
    private String sqlExpression;
    private Specification<Object> specification;

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

    public graphql.schema.DataFetcher searchEmployeesSQL() {

        return new graphql.schema.DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
                FilterExpression.FilterExpressionBuilder builder = FilterExpression.newFilterExpressionBuilder();
                Map<String, String> fieldMap = new HashMap<>();
                fieldMap.put("firstName","empFirstName");
                FilterExpression filterExpression = builder.field(dataFetchingEnvironment.getField())
                        .map(fieldMap)
                        .args(dataFetchingEnvironment.getArguments())
                        .build();
                sqlExpression = filterExpression.getExpression(ExpressionFormat.SQL);
                return null;
            }
        };
    }

    public graphql.schema.DataFetcher searchEmployeesJPA() {

        return new graphql.schema.DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
                FilterExpression.FilterExpressionBuilder builder = FilterExpression.newFilterExpressionBuilder();
                FilterExpression filterExpression = builder.field(dataFetchingEnvironment.getField())
                        .args(dataFetchingEnvironment.getArguments())
                        .build();
                specification = filterExpression.getExpression(ExpressionFormat.JPA);
                return null;
            }
        };
    }

    public String getExpression() {
        return expression;
    }

    public String getSqlExpression() {
        return sqlExpression;
    }

    public Specification<Object> getSpecification() {
        return specification;
    }

}
