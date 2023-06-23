package ru.nicoolas.warehouse.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    @Id
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "cotton_part")
    private Integer cottonPart;
    @Column(name = "total_quantity")
    private Integer quantity;
}