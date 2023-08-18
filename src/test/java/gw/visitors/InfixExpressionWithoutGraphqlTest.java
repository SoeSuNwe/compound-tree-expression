package gw.visitors;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class InfixExpressionWithoutGraphqlTest extends BaseFilterExpressionWithoutGraphqlTest{
    @Test
    public void filterExpressionSimple() throws IOException {
        String expectedExpression = "(firstName contains Saurabh)";
        getDataFetcher().searchEmployeesWithoutGraphql();
        Assert.assertEquals(expectedExpression, getDataFetcher().getExpression());
        System.out.println("--------------------------------------------"+getDataFetcher().getExpression()+"----------------------------------------------\n");
    }
}
