package dev.sakshijoshi.productcatalogservice.services;

import dev.sakshijoshi.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {


    Product getProductById(Long id);

    List<Product> getAllProducts();
    Product createProduct(Product product);
}
