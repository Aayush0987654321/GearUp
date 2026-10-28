package com.example.car_management.controller;

import com.example.car_management.entity.Booking;
import com.example.car_management.repository.BookingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Bookings", description = "Service booking APIs")

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepository repository;

    public BookingController(BookingRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Booking> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable Long id) {
        Booking item = repository.findById(id).orElse(null);
        return item == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(item);
    }

    @PostMapping
    public Booking create(@Valid @RequestBody Booking item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(
            @PathVariable Long id,
            @Valid @RequestBody Booking item) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        item.setBookingId(id);
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
    public ResponseEntity<Booking> patch(
            @PathVariable Long id,
            @RequestBody Booking changes) {

        Booking existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (changes.getBookingDate() != null) {
            existing.setBookingDate(changes.getBookingDate());
        }

        if (changes.getBookingTime() != null) {
            existing.setBookingTime(changes.getBookingTime());
        }

        if (changes.getBookingStatus() != null) {
            existing.setBookingStatus(changes.getBookingStatus());
        }

        if (changes.getPaymentMethod() != null) {
            existing.setPaymentMethod(changes.getPaymentMethod());
        }

        if (changes.getTotalAmount() != null) {
            existing.setTotalAmount(changes.getTotalAmount());
        }

        if (changes.getRemarks() != null) {
            existing.setRemarks(changes.getRemarks());
        }

        return ResponseEntity.ok(repository.save(existing));
    }
}