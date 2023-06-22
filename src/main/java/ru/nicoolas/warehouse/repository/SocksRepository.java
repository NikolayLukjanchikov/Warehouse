package ru.nicoolas.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nicoolas.warehouse.model.Socks;

public interface SocksRepository extends JpaRepository<Socks, Long> {
}
