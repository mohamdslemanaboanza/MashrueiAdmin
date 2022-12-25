package com.example.mashrueiadmin.Model;

public class StoreModel
{
    private String nameStore;
    private String descriptionStore;
    private String phoneStore;
    private String workTimeStore;
    private int isActiveStore;
    private String imgUriStore;
    private String cityStore;
    private String nameUser;
    private String phoneUser;
    private String keyUser;
    private boolean thereAreItems;
    private double latitude;
    private double longitude;
    private String locationStore;
    private String typeStore;
    private String tokenStore;







    public StoreModel(String nameStore, String descriptionStore, String phoneStore, String workTimeStore, int isActiveStore, String imgUriStore, String cityStore, String nameUser, String phoneUser, String keyUser, boolean thereAreItems, double latitude, double longitude, String locationStore, String typeStore,String tokenStore) {
        this.nameStore = nameStore;
        this.descriptionStore = descriptionStore;
        this.phoneStore = phoneStore;
        this.workTimeStore = workTimeStore;
        this.isActiveStore = isActiveStore;
        this.imgUriStore = imgUriStore;
        this.cityStore = cityStore;
        this.nameUser = nameUser;
        this.phoneUser = phoneUser;
        this.keyUser = keyUser;
        this.thereAreItems = thereAreItems;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationStore = locationStore;
        this.typeStore = typeStore;
        this.tokenStore=tokenStore;
    }


    public StoreModel() {
    }


    public String getTokenStore() {
        return tokenStore;
    }

    public void setTokenStore(String tokenStore) {
        this.tokenStore = tokenStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getDescriptionStore() {
        return descriptionStore;
    }

    public void setDescriptionStore(String descriptionStore) {
        this.descriptionStore = descriptionStore;
    }

    public String getPhoneStore() {
        return phoneStore;
    }

    public void setPhoneStore(String phoneStore) {
        this.phoneStore = phoneStore;
    }

    public String getWorkTimeStore() {
        return workTimeStore;
    }

    public void setWorkTimeStore(String workTimeStore) {
        this.workTimeStore = workTimeStore;
    }

    public int getIsActiveStore() {
        return isActiveStore;
    }

    public void setIsActiveStore(int isActiveStore) {
        this.isActiveStore = isActiveStore;
    }

    public String getImgUriStore() {
        return imgUriStore;
    }

    public void setImgUriStore(String imgUriStore) {
        this.imgUriStore = imgUriStore;
    }

    public String getCityStore() {
        return cityStore;
    }

    public void setCityStore(String cityStore) {
        this.cityStore = cityStore;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public boolean isThereAreItems() {
        return thereAreItems;
    }

    public void setThereAreItems(boolean thereAreItems) {
        this.thereAreItems = thereAreItems;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationStore() {
        return locationStore;
    }

    public void setLocationStore(String locationStore) {
        this.locationStore = locationStore;
    }

    public String getTypeStore() {
        return typeStore;
    }

    public void setTypeStore(String typeStore) {
        this.typeStore = typeStore;
    }
}
