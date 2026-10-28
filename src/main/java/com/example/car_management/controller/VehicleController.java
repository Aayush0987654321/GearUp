package com.example.car_management.controller;

import com.example.car_management.entity.Vehicle;
import com.example.car_management.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Vehicles", description = "Vehicle management APIs")

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleRepository repository;

    public VehicleController(VehicleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable Long id) {
        Vehicle item = repository.findById(id).orElse(null);
        return item == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(item);
    }

    @PostMapping
    public Vehicle create(@Valid @RequestBody Vehicle item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(
            @PathVariable Long id,
            @Valid @RequestBody Vehicle item) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        item.setVehicleId(id);
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
    public ResponseEntity<Vehicle> patch(
            @PathVariable Long id,
            @RequestBody Vehicle changes) {

        Vehicle existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (changes.getVehicleName() != null) {
            existing.setVehicleName(changes.getVehicleName());
        }

        if (changes.getBrand() != null) {
            existing.setBrand(changes.getBrand());
        }

        if (changes.getModel() != null) {
            existing.setModel(changes.getModel());
        }

        if (changes.getRegistrationNumber() != null) {
            existing.setRegistrationNumber(changes.getRegistrationNumber());
        }

        if (changes.getVehicleType() != null) {
            existing.setVehicleType(changes.getVehicleType());
        }

        if (changes.getFuelType() != null) {
            existing.setFuelType(changes.getFuelType());
        }

        return ResponseEntity.ok(repository.save(existing));
    }
}