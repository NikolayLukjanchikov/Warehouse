package ru.nicoolas.warehouse.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@IdClass(SocksCompositePK.class)
public class Socks {
    @Id
    @Column(name = "color", nullable = false)
    private String color;
    @Id
    @Column(name = "cotton_part")
    private Integer cottonPart;
    @NotNull
    @Column(name = "total_quantity")
    private Integer quantity;
}