package com.trading.aggregator.dto;

import com.trading.aggregator.domain.Ticker;

public record Holding(Ticker ticker,
                      Integer quantity) {
}
