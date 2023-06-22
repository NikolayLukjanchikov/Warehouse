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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "cotton_part", nullable = false)
    private String cottonPart;

}