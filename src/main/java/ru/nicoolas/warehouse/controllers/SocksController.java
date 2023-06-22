package ru.nicoolas.warehouse.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
@Tag(name = "Управляем носками", description = "Позволяет управлять методами по работе с носками")
public class SocksController {
    private SocksRepository socksRepository;

    @GetMapping
    public List<Socks> allSocks() {
        return new ArrayList<>();
    }

}
