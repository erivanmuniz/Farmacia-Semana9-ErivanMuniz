package com.farmacia9.api.domain.fabricante;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosFabricante(

        @NotBlank(message = "{nome.obrigatorio}")
        @Valid
        String nome) {
}
