package com.trading.aggregator.client;

import com.trading.aggregator.dto.CustomerInformation;
import com.trading.aggregator.dto.StockTradeRequest;
import com.trading.aggregator.dto.StockTradeResponse;
import com.trading.aggregator.exceptions.ApplicationExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class CustomerServiceClient {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceClient.class);

    private final WebClient client;

    public CustomerServiceClient(WebClient client) {
        this.client = client;
    }

    public Mono<CustomerInformation> getCustomerInformation(Integer customerId) {

        return this.client.get()
                .uri("/customers/{customerId}", customerId)
                .retrieve()
                .bodyToMono(CustomerInformation.class)
                .onErrorResume(WebClientResponseException.NotFound.class, ex -> ApplicationExceptions.customerNotFound(customerId));
    }

    public Mono<StockTradeResponse> trade(Integer customerId, StockTradeRequest request) {

        return this.client.post()
                .uri("/customers/{customerId}/trade", customerId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(StockTradeResponse.class)
                .onErrorResume(WebClientResponseException.NotFound.class, ex -> ApplicationExceptions.customerNotFound(customerId))
                .onErrorResume(WebClientResponseException.BadRequest.class, this::handleException);
    }

    private <T> Mono<T> handleException(WebClientResponseException.BadRequest exception) {

        var problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
        var message = Objects.nonNull(problemDetail) ? problemDetail.getDetail() : exception.getMessage();
        log.error("customer service problem detail: {}", message);

        return ApplicationExceptions.invalidTradeRequest(message);
    }
}
