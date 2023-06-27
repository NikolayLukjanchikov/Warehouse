package ru.nicoolas.warehouse.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Класс-сущность для носков
 */
@Entity
@Data
@IdClass(SocksCompositePK.class)
public class Socks {
    @Id
    @Column(name = "color", nullable = false)
    @NotBlank
    private String color;
    @Id
    @Column(name = "cotton_part")
    @NotBlank
    private Integer cottonPart;
    @Column(name = "total_quantity")
    @NotBlank
    @NotNull
    private Integer quantity;

}