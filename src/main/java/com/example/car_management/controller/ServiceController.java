package com.example.car_management.controller;

import com.example.car_management.entity.Service;
import com.example.car_management.repository.ServiceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Services", description = "Vehicle service management APIs")

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository repository;

    public ServiceController(ServiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Service> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getById(@PathVariable Long id) {
        Service item = repository.findById(id).orElse(null);
        return item == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(item);
    }

    @PostMapping
    public Service create(@Valid @RequestBody Service item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> update(
            @PathVariable Long id,
            @Valid @RequestBody Service item) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        item.setServiceId(id);
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
    public ResponseEntity<Service> patch(
            @PathVariable Long id,
            @RequestBody Service changes) {

        Service existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (changes.getServiceType() != null) {
            existing.setServiceType(changes.getServiceType());
        }

        if (changes.getDescription() != null) {
            existing.setDescription(changes.getDescription());
        }

        if (changes.getBasePrice() != null) {
            existing.setBasePrice(changes.getBasePrice());
        }

        if (changes.getEstimatedDuration() != null) {
            existing.setEstimatedDuration(changes.getEstimatedDuration());
        }

        if (changes.getServicePlace() != null) {
            existing.setServicePlace(changes.getServicePlace());
        }

        return ResponseEntity.ok(repository.save(existing));
    }
}