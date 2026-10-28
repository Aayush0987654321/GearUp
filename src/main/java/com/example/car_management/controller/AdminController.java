package com.example.car_management.controller;

import com.example.car_management.entity.Admin;
import com.example.car_management.repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admins", description = "Admin management APIs")

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminRepository repository;

    public AdminController(AdminRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Admin> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getById(@PathVariable Long id) {
        Admin item = repository.findById(id).orElse(null);
        return item == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(item);
    }

    @PostMapping
    public Admin create(@Valid @RequestBody Admin item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(
            @PathVariable Long id,
            @Valid @RequestBody Admin item) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        item.setAdminId(id);
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
    public ResponseEntity<Admin> patch(
            @PathVariable Long id,
            @RequestBody Admin changes) {

        Admin existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (changes.getAdminName() != null) {
            existing.setAdminName(changes.getAdminName());
        }

        if (changes.getAdminEmail() != null) {
            existing.setAdminEmail(changes.getAdminEmail());
        }

        if (changes.getAdminRole() != null) {
            existing.setAdminRole(changes.getAdminRole());
        }

        if (changes.getStatus() != null) {
            existing.setStatus(changes.getStatus());
        }

        return ResponseEntity.ok(repository.save(existing));
    }
}