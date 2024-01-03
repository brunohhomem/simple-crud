package com.challenge.simplecrud.controllers;

import com.challenge.simplecrud.domain.product.Product;
import com.challenge.simplecrud.domain.product.ProductRepository;
import com.challenge.simplecrud.domain.product.RequestProductDTO;
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
//        var allProducts = productRepository.findAll();
        return ResponseEntity.ok(productRepository.findAll());
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
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
