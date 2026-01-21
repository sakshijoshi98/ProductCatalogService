package dev.sakshijoshi.productcatalogservice.dtos;

import dev.sakshijoshi.productcatalogservice.models.Category;
import dev.sakshijoshi.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakestoreProductDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;


    public Product from(FakestoreProductDTO fakestoreProductDTO) {
        Product product = new Product();

        product.setId(fakestoreProductDTO.getId());
        product.setName(fakestoreProductDTO.getTitle());
        product.setDescription(fakestoreProductDTO.getDescription());
        product.setPrice(fakestoreProductDTO.getPrice());
        product.setImageURL(fakestoreProductDTO.getImage());

        //we have category as an object in product but in fakestore we have string ,so we will create an object of category

        Category category = new Category();
        category.setName(fakestoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }
}
