package dev.sakshijoshi.productcatalogservice.dtos;

import dev.sakshijoshi.productcatalogservice.models.Category;
import dev.sakshijoshi.productcatalogservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private CategoryDTO category;
    private String imageURL;

    public Product convertToProduct(){
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(imageURL);
        if(product.getCategory() != null){
            Category category1 = new Category();
            category1.setId(product.getCategory().getId());
            category1.setName(product.getCategory().getName());
            category1.setDescription(product.getCategory().getDescription());
            product.setCategory(category1);
        }

        return product;
    }
}
