package dev.sakshijoshi.productcatalogservice.services;

import dev.sakshijoshi.productcatalogservice.dtos.FakestoreProductDTO;
import dev.sakshijoshi.productcatalogservice.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakestoreProductService implements IProductService {

    private RestTemplate restTemplate;

    private FakestoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getProductById(Long id) {
        //if it's not null
//        FakestoreProductDTO fakestoreProductDTO = restTemplate.getForObject("http://fakestoreapi.com/products/{id}" ,
//                FakestoreProductDTO.class,
//                id);

        // if it's null
        ResponseEntity<FakestoreProductDTO> fakestoreProductDtoResponseEntity = restTemplate.getForEntity("http://fakestoreapi.com/products/{id}" ,
                FakestoreProductDTO.class,
                id);

        if(fakestoreProductDtoResponseEntity.hasBody() &&
                fakestoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return fakestoreProductDtoResponseEntity.getBody().from(fakestoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
