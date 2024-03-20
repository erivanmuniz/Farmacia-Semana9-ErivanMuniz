package com.farmacia9.api.domain.produto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Dto contendo os dados para cadastro de produtos e fabricante
public record DadosCadastroProduto(

        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Descrição é obrigatório")
        String descricao,
        @NotNull(message = "Preço é obrigatorio")
        Double preco,
        @NotBlank(message = "Fabricante é obrigatório")
        @Valid
        String fabricante) {

}


