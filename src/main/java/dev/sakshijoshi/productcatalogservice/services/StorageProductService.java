package dev.sakshijoshi.productcatalogservice.services;

import dev.sakshijoshi.productcatalogservice.models.Product;
import dev.sakshijoshi.productcatalogservice.models.State;
import dev.sakshijoshi.productcatalogservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Primary
public class StorageProductService implements  IProductService{

    private ProductRepository productRepository;

    public StorageProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product getProductById(Long id) {
        // here the datatype is optional if product is null it will return null without any error
        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId()); // product exist or not

        if (optionalProduct.isEmpty()) {
           return createProduct(product);
        }
        else {
            return null;
        }

    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());

        if (optionalProduct.isEmpty()) {
            return null; //if product doesn't exist who need to update
        }
        else{
            product.setId(id);

            // it will extract the creation date from already existing product and then save it to updated product
            product.setCreatedAt(optionalProduct.get().getCreatedAt());

            return productRepository.save(product);
        }
    }

    public boolean deleteProduct(Product input) {
        Optional<Product> optionalProduct = productRepository.findById(input.getId());

        if (optionalProduct.isEmpty()) {
            return false;
        }
        else{
            // we are not deleting the product if we delete we can never retrieve it from database, so we are setting it to inactive
            Product product = optionalProduct.get();
            if(product.getState().equals(State.ACTIVE)){
                product.setState(State.INACTIVE);
                productRepository.save(product);
            }
            return true;
        }
    }
}
