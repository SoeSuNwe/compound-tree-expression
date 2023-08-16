package gw.visitors;

import graphql.ExecutionResult;
import graphql.schema.idl.RuntimeWiring;
import gw.common.TestConstants;
import org.junit.Assert;
import org.junit.Test;
import graphql.scalars.ExtendedScalars;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class InfixExpressionTest extends BaseFilterExpressionTest {

    @Override
    public RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .scalar(ExtendedScalars.DateTime)
                .type(newTypeWiring("Query")
                        .dataFetcher("searchEmployees", getEmployeeDataFetcher().searchEmployees()))
                .build();
    }

    @Test
    public void filterExpressionSimple() {
        ExecutionResult result = getGraphQL().execute(TestConstants.BINARY_FILER);
        System.out.println(TestConstants.BINARY_FILER);

        String expectedExpression = "(firstName contains Saurabh)";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionSimpleInt() {
        ExecutionResult result = getGraphQL().execute(TestConstants.BINARY_FILER_INT);
        System.out.println(TestConstants.BINARY_FILER_INT);

        String expectedExpression = "(age gte 25)";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionWithOR() {
        ExecutionResult result = getGraphQL().execute(TestConstants.COMPOUND_FILER_WITH_OR);
        System.out.println(TestConstants.COMPOUND_FILER_WITH_OR);

        String expectedExpression = "((firstName contains Saurabh) or (lastName equals Jaiswal))";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionWithAND() {
        ExecutionResult result = getGraphQL().execute(TestConstants.COMPOUND_FILER_WITH_AND);
        System.out.println(TestConstants.COMPOUND_FILER_WITH_AND);

        String expectedExpression = "((firstName contains Saurabh) and (lastName equals Jaiswal))";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionORWithAND() {
        ExecutionResult result = getGraphQL().execute(TestConstants.COMPOUND_FILER_WITH_OR_AND);
        System.out.println(TestConstants.COMPOUND_FILER_WITH_OR_AND);

        String expectedExpression = "((firstName contains Saurabh) or ((lastName equals Jaiswal) and (age gte 25)))";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionANDWithOR() {
        ExecutionResult result = getGraphQL().execute(TestConstants.COMPOUND_FILER_WITH_AND_OR);
        System.out.println(TestConstants.COMPOUND_FILER_WITH_AND_OR);
        String expectedExpression = "((firstName contains Saurabh) and ((lastName equals Jaiswal) or (age gte 25)))";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionANDWithMultipleOR() {
        ExecutionResult result = getGraphQL().execute(TestConstants.COMPOUND_FILER_WITH_OR_OR_AND);
        System.out.println(TestConstants.COMPOUND_FILER_WITH_OR_OR_AND);
        String expectedExpression = "(((firstName contains Saurabh) or (lastName equals Jaiswal)) or ((firstName equals Vinod) and (age gte 30)))";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionORWithMultipleAND() {
        ExecutionResult result = getGraphQL().execute(TestConstants.COMPOUND_FILER_WITH_AND_AND_OR);
        System.out.println(TestConstants.COMPOUND_FILER_WITH_AND_AND_OR);
        String expectedExpression = "(((firstName contains Saurabh) and (lastName equals Jaiswal)) and ((firstName equals Vinod) or (age gte 30)))";

        Assert.assertEquals(expectedExpression, getEmployeeDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }

    @Test
    public void filterExpressionWithVariables () {
        ExecutionResult result = getGraphQL().execute(TestConstants.FILTER_WITH_VARIABLE);
        System.out.println(TestConstants.FILTER_WITH_VARIABLE);
        String parent = getEmployeeDataFetcher().getExpression();

        String expectedExpression = "((firstName contains Saurabh) and (lastName equals Jaiswal))";

        Assert.assertEquals(expectedExpression,parent);
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }
    @Test
    public void filterExpressionWithNotCompound () {
        ExecutionResult result = getGraphQL().execute(TestConstants.NOT_COMPOUND_FILTER);
        System.out.println(TestConstants.NOT_COMPOUND_FILTER);
        String parent = getEmployeeDataFetcher().getExpression();

        String expectedExpression = "( NOT ((firstName equals Saurabh) and (lastName contains Jaiswal)))";

        Assert.assertEquals(expectedExpression,parent);
        System.out.println("--------------------------------------------"+expectedExpression+"----------------------------------------------\n");
    }
}
