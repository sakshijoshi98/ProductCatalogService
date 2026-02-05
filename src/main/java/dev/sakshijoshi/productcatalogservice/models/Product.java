package dev.sakshijoshi.productcatalogservice.models;

import dev.sakshijoshi.productcatalogservice.dtos.CategoryDTO;
import dev.sakshijoshi.productcatalogservice.dtos.FakestoreProductDTO;
import dev.sakshijoshi.productcatalogservice.dtos.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private Double price;
    private String imageURL;
    private Category category;

    public ProductDTO convert() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(this.getId());
        productDTO.setName(this.getName());
        productDTO.setDescription(this.getDescription());
        productDTO.setPrice(this.getPrice());
        productDTO.setImageURL(this.getImageURL());

        if(this.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(this.getCategory().getId());
            categoryDTO.setName(this.getCategory().getName());
            categoryDTO.setDescription(this.getCategory().getDescription());
            productDTO.setCategory(categoryDTO);
        }

        return productDTO;
    }


    public FakestoreProductDTO convertFakeStoreProduct() {
        FakestoreProductDTO fakestoreProductDTO = new FakestoreProductDTO();

        fakestoreProductDTO.setId(this.getId());
        fakestoreProductDTO.setTitle(this.getName());
        fakestoreProductDTO.setDescription(this.getDescription());
        fakestoreProductDTO.setPrice(this.getPrice());
        fakestoreProductDTO.setImage(this.getImageURL());
        if(this.getCategory() != null) {
            fakestoreProductDTO.setCategory(this.getCategory().getName());
        }

        return fakestoreProductDTO;
    }
}
