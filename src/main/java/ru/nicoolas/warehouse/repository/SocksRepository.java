package ru.nicoolas.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nicoolas.warehouse.model.Socks;

import java.util.Optional;
@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    @Query(value = "SELECT SUM(total_quantity) FROM socks WHERE color = :color AND cotton_part > :cottonPart", nativeQuery = true)
    Optional<Integer> getTotalQuantityByColorAndCottonPartGreaterThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query(value = "SELECT SUM(total_quantity) FROM socks WHERE color = :color AND cotton_part < :cottonPart", nativeQuery = true)
    Optional<Integer> getTotalQuantityByColorAndCottonPartLessThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query(value = "SELECT SUM(total_quantity) FROM socks WHERE color = :color AND cotton_part = :cottonPart", nativeQuery = true)
    Optional<Integer> getTotalQuantityByColorAndCottonPartEquals(@Param("color") String color, @Param("cottonPart") int cottonPart);

}


