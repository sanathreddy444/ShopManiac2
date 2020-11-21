package com.example.myfinalproject;

public class ProductClass {
    String Name, Category, Description, Image;
    Integer id, Price, Quantity;

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public ProductClass(String name, String category, String description, String image, Integer id, Integer price, Integer quantity) {
        this.Name = name;
        this.Category = category;
        this.Description = description;
        this.Image = image;
        this.id = id;
        this.Price = price;
        this.Quantity = quantity;
    }

    public ProductClass() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }


}
