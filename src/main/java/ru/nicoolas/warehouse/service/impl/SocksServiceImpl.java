package ru.nicoolas.warehouse.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nicoolas.warehouse.Exceptions.NotEnoughtGoodsOnWarehouseException;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;
import ru.nicoolas.warehouse.service.SocksService;

import java.security.InvalidParameterException;


@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private SocksRepository socksRepository;

    public void addSocks(Socks socks) {
        if (socks.getQuantity() <= 0) {
            throw new IllegalArgumentException("Значение quantity должно быть больше нуля");
        }
        socks.setColor(socks.getColor().toLowerCase());
        socks.setQuantity(getEqualTotalQuantity(socks) + socks.getQuantity());
        socksRepository.save(socks);
    }

    public void deleteSocks(Socks socks) {
        if (socks.getQuantity() <= 0) {
            throw new IllegalArgumentException("Значение quantity должно быть больше нуля");
        }
        socks.setColor(socks.getColor().toLowerCase());
        int totalQuantity = getEqualTotalQuantity(socks);
        if (totalQuantity >= socks.getQuantity()) {
            socks.setQuantity(totalQuantity - socks.getQuantity());
            socksRepository.save(socks);
        } else {
            throw new NotEnoughtGoodsOnWarehouseException("Недостаточно товара на складе");
        }
    }

    private int getEqualTotalQuantity(Socks socks) {
        return socksRepository.getTotalQuantityByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).orElse(0);
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
                throw new InvalidParameterException("Введены некорректные параметры запроса");
        }
    }

}

