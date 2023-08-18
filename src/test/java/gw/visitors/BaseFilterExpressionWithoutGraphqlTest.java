package gw.visitors;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import gw.common.SampleDataFetcher;
import gw.common.SampleDataWithoutGraphql;
import org.junit.Before;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public abstract class BaseFilterExpressionWithoutGraphqlTest {

    private SampleDataWithoutGraphql sampleDataWithoutGraphql;
    public SampleDataWithoutGraphql getDataFetcher() {
        return sampleDataWithoutGraphql;
    }
    @Before
    public void init()  {
        sampleDataWithoutGraphql = new SampleDataWithoutGraphql();
    }
}
