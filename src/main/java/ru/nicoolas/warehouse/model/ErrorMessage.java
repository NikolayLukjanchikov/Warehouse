package ru.nicoolas.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс-сущность для сообщений при возникновении исключений
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private String message;
}
