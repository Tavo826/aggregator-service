package com.trading.aggregator.exceptions;

public class InsufficientSharesException extends RuntimeException {

    private static final String MESSAGE = "Customer [id=%d] does not have enough shares";

    public InsufficientSharesException(Integer id) {
        super(String.format(MESSAGE, id));
    }
}
