package com.example.mashrueiadmin.Model;

public class ProductModel
{

    public String idProduct;
    public String nameProduct;
    public String priceProduct;
    public String deskProduct;
    public String quantityProduct;
    public String minQuantity;
    public String maxQuantity;
    public long dateProduct;
    public int isActive;
    public String imageScreen;
    public String idStore;
    public String hintProduct;
    public String typeStore;


    public String nameUser;
    public double lat;
    public double lon;
    public String phoneNumber;
    public String city;
    public String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ProductModel(String idProduct, String nameProduct, String priceProduct, String deskProduct, String quantityProduct, String minQuantity, String maxQuantity, long dateProduct, int isActive, String imageScreen, String idStore, String hintProduct,String typeStore) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.deskProduct = deskProduct;
        this.quantityProduct = quantityProduct;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.dateProduct = dateProduct;
        this.isActive = isActive;
        this.imageScreen = imageScreen;
        this.idStore = idStore;
        this.hintProduct = hintProduct;
        this.typeStore = typeStore;

    }




    public String getHintProduct() {
        return hintProduct;
    }

    public void setHintProduct(String hintProduct) {
        this.hintProduct = hintProduct;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getTypeStore() {
        return typeStore;
    }

    public void setTypeStore(String typeStore) {
        this.typeStore = typeStore;
    }

    public ProductModel() {
    }


    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getDeskProduct() {
        return deskProduct;
    }

    public void setDeskProduct(String deskProduct) {
        this.deskProduct = deskProduct;
    }

    public String getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(String maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public long getDateProduct() {
        return dateProduct;
    }

    public void setDateProduct(long dateProduct) {
        this.dateProduct = dateProduct;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getImageScreen() {
        return imageScreen;
    }

    public void setImageScreen(String imageScreen) {
        this.imageScreen = imageScreen;
    }
}
