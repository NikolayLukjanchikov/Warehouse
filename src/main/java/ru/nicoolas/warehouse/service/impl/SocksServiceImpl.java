package ru.nicoolas.warehouse.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;
import ru.nicoolas.warehouse.service.SocksService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private SocksRepository socksRepository;

    public void addSocks(Socks socks) {
        int totalQuantity = 0;
        Socks socks1 = new Socks();
        socks1.setColor(socks.getColor().toLowerCase());
        socks1.setCottonPart(socks.getCottonPart());
        Optional<Integer> count = socksRepository.getTotalQuantityByColorAndCottonPartEquals(socks1.getColor(), socks1.getCottonPart());
        if(count.isPresent()) {
            totalQuantity = count.get();
        }
        socks1.setQuantity(totalQuantity + socks.getQuantity());
        socksRepository.save(socks1);
    }



}
