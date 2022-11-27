package com.acme.balloons.Beans;

public class Product {
    private int productid;
    private String productname;
    private double price;
    private String productDescription;
    private int categoryId;
    
    
    public Product() {} 
    
    public Product (int productId, String productname, double price, 
            String productDescription, int categoryId){
        this.productid = productId;
        this.productname = this.productname;
        this.price = price;
        this.productDescription = productDescription;
        this.categoryId = categoryId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
    
}
