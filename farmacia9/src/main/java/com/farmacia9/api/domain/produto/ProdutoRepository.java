package com.farmacia9.api.domain.produto;


import org.springframework.data.jpa.repository.JpaRepository;

//Interface para fornecer os métodos CRUD para operações dos produtos no banco de dados
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
