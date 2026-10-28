package com.example.car_management.controller;

import com.example.car_management.entity.Customer;
import com.example.car_management.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customers", description = "Customer management APIs")

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Customer item = repository.findById(id).orElse(null);
        return item == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(item);
    }

    @PostMapping
    public Customer create(@Valid @RequestBody Customer item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(
            @PathVariable Long id,
            @Valid @RequestBody Customer item) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        item.setCustomerId(id);
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
    public ResponseEntity<Customer> patch(
            @PathVariable Long id,
            @RequestBody Customer changes) {

        Customer existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (changes.getCustomerName() != null) {
            existing.setCustomerName(changes.getCustomerName());
        }

        if (changes.getCustomerEmail() != null) {
            existing.setCustomerEmail(changes.getCustomerEmail());
        }

        if (changes.getCustomerPhone() != null) {
            existing.setCustomerPhone(changes.getCustomerPhone());
        }

        if (changes.getCustomerAddress() != null) {
            existing.setCustomerAddress(changes.getCustomerAddress());
        }

        if (changes.getCustomerStatus() != null) {
            existing.setCustomerStatus(changes.getCustomerStatus());
        }

        return ResponseEntity.ok(repository.save(existing));
    }
}