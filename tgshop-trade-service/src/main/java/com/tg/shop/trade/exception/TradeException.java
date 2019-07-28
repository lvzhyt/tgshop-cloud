package com.tg.shop.trade.exception;

public class TradeException extends Exception {

    public TradeException() {
    }

    public TradeException(String message) {
        super(message);
    }

    public TradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeException(Throwable cause) {
        super(cause);
    }

    public TradeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
