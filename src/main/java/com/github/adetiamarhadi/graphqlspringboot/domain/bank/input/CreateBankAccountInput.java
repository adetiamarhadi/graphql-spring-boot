package com.github.adetiamarhadi.graphqlspringboot.domain.bank.input;

import lombok.Data;

@Data
public class CreateBankAccountInput {
    private String firstName;
    private int age;
}
