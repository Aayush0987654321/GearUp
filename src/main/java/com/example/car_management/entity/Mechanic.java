package com.example.car_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mechanic")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mechId;

    private String mechName;
    private String mechPhone;
    private String mechEmail;
    private String skillLevel;
    private String specialization;
    private Integer experienceYears;
    private String availabilityStatus;

    public Mechanic() {
    }

    public Mechanic(String mechName, String mechPhone, String mechEmail,
                    String skillLevel, String specialization,
                    Integer experienceYears, String availabilityStatus) {

        this.mechName = mechName;
        this.mechPhone = mechPhone;
        this.mechEmail = mechEmail;
        this.skillLevel = skillLevel;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.availabilityStatus = availabilityStatus;
    }

    public Long getMechId() {
        return mechId;
    }

    public void setMechId(Long mechId) {
        this.mechId = mechId;
    }

    public String getMechName() {
        return mechName;
    }

    public void setMechName(String mechName) {
        this.mechName = mechName;
    }

    public String getMechPhone() {
        return mechPhone;
    }

    public void setMechPhone(String mechPhone) {
        this.mechPhone = mechPhone;
    }

    public String getMechEmail() {
        return mechEmail;
    }

    public void setMechEmail(String mechEmail) {
        this.mechEmail = mechEmail;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}