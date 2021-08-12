package com.github.adetiamarhadi.graphqlspringboot.service;

import com.github.adetiamarhadi.graphqlspringboot.domain.bank.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class BalanceService {

    public Map<UUID, BigDecimal> getBalanceFor(Map<UUID, BankAccount> bankAccountIds, String userId) {

        log.info("Requesting bank account ids: {} for user id: {}", bankAccountIds, userId);

        var ids = bankAccountIds.keySet();

        return Map.of(
                UUID.fromString("e5f69958-3046-422b-9661-3e36e16f5851"), BigDecimal.ONE,
                UUID.fromString("0827ddb1-2e30-4092-b2c6-8f504165fc31"), new BigDecimal("23431.22")
        );
    }
}
