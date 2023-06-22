package ru.nicoolas.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;


@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
@Tag(name = "Управляем носками", description = "Позволяет управлять методами по работе с носками")
public class SocksController {
    private SocksRepository socksRepository;

    @Operation(summary = "Метод регистрирует приход носков на склад", description = "регистрация прихода с параметрами",
            responses = {
                    @ApiResponse(responseCode = "200", description = "удалось добавить приход", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).", content = @Content(schema = @Schema()))
            })
    @PostMapping("/income")
    public ResponseEntity<Void> income(@RequestBody Socks socks) {
        // socksRepository.addNew(socks);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса",
            responses = {
                    @ApiResponse(responseCode = "200", description = "запрос выполнен", content = @Content(schema = @Schema(implementation = Integer.class))),
                    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат.", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна.", content = @Content(schema = @Schema()))
            })
    @GetMapping
    public ResponseEntity<Integer> getTotalSocksCount(@RequestParam("color") String color,
                                                      @RequestParam("operation") String operation,
                                                      @RequestParam("cottonPart") int cottonPart) {
        if (color == null || operation == null || (operation.equals("moreThan") && operation.equals("lessThan") && operation.equals("equal"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            int totalSocksCount = 3;
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(totalSocksCount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
