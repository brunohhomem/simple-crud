package com.challenge.simplecrud.controllers;

import com.challenge.simplecrud.domain.product.Product;
import com.challenge.simplecrud.domain.product.ProductRepository;
import com.challenge.simplecrud.domain.product.RequestProductDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity getAllProductsActive(){
//        return ResponseEntity.ok(productRepository.findAll());
        return ResponseEntity.ok(productRepository.findAllByActiveTrue()); //Ao inves de retornar todos os produtos, retorna apenas os ativos
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProductDTO data){
        System.out.println(data); // repository nao salva o dado assim

        Product newProduct = new Product(data); //precisa instanciar o dado com a entidade

        productRepository.save(newProduct); //repository salva o dado instanciado pela entidade
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProductDTO data){
        Optional<Product> optionalProduct = productRepository.findById(data.id()); //buscando o produto por id e salvando na variavel
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id){{
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

//    Desabilitando o metodo de excluir o produto por id, para usar o método de inativar o produto
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteProduct(@PathVariable String id){
//        productRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}}
