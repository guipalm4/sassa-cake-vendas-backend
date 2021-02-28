package com.sassacakes.sales.product.dto;

import com.google.common.base.Objects;
import com.sassacakes.sales.product.model.Category;

import java.math.BigDecimal;

public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal cost;
    private String price;
    private Category category;
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


    public static final class Builder {
        private Integer id;
        private String name;
        private String description;
        private BigDecimal cost;
        private String price;
        private Category category;
        private String imageUrl;

        private Builder() {
        }

        public static Builder aProductDto() {
            return new Builder();
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder cost(BigDecimal cost) {
            this.cost = cost;
            return this;
        }

        public Builder price(String price) {
            this.price = price;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder imageUrl(String url) {
            this.imageUrl = url;
            return this;
        }

        public ProductDto build() {
            ProductDto productDto = new ProductDto();
            productDto.setId(id);
            productDto.setName(name);
            productDto.setDescription(description);
            productDto.setCost(cost);
            productDto.setPrice(price);
            productDto.setCategory(category);
            productDto.setImageUrl(imageUrl);
            return productDto;
        }
    }
}
