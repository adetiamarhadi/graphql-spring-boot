package com.github.adetiamarhadi.graphqlspringboot.resolver.bank.mutation;

import com.github.adetiamarhadi.graphqlspringboot.domain.bank.BankAccount;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.Currency;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.input.CreateBankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountMutation implements GraphQLMutationResolver {

    public BankAccount createBankAccount(CreateBankAccountInput input) {
        log.info("Creating bank account for {}", input);
        return BankAccount.builder().id(UUID.randomUUID()).currency(Currency.USD).build();
    }
}
