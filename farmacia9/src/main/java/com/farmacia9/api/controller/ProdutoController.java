package com.farmacia9.api.controller;

import com.farmacia9.api.domain.fabricante.Fabricante;
import com.farmacia9.api.domain.fabricante.FabricanteRepository;
import com.farmacia9.api.domain.produto.DadosListagemProduto;
import com.farmacia9.api.domain.produto.Produto;
import com.farmacia9.api.domain.produto.ProdutoRepository;
import com.farmacia9.api.domain.produto.*;
import com.farmacia9.api.domain.produto.DadosCadastroProduto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        // Verifica se o fabricante já existe no banco de dados
        Fabricante fabricante = fabricanteRepository.findByNome(dados.fabricante());
        if (fabricante == null) {
            // Se não existir, cria um novo fabricante
            fabricante = new Fabricante();
            fabricante.setNome(dados.fabricante());
            fabricante = fabricanteRepository.save(fabricante);
        }

        // Cria o produto associado ao fabricante
        var produto = new Produto(dados);
        produto.setNome(dados.nome());
        produto.setDescricao(dados.descricao());
        produto.setPreco(dados.preco());
        produto.setFabricante(fabricante);

        // Salva o produto

        produtoRepository.save(produto);

        var uri =uriBuilder.path("/medicos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }
    //Método para listar os produtos
    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> Listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page =  produtoRepository.findAll(paginacao).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

    //Método para atualiazar os dados do produto (descrição e preço)
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    //Método para excluir um produto
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable int id) {
        var produto = produtoRepository.getReferenceById(id);
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Método para detalhar um produto
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable int id) {
        var produto = produtoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

}
