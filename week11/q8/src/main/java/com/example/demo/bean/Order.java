package com.example.demo.bean;


public class Order {

    private long productId;
    private String productName;
    private String productCnt;

    @Override
    public String toString() {
        return "Order{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCnt='" + productCnt + '\'' +
                '}';
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCnt() {
        return productCnt;
    }

    public void setProductCnt(String productCnt) {
        this.productCnt = productCnt;
    }
}
