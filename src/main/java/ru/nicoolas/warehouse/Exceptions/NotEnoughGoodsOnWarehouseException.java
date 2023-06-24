package ru.nicoolas.warehouse.Exceptions;

import java.util.NoSuchElementException;

/**
 * Класс-исключение на случай если на складе нет необходимого количества носков
 * Наследуется от класса {@link RuntimeException}
 */
public class NotEnoughGoodsOnWarehouseException extends RuntimeException {

    public NotEnoughGoodsOnWarehouseException(String message) {
        super(message);
    }
}
