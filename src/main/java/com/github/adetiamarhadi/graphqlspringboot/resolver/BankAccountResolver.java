package com.github.adetiamarhadi.graphqlspringboot.resolver;

import com.github.adetiamarhadi.graphqlspringboot.domain.bank.BankAccount;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.Client;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id) {
        log.info("Retrieving bank account id: {}", id);

        var clientA = Client.builder()
                .id(UUID.randomUUID())
                .firstName("Adetia")
                .lastName("Marhadi")
                .build();

        var clientB = Client.builder()
                .id(UUID.randomUUID())
                .firstName("Adetia")
                .lastName("Marhadi II").build();

        clientA.setClient(clientB);
        clientB.setClient(clientA);

        return BankAccount.builder()
                .id(id)
                .currency(Currency.USD)
                .client(clientA)
                .build();
    }
}
