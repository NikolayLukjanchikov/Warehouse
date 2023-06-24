package ru.nicoolas.warehouse.model;

import io.swagger.v3.oas.annotations.callbacks.Callbacks;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

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