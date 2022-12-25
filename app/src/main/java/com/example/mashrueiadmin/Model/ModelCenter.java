package com.example.mashrueiadmin.Model;

public class ModelCenter
{
    private String video;
    private String name;
    private double lat;
    private double lon;
    private String phoneNumber;
    private String location;
    private String desk;


    public ModelCenter(String video, String name, double lat, double lon, String phoneNumber, String location, String desk) {
        this.video = video;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.desk = desk;

    }

    public ModelCenter() {

    }





    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }


    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCity() {
        return location;
    }

    public void setCity(String city) {
        this.location = city;
    }
}
