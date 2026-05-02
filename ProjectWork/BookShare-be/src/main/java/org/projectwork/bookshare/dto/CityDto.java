package org.projectwork.bookshare.dto;

import org.projectwork.bookshare.model.City;

public class CityDto {
    private long id;
    private String name;
    private String lockerpoint;
    private Double latitude;
    private Double longitude;

    public CityDto() {
    }

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.lockerpoint = city.getLockerpoint();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
    }

    public City toCity() {
        return new City(this.id, this.name, this.lockerpoint, this.latitude, this.longitude);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLockerpoint() {
        return lockerpoint;
    }

    public void setLockerpoint(String lockerpoint) {
        this.lockerpoint = lockerpoint;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}