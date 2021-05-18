package com.github.adetiamarhadi.graphqlspringboot.resolver.bank.query;

import com.github.adetiamarhadi.graphqlspringboot.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLResolver<BankAccount> {

    public BigDecimal balance(BankAccount bankAccount) {
        log.info("Getting balance for {}", bankAccount.getId());
        return BigDecimal.ONE;
    }
}
