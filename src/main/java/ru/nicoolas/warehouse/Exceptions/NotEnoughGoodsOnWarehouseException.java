package ru.nicoolas.warehouse.Exceptions;

public class NotEnoughGoodsOnWarehouseException extends RuntimeException {

    public NotEnoughGoodsOnWarehouseException(String message) {
        super(message);
    }
}
