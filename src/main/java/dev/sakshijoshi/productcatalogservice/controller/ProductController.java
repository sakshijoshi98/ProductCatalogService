package dev.sakshijoshi.productcatalogservice.controller;


import dev.sakshijoshi.productcatalogservice.dtos.ProductDTO;
import dev.sakshijoshi.productcatalogservice.models.Product;
import dev.sakshijoshi.productcatalogservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    IProductService productService;


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // create product
    @PostMapping("/products")
    ProductDTO createProduct(@RequestBody ProductDTO product){
          ProductDTO productDTO = new ProductDTO();
          return productDTO;
    }


    //get product by id
    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id){
        if(id < 1){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productService.getProductById(id);

        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDTO productDto = product.convert();

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }


    //get all product
    @GetMapping("/products")
    List<ProductDTO> getAllProduct(){
        List<ProductDTO> productDTOList = new ArrayList<>();
        return productDTOList;
    }
}
