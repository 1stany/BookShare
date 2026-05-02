package org.projectwork.bookshare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lockerpoint;
    private Double latitude;
    private Double longitude;

    public City() {
    }

    public City(long id, String name, String lockerpoint, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.lockerpoint = lockerpoint;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLockerpoint() {
        return lockerpoint;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
