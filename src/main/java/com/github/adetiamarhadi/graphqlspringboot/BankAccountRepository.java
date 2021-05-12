package com.github.adetiamarhadi.graphqlspringboot;

import com.github.adetiamarhadi.graphqlspringboot.domain.bank.BankAccount;
import com.github.adetiamarhadi.graphqlspringboot.domain.bank.Currency;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BankAccountRepository {

    private final List<BankAccount> bankAccounts = List.of(
            BankAccount.builder()
                    .id(UUID.fromString("b0ca90d2-b3b7-4a0d-93d9-fb40acf9da7b"))
                    .currency(Currency.USD)
                    .createdAt(ZonedDateTime.parse("2021-05-01T12:00:00+07:00"))
                    .build(),
            BankAccount.builder()
                    .id(UUID.fromString("4fafece9-3862-476e-84b8-e7de0822f868"))
                    .currency(Currency.CHF)
                    .createdAt(ZonedDateTime.parse("2021-05-01T20:15:00+07:00"))
                    .build(),
            BankAccount.builder()
                    .id(UUID.fromString("45b4eb94-57d3-44e9-8b31-174bb0da36f4"))
                    .currency(Currency.CHF)
                    .createdAt(ZonedDateTime.parse("2021-05-03T09:08:10+07:00"))
                    .build(),
            BankAccount.builder()
                    .id(UUID.fromString("c6b4c38c-aa34-4c9b-bc52-ccdc338340d3"))
                    .currency(Currency.USD)
                    .createdAt(ZonedDateTime.parse("2021-05-06T11:45:00+07:00"))
                    .build()
    ).stream()
            .sorted(Comparator.comparing(BankAccount::getId))
            .collect(Collectors.toList());

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public List<BankAccount> getBankAccountsAfter(UUID id) {
        return bankAccounts.stream().dropWhile(bankAccount -> bankAccount.getId().compareTo(id) != 1)
                .collect(Collectors.toUnmodifiableList());
    }
}
