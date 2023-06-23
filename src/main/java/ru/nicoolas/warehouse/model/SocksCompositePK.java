package ru.nicoolas.warehouse.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocksCompositePK implements Serializable {
    private String color;
    private Integer cottonPart;
}
