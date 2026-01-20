package dev.sakshijoshi.productcatalogservice.controller;


import dev.sakshijoshi.productcatalogservice.dtos.ProductRequestDTO;
import dev.sakshijoshi.productcatalogservice.dtos.ProductResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    // create product
    @PostMapping("/products")
    ProductResponseDTO createProduct(@RequestBody ProductRequestDTO product){
          ProductResponseDTO productResponseDTO = new ProductResponseDTO();

          return productResponseDTO;
    }


    //get product by id
    @GetMapping("/products/{id}")
    ProductResponseDTO getProductId(@PathVariable("id") Long id){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        return productResponseDTO;
    }


    //get all product
    @GetMapping("/products")
    List<ProductResponseDTO> getAllProduct(){
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        return productResponseDTOList;
    }
}
