package com.example.car_management.controller;

import com.example.car_management.entity.Membership;
import com.example.car_management.repository.MembershipRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Memberships", description = "Memeberships management APIs")

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    private final MembershipRepository repository;

    public MembershipController(MembershipRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Membership> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membership> getById(@PathVariable Long id) {
        Membership item = repository.findById(id).orElse(null);
        return item == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(item);
    }

    @PostMapping
    public Membership create(@Valid @RequestBody Membership item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membership> update(
            @PathVariable Long id,
            @Valid @RequestBody Membership item) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        item.setMembershipId(id);
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
    public ResponseEntity<Membership> patch(
            @PathVariable Long id,
            @RequestBody Membership changes) {

        Membership existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (changes.getMembershipType() != null) {
            existing.setMembershipType(changes.getMembershipType());
        }

        if (changes.getMembershipFee() != null) {
            existing.setMembershipFee(changes.getMembershipFee());
        }

        if (changes.getDiscountPercentage() != null) {
            existing.setDiscountPercentage(changes.getDiscountPercentage());
        }

        if (changes.getBenefits() != null) {
            existing.setBenefits(changes.getBenefits());
        }

        if (changes.getStatus() != null) {
            existing.setStatus(changes.getStatus());
        }

        return ResponseEntity.ok(repository.save(existing));
    }
}