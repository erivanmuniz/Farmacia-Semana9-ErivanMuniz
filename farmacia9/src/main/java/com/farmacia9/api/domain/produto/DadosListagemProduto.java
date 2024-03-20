package com.farmacia9.api.domain.produto;

import com.farmacia9.api.domain.fabricante.Fabricante;

public record DadosListagemProduto(int id, String nome, String descricao, Double preco, Fabricante fabricante) {

    //Construtor que recebe um objeto do tipo produto para devolver a lista no método listar na classe ProdutoController
    public DadosListagemProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getFabricante());
    }
}
