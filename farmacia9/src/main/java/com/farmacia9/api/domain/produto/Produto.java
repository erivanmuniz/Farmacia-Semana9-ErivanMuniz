package com.farmacia9.api.domain.produto;

import com.farmacia9.api.domain.fabricante.Fabricante;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    private double preco;

    @ManyToOne(fetch = FetchType.EAGER)
    private Fabricante fabricante;


    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
    }

    //método para validar atualizações no método atualizar Produto na classe ProdutoController
    //Verifica se os dados são nulos ou não, antes de atualizar o produto no banco de dados
    public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if (dados.preco() != null){
            this.preco = dados.preco();
        }

    }
}
