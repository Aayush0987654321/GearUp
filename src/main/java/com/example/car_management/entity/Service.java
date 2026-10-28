package com.example.car_management.entity;

import jakarta.persistence.*;

@Entity

@Table(name = "service")

public class Service {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "service_id")

    private Long serviceId;

    @Column(name = "service_type", nullable = false)

    private String serviceType;

    @Column(name = "description")

    private String description;

    @Column(name = "base_price", nullable = false)

    private Double basePrice;

    @Column(name = "estimated_duration")

    private Integer estimatedDuration;

    @Column(name = "service_place")

    private String servicePlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mech_id")
    private Mechanic mechanic;

    public Service() {

    }

    public Long getServiceId() {

        return serviceId;

    }

    public void setServiceId(Long serviceId) {

        this.serviceId = serviceId;

    }

    public String getServiceType() {

        return serviceType;

    }

    public void setServiceType(String serviceType) {

        this.serviceType = serviceType;

    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public Double getBasePrice() {

        return basePrice;

    }

    public void setBasePrice(Double basePrice) {

        this.basePrice = basePrice;

    }

    public Integer getEstimatedDuration() {

        return estimatedDuration;

    }

    public void setEstimatedDuration(Integer estimatedDuration) {

        this.estimatedDuration = estimatedDuration;

    }

    public String getServicePlace() {

        return servicePlace;

    }

    public void setServicePlace(String servicePlace) {

        this.servicePlace = servicePlace;

    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

}