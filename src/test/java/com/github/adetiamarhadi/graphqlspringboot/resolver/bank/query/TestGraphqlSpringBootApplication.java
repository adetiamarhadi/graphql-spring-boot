package com.github.adetiamarhadi.graphqlspringboot.resolver.bank.query;

import com.github.adetiamarhadi.graphqlspringboot.GraphqlSpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Configuration
@Import(GraphqlSpringBootApplication.class)
public class TestGraphqlSpringBootApplication {

    @Bean
    @Primary
    public Clock testClock() {
        return Clock.fixed(LocalDate.of(2021, 8, 6).atStartOfDay(UTC).toInstant(), UTC);
    }
}
