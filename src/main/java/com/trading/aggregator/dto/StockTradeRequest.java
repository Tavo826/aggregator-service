package com.trading.aggregator.dto;

import com.trading.aggregator.domain.Ticker;
import com.trading.aggregator.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                Integer price,
                                Integer quantity,
                                TradeAction action) {

    public Integer totalPrice() {
        return price * quantity;
    }
}
