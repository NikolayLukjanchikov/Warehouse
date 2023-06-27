package ru.nicoolas.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nicoolas.warehouse.model.Socks;

import java.util.Optional;

/**
 * Интерфейс-репозиторий для работы с методами всех объявлений, опубликованных на платформе.
 * Наследуется от интерфейса {@link JpaRepository}
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    /**
     * Метод поиска суммарного значения носков с содержанием хлопка больше заданного значения
     *
     * @param color      цвет носков
     * @param cottonPart содержание хлопка
     * @return возращает сумму всех найденых носков
     */
    @Query(value = "SELECT SUM(total_quantity) FROM socks WHERE color = :color AND cotton_part > :cottonPart", nativeQuery = true)
    Optional<Integer> getTotalQuantityByColorAndCottonPartGreaterThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    /**
     * Метод поиска суммарного значения носков с содержанием хлопка меньше заданного значения
     *
     * @param color      цвет носков
     * @param cottonPart содержание хлопка
     * @return возращает сумму всех найденых носков
     */
    @Query(value = "SELECT SUM(total_quantity) FROM socks WHERE color = :color AND cotton_part < :cottonPart", nativeQuery = true)
    Optional<Integer> getTotalQuantityByColorAndCottonPartLessThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    /**
     * Метод поиска суммарного значения носков с содержанием хлопка равное заданномузначения
     *
     * @param color      цвет носков
     * @param cottonPart содержание хлопка
     * @return возращает сумму всех найденых носков
     */
    @Query(value = "SELECT SUM(total_quantity) FROM socks WHERE color = :color AND cotton_part = :cottonPart", nativeQuery = true)
    Optional<Integer> getTotalQuantityByColorAndCottonPartEquals(@Param("color") String color, @Param("cottonPart") int cottonPart);

}