package com.farmacia9.api.domain.produto;

import com.farmacia9.api.domain.fabricante.Fabricante;

public record DadosDetalhamentoProduto(int id, String nome, String descricao, Double preco, Fabricante fabricante) {

    public DadosDetalhamentoProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getFabricante());
    }
}
