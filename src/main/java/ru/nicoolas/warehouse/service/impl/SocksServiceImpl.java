package ru.nicoolas.warehouse.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nicoolas.warehouse.Exceptions.NotEnoughGoodsOnWarehouseException;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;
import ru.nicoolas.warehouse.service.SocksService;


@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    final private SocksRepository socksRepository;

    public void addSocks(Socks socks) {
        checkQuantity(socks);
        socks.setColor(socks.getColor().toLowerCase());
        int totalQuantity = getEqualTotalQuantity(socks);
        socks.setQuantity(totalQuantity + socks.getQuantity());
        socksRepository.save(socks);
    }

    public void deleteSocks(Socks socks) {
        checkQuantity(socks);
        socks.setColor(socks.getColor().toLowerCase());
        int totalQuantity = getEqualTotalQuantity(socks);
        if (totalQuantity < socks.getQuantity()) {
            throw new NotEnoughGoodsOnWarehouseException("Недостаточно товара на складе");
        }
        socks.setQuantity(totalQuantity - socks.getQuantity());
        socksRepository.save(socks);
    }

    public int getTotalQuantity(String color, String operation, int cottonPart) {
        switch (operation) {
            case "moreThan":
                return socksRepository.getTotalQuantityByColorAndCottonPartGreaterThan(color, cottonPart).orElse(0);
            case "lessThan":
                return socksRepository.getTotalQuantityByColorAndCottonPartLessThan(color, cottonPart).orElse(0);
            case "equal":
                return socksRepository.getTotalQuantityByColorAndCottonPartEquals(color, cottonPart).orElse(0);
            default:
                throw new IllegalArgumentException("Введены некорректные параметры запроса: " + operation);
        }
    }

    private void checkQuantity(Socks socks) {
        if (socks.getQuantity() <= 0) {
            throw new IllegalArgumentException("Значение quantity должно быть больше нуля");
        }
        if (socks.getColor().isBlank()) {
            throw new IllegalArgumentException("Значение color не может быть пустым");
        }
        if (socks.getCottonPart() < 0 || socks.getCottonPart() > 100) {
            throw new IllegalArgumentException("Значение cottonPart не может быть меньше 0 и больше 100");
        }
    }

    private int getEqualTotalQuantity(Socks socks) {
        return socksRepository.getTotalQuantityByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).orElse(0);
    }


}

