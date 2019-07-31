package com.myfitband.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "physicalProperties")
public class PhysicalProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer physicalPropertyId;

    @OneToOne
    private User user;

    private Integer height;

    private Double fatMass;

    private Double boneMass;

    private Double muscleMass;

    private Double water;

    public Integer getPhysicalPropertyId() {
        return physicalPropertyId;
    }

    public void setPhysicalPropertyId(Integer physicalPropertyId) {
        this.physicalPropertyId = physicalPropertyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getFatMass() {
        return fatMass;
    }

    public void setFatMass(Double fatMass) {
        this.fatMass = fatMass;
    }

    public Double getBoneMass() {
        return boneMass;
    }

    public void setBoneMass(Double boneMass) {
        this.boneMass = boneMass;
    }

    public Double getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(Double muscleMass) {
        this.muscleMass = muscleMass;
    }

    public Double getWater() {
        return water;
    }

    public void setWater(Double water) {
        this.water = water;
    }
}
