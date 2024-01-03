package com.challenge.simplecrud.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, String> {
    List<Product> findAllByActiveTrue(); //Cria um método seguindo as convenções do Spring para listar todos os produtos ativos;
}
