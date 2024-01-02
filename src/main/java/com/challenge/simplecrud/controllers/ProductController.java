package com.challenge.simplecrud.controllers;

import com.challenge.simplecrud.domain.product.Product;
import com.challenge.simplecrud.domain.product.ProductRepository;
import com.challenge.simplecrud.domain.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity getAllProducts(){
//        var allProducts = productRepository.findAll();
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid ProductDTO data){
        System.out.println(data); // repository nao salva o dado assim

        Product newProduct = new Product(data); //precisa instanciar o dado com a entidade

        productRepository.save(newProduct); //repository salva o dado instanciado pela entidade
        return ResponseEntity.ok().build();
    }

}
