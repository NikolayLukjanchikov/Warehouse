package ru.nicoolas.warehouse.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nicoolas.warehouse.Exceptions.NotEnoughtGoodsOnWarehouseException;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;
import ru.nicoolas.warehouse.service.SocksService;


@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private SocksRepository socksRepository;

    public void addSocks(Socks socks) {
        socks.setColor(socks.getColor().toLowerCase());
        socks.setQuantity(getTotalQuantity(socks) + socks.getQuantity());
        socksRepository.save(socks);
    }

    public void deleteSocks(Socks socks) {
        socks.setColor(socks.getColor().toLowerCase());
        int totalQuantity = getTotalQuantity(socks);
        if (totalQuantity >= socks.getQuantity()) {
            socks.setQuantity(totalQuantity - socks.getQuantity());
            socksRepository.save(socks);
        } else {
            throw new NotEnoughtGoodsOnWarehouseException("Недостаточно товара на складе");
        }
    }

    private int getTotalQuantity(Socks socks) {
        return socksRepository.getTotalQuantityByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).orElse(0);
    }

}
