package ru.nicoolas.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.service.impl.SocksServiceImpl;

/**
 * Класс-контроллер для работы со всеми операциями со складом носков
 */
@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
@Tag(name = "Система управления складом", description = "Позволяет управлять методами по работе с товарами")
public class SocksController {
    private SocksServiceImpl socksService;

    /**
     * Метод для добавления носков на склад
     *
     * @param socks носки которые необходимо добавить
     * @return http статус операции
     */
    @Operation(summary = "Метод регистрирует приход носков на склад", description = "регистрация прихода с параметрами",
            responses = {
                    @ApiResponse(responseCode = "200", description = "удалось добавить приход", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).", content = @Content(schema = @Schema()))
            })
    @PostMapping("/income")
    public ResponseEntity<Void> income(@RequestBody Socks socks) {
        socksService.addSocks(socks);
        return ResponseEntity.ok().build();

    }

    /**
     * Метод для удаления носков со склада
     *
     * @param socks носки которые необходимо удалить
     * @return http статус операции
     */
    @Operation(summary = "Метод регистрирует отпуск носков со склада", description = "отпуск носков с параметрами",
            responses = {
                    @ApiResponse(responseCode = "200", description = "удалось провести выбытие", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).", content = @Content(schema = @Schema()))
            })
    @PostMapping("/outcome")
    public ResponseEntity<Void> outcome(@RequestBody Socks socks) {
        socksService.deleteSocks(socks);
        return ResponseEntity.ok().build();
    }

    /**
     * Метод для получения количества носков на складе согласно параметрам
     *
     * @param color      цвет носков
     * @param operation  операция сравнения
     * @param cottonPart содержание хлопка в носках
     * @return количество носков подходящих по заданным параметрам в виде строкового представления целого числа
     */
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса",
            responses = {
                    @ApiResponse(responseCode = "200", description = "запрос выполнен", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат.", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна.", content = @Content(schema = @Schema()))
            })
    @GetMapping
    public ResponseEntity<String> getTotalSocksCount(@RequestParam("color") String color,
                                                     @RequestParam("operation") String operation,
                                                     @RequestParam("cottonPart") int cottonPart) {
        return ResponseEntity.ok()
                .body((String.valueOf(socksService.getTotalQuantity(color, operation, cottonPart))));
    }
}
