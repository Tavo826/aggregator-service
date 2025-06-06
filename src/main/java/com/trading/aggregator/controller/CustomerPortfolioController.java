package com.trading.aggregator.controller;

import com.trading.aggregator.dto.CustomerInformation;
import com.trading.aggregator.dto.StockTradeResponse;
import com.trading.aggregator.dto.TradeRequest;
import com.trading.aggregator.service.CustomerPortfolioService;
import com.trading.aggregator.validator.RequestValidator;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CustomerPortfolioController {

    private final CustomerPortfolioService customerPortfolioService;

    public CustomerPortfolioController(CustomerPortfolioService customerPortfolioService) {
        this.customerPortfolioService = customerPortfolioService;
    }

    @GetMapping("/{customerId}")
    public Mono<CustomerInformation> getCustomerInformation(@PathVariable Integer customerId) {

        return this.customerPortfolioService.getCustomerInformation(customerId);
    }

    @PostMapping("/{customerId}/trade")
    public Mono<StockTradeResponse> trade(@PathVariable Integer customerId, @RequestBody Mono<TradeRequest> request) {

        return request.transform(RequestValidator.validate())
                .flatMap(tradeRequest -> this.customerPortfolioService.trade(customerId, tradeRequest));
    }
}
