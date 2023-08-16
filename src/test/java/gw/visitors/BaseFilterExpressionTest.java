package gw.visitors;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import gw.common.SampleDataFetcher;
import org.junit.Before;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public abstract class BaseFilterExpressionTest {

    private GraphQL graphQL;
    private SampleDataFetcher employeeDataFetcher;

    @Before
    public void init() throws IOException {

        employeeDataFetcher = new SampleDataFetcher();
        String schemaFile=  "D:\\JavaLeaning\\compound-tree\\target\\test-classes\\schema.graphql";
        Path filePath = Paths.get(Paths.get(schemaFile).toString());
        String sdl = Files.lines(Paths.get(filePath.toUri()), StandardCharsets.UTF_8).
                collect(Collectors.joining(System.lineSeparator()));

        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl){
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    protected abstract RuntimeWiring buildWiring();

    public GraphQL getGraphQL() {
        return graphQL;
    }

    public SampleDataFetcher getEmployeeDataFetcher() {
        return employeeDataFetcher;
    }
}
