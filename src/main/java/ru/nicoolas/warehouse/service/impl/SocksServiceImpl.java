package ru.nicoolas.warehouse.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nicoolas.warehouse.Exceptions.NotEnoughGoodsOnWarehouseException;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;
import ru.nicoolas.warehouse.service.SocksService;

/**
 * Сервис-класс содержжащий логику работы с носками
 * Реализует интерфейс {@link SocksService}
 */
@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    public static final int MIN_EDGE = 0;
    public static final int MAX_EDGE = 100;

    final private SocksRepository socksRepository;

    /**
     * Метод для добавления носков на склад
     *
     * @param socks носки в JSON
     */
    public void addSocks(Socks socks) {
        checkQuantity(socks);
        socks.setColor(socks.getColor().toLowerCase());
        int totalQuantity = getEqualTotalQuantity(socks);
        socks.setQuantity(totalQuantity + socks.getQuantity());
        socksRepository.save(socks);
    }

    /**
     * Метод для удаления носков со склада
     *
     * @param socks носки в JSON
     */
    public void deleteSocks(Socks socks) {
        checkQuantity(socks);
        socks.setColor(socks.getColor().toLowerCase());
        int totalQuantity = getEqualTotalQuantity(socks);
        if (totalQuantity < socks.getQuantity()) {
            throw new NotEnoughGoodsOnWarehouseException("Недостаточно товара на складе");
        }
        totalQuantity -= socks.getQuantity();
        if (totalQuantity == 0) {
            socksRepository.delete(socks);
        } else {
            socks.setQuantity(totalQuantity);
            socksRepository.save(socks);
        }
    }

    /**
     * Метод для получения количества носков на складе по заданным параметрам
     *
     * @param color      цвет носков
     * @param operation  операция сравнения
     * @param cottonPart содержание хлопка в носках
     * @return возврашает челочисленное количество носков на складе согласно параметрам
     */
    public int getTotalQuantity(String color, String operation, int cottonPart) {
        color = color.trim().toLowerCase();
        return switch (operation) {
            case "moreThan" ->
                    socksRepository.getTotalQuantityByColorAndCottonPartGreaterThan(color, cottonPart).orElse(0);
            case "lessThan" ->
                    socksRepository.getTotalQuantityByColorAndCottonPartLessThan(color, cottonPart).orElse(0);
            case "equal" -> socksRepository.getTotalQuantityByColorAndCottonPartEquals(color, cottonPart).orElse(0);
            default -> throw new IllegalArgumentException("Введены некорректные параметры запроса: " + operation);
        };
    }

    private void checkQuantity(Socks socks) {
        if (socks.getQuantity() <= MIN_EDGE) {
            throw new IllegalArgumentException("Значение quantity должно быть больше нуля");
        }
        if (socks.getColor().isBlank()) {
            throw new IllegalArgumentException("Значение color не может быть пустым");
        }
        if (socks.getCottonPart() < MIN_EDGE || socks.getCottonPart() > MAX_EDGE) {
            throw new IllegalArgumentException("Значение cottonPart не может быть меньше 0 и больше 100");
        }
    }

    private int getEqualTotalQuantity(Socks socks) {
        return socksRepository.getTotalQuantityByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart()).orElse(0);
    }

}

