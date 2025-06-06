package com.trading.aggregator.advice;

import com.trading.aggregator.exceptions.CustomerNotFoundException;
import com.trading.aggregator.exceptions.InvalidTradeRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.function.Consumer;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleException(CustomerNotFoundException ex) {

        return build(HttpStatus.NOT_FOUND, ex, problemDetail -> {
            problemDetail.setTitle("Customer Not Found");
            problemDetail.setDetail(ex.getMessage());
        });
    }

    @ExceptionHandler(InvalidTradeRequestException.class)
    public ProblemDetail handleException(InvalidTradeRequestException ex) {

        return build(HttpStatus.BAD_REQUEST, ex, problemDetail -> {
            problemDetail.setTitle("Invalid trade request");
            problemDetail.setDetail(ex.getMessage());
        });
    }

    private ProblemDetail build(HttpStatus status, Exception ex, Consumer<ProblemDetail> consumer) {
        var problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        consumer.accept(problem);
        return problem;
    }
}
