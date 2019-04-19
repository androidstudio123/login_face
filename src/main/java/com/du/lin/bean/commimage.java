package com.du.lin.bean;

public class commimage {
    private  int id;
    private String name;
    private  String commoditytype;
    private String  price;
    private String  originalPrice;
    private String description;
    private String pingxiang;
    private int commodity_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommoditytype() {
        return commoditytype;
    }

    public void setCommoditytype(String commoditytype) {
        this.commoditytype = commoditytype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPingxiang() {
        return pingxiang;
    }

    public void setPingxiang(String pingxiang) {
        this.pingxiang = pingxiang;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }
}
