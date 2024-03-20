package com.farmacia9.api.domain.fabricante;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "fabricantes")
@Entity(name = "Fabricante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
}
