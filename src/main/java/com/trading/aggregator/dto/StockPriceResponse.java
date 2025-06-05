package com.trading.aggregator.dto;

import com.trading.aggregator.domain.Ticker;

import java.time.LocalDateTime;

public record StockPriceResponse(Ticker ticker,
                                 Integer price) {
}
