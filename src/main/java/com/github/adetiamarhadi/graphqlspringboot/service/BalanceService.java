package com.github.adetiamarhadi.graphqlspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class BalanceService {

    public Map<UUID, BigDecimal> getBalanceFor(Set<UUID> bankAccountIds, String userId) {

        log.info("Requesting bank account ids: {} for user id: {}", bankAccountIds, userId);
        return Map.of();
    }
}
