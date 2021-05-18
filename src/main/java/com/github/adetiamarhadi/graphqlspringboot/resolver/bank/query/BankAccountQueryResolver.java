package com.github.adetiamarhadi.graphqlspringboot.resolver.bank.query;

import com.github.adetiamarhadi.graphqlspringboot.BankAccountRepository;
import com.github.adetiamarhadi.graphqlspringboot.connection.CursorUtil;
import com.github.adetiamarhadi.graphqlspringboot.context.CustomGraphQLContext;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.BankAccount;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountQueryResolver implements GraphQLQueryResolver {

    private final BankAccountRepository bankAccountRepository;
    private final CursorUtil cursorUtil;

    public BankAccount bankAccount(UUID id, DataFetchingEnvironment environment) {
        log.info("Retrieving bank account id: {}", id);

        CustomGraphQLContext context = environment.getContext();

        log.info("user id : {}", context.getUserId());

        var requestedFields = environment.getSelectionSet().getFields().stream().map(SelectedField::getName)
                .collect(Collectors.toUnmodifiableSet());

        if (environment.getSelectionSet().contains("specialField")) {
            // do special stuff
        }

        log.info("Requested field : {}", requestedFields);

        return BankAccount.builder()
                .id(id)
                .currency(Currency.USD)
                .build();
    }

    public Connection<BankAccount> bankAccounts(int first, @Nullable String cursor) {

        List<Edge<BankAccount>> edges = getBankAccounts(cursor)
                .stream()
                .map(bankAccount -> new DefaultEdge<>(bankAccount, this.cursorUtil.createCursorWith(bankAccount.getId())))
                .limit(first)
                .collect(Collectors.toUnmodifiableList());

        var firstCursor = this.cursorUtil.getFirstCursorFrom(edges);
        var lastCursor = this.cursorUtil.getLastCursorFrom(edges);

        var pageInfo = new DefaultPageInfo(firstCursor, lastCursor, null != cursor, edges.size() >= first);

        return new DefaultConnection<>(edges, pageInfo);
    }

    public List<BankAccount> getBankAccounts(String cursor) {

        if (null == cursor) {
            return this.bankAccountRepository.getBankAccounts();
        }
        return this.bankAccountRepository.getBankAccountsAfter(this.cursorUtil.decode(cursor));
    }
}
