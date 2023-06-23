package ru.nicoolas.warehouse.Exceptions;

public class NotEnoughtGoodsOnWarehouseException extends RuntimeException {
    public NotEnoughtGoodsOnWarehouseException() {
    }

    public NotEnoughtGoodsOnWarehouseException(String message) {
        super(message);
    }

    public NotEnoughtGoodsOnWarehouseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughtGoodsOnWarehouseException(Throwable cause) {
        super(cause);
    }

    public NotEnoughtGoodsOnWarehouseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
