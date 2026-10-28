package com.example.car_management.repository;

import com.example.car_management.entity.Membership;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

}