package com.trading.aggregator.exceptions;

public class InsufficientBalanceException extends RuntimeException {

    private static final String MESSAGE = "Customer [id=%d] does not have enough balance";

    public InsufficientBalanceException(Integer id) {
        super(String.format(MESSAGE, id));
    }
}
