package com.farmacia9.api.domain.produto;

import jakarta.validation.constraints.NotNull;

//Dto com os contendo os atributos descricao e preco, a serem atualizados - dados serão devolvidos no método atualizar na classe ProdutoController
public record DadosAtualizacaoProduto(

        @NotNull
        int id,
        String descricao,
        Double preco) {
}
