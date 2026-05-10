package com.turkcell.library_management_cqrs.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.library_management_cqrs.domain.student.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByIdentityNumber(String identityNumber);
    Optional<Student> findByPhoneNumber(String phoneNumber);
}