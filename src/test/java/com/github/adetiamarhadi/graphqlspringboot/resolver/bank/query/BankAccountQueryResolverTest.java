package com.github.adetiamarhadi.graphqlspringboot.resolver.bank.query;

import com.github.adetiamarhadi.graphqlspringboot.GraphqlSpringBootApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestGraphqlSpringBootApplication.class)
public class BankAccountQueryResolverTest {

    private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/request/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/resolver/query/response/%s.json";

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void bank_accounts_are_returned() throws IOException, JSONException {
        var testName = "bank_account";
        var response = this.graphQLTestTemplate
                .postForResource(String.format(GRAPHQL_QUERY_REQUEST_PATH, testName));

        var expectedResponseBody = read(String.format(GRAPHQL_QUERY_RESPONSE_PATH, testName));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertEquals(expectedResponseBody, response.getRawResponse().getBody(), true);
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
