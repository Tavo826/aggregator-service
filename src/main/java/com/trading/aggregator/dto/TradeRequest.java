package com.trading.aggregator.dto;

import com.trading.aggregator.domain.Ticker;
import com.trading.aggregator.domain.TradeAction;

public record TradeRequest (Ticker ticker,
                            TradeAction action,
                            Integer quantity){
}
