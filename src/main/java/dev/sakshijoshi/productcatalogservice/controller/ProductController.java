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

        List<Product> products = productService.getAllProducts();

        if(products != null){
            for(Product product : products){
                ProductDTO productDto = product.convert();
                productDTOList.add(productDto);
            }
        }

        return productDTOList;
    }

    @PutMapping("/products/{productId}")
    ProductDTO updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDTO productDTO){
        ProductDTO productResponseDTO = new ProductDTO();

        Product product = productService.replaceProduct(productDTO.convertToProduct(),productId);

        if(product != null){
            return product.convert();
        }

        return null;
    }
}
