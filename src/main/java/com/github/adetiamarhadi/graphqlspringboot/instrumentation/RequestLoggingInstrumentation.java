package com.github.adetiamarhadi.graphqlspringboot.instrumentation;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLoggingInstrumentation extends SimpleInstrumentation {

    public static String CORRELATION_ID = "correlation_id";

    private final Clock clock;

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {

        var start = Instant.now(this.clock);

        MDC.put(CORRELATION_ID, parameters.getExecutionInput().getExecutionId().toString());

        log.info("Query: {} with variables: {}", parameters.getQuery(), parameters.getVariables());

        return SimpleInstrumentationContext.whenCompleted((executionResult, throwable) -> {
            var duration = Duration.between(start, Instant.now(this.clock));
            if (null == throwable) {
                log.info("Completed successfully in: {}", duration);
            } else {
                log.warn("Failed in: {}", duration, throwable);
            }
            MDC.clear();
        });
    }
}
