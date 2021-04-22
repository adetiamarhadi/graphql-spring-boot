package com.github.adetiamarhadi.graphqlspringboot.resolver.bank;

import com.github.adetiamarhadi.graphqlspringboot.domain.bank.BankAccount;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.Client;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

    public Client client(BankAccount bankAccount) {
        log.info("Requesting client data for bank account id {}", bankAccount.getId());

        throw new RuntimeException("Spring exception can't connect to database: (sql select *)");

//        return Client.builder().id(UUID.randomUUID()).firstName("Adetia").lastName("Marhadi").build();
    }
}
