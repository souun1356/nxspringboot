package com.example.demo.domain;

/**
 * 訂單中一項
 */
public class OrderItem {
    private String order_id; // 訂單號
    private String product_id; // 商品號
    private int buynum; // 購買數量

    // 添加product的名稱和單價
    private String name;
    private double price;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String orderId) {
        order_id = orderId;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String productId) {
        product_id = productId;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
