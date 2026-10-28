package com.example.car_management.controller;

import com.example.car_management.entity.Mechanic;
import com.example.car_management.repository.MechanicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Mechanics", description = "Mechanics management APIs")

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicRepository repository;

    public MechanicController(MechanicRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Mechanic> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mechanic> getById(@PathVariable Long id) {
        Mechanic item = repository.findById(id).orElse(null);
        return item == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(item);
    }

    @PostMapping
    public Mechanic create(@Valid @RequestBody Mechanic item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mechanic> update(
            @PathVariable Long id,
            @Valid @RequestBody Mechanic item) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        item.setMechId(id);
        return ResponseEntity.ok(repository.save(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Mechanic> patch(
            @PathVariable Long id,
            @RequestBody Mechanic changes) {

        Mechanic existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (changes.getMechName() != null) {
            existing.setMechName(changes.getMechName());
        }

        if (changes.getMechPhone() != null) {
            existing.setMechPhone(changes.getMechPhone());
        }

        if (changes.getMechEmail() != null) {
            existing.setMechEmail(changes.getMechEmail());
        }

        if (changes.getSkillLevel() != null) {
            existing.setSkillLevel(changes.getSkillLevel());
        }

        if (changes.getSpecialization() != null) {
            existing.setSpecialization(changes.getSpecialization());
        }

        if (changes.getExperienceYears() != null) {
            existing.setExperienceYears(changes.getExperienceYears());
        }

        if (changes.getAvailabilityStatus() != null) {
            existing.setAvailabilityStatus(changes.getAvailabilityStatus());
        }

        return ResponseEntity.ok(repository.save(existing));
    }
}