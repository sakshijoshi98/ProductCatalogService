package dev.sakshijoshi.productcatalogservice.services;

import dev.sakshijoshi.productcatalogservice.client.FakeStoreAPIClient;
import dev.sakshijoshi.productcatalogservice.dtos.FakestoreProductDTO;
import dev.sakshijoshi.productcatalogservice.models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakestoreProductService implements IProductService {

    private FakeStoreAPIClient fakeStoreAPIClient;

    private FakestoreProductService(FakeStoreAPIClient fakeStoreAPIClient) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
    }


    @Override
    public Product getProductById(Long id) {
        //if it's not null
//        FakestoreProductDTO fakestoreProductDTO = restTemplate.getForObject("http://fakestoreapi.com/products/{id}" ,
//                FakestoreProductDTO.class,
//                id);

        // if it's null
        ResponseEntity<FakestoreProductDTO> fakestoreProductDtoResponseEntity = fakeStoreAPIClient.getForEntity("http://fakestoreapi.com/products/{id}" ,
                FakestoreProductDTO.class,
                id);

        if(fakeStoreAPIClient.validateResponse(fakestoreProductDtoResponseEntity)){
            return fakestoreProductDtoResponseEntity.getBody().from(fakestoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        ResponseEntity<FakestoreProductDTO[]> response = fakeStoreAPIClient.getForEntity("https://fakestoreapi.com/products",
                FakestoreProductDTO[].class);

        if(response.hasBody() &&
                response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            FakestoreProductDTO[] fakestoreProductDtos = response.getBody();

            for(FakestoreProductDTO fakestoreProductDto : fakestoreProductDtos){
                products.add(fakestoreProductDto.from(fakestoreProductDto));
            }

            return products;

        }

        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }


    public Product replaceProduct(Product product,Long id) {

        FakestoreProductDTO fakestoreProductDTO = product.convertFakeStoreProduct();

        ResponseEntity<FakestoreProductDTO> response = fakeStoreAPIClient.putForEntity("https://fakestoreapi.com/products/{id}",
                fakestoreProductDTO,
                FakestoreProductDTO.class,
                id);

        if(response.hasBody() &&
                response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
//            FakestoreProductDto fakestoreProductDto1 = response.getBody();
//            return fakestoreProductDto1.from(fakestoreProductDto1);
            return product;
        }

        return  null;
    }
}
