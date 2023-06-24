package ru.nicoolas.warehouse.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nicoolas.warehouse.Exceptions.NotEnoughGoodsOnWarehouseException;
import ru.nicoolas.warehouse.model.ErrorMessage;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NotEnoughGoodsOnWarehouseException.class)
    public ResponseEntity<ErrorMessage> notEnoughException(NotEnoughGoodsOnWarehouseException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> IllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
