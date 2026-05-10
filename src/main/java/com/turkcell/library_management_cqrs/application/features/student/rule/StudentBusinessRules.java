package com.turkcell.library_management_cqrs.application.features.student.rule;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.persistence.repository.StudentRepository;

@Component
public class StudentBusinessRules {

    private final StudentRepository studentRepository;

    public StudentBusinessRules(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void studentWithSameIdentityNumberMustNotExist(String identityNumber) {
        var existingStudent = studentRepository.findByIdentityNumber(identityNumber);
        if (existingStudent.isPresent()) {
            throw new RuntimeException("Student with same identity number already exists");
        }
    }

    public void studentWithSameIdentityNumberMustNotExist(String identityNumber, UUID currentId) {
        var existingStudent = studentRepository.findByIdentityNumber(identityNumber);
        if (existingStudent.isPresent() && !existingStudent.get().getId().equals(currentId)) {
            throw new RuntimeException("Student with same identity number already exists");
        }
    }

    public void studentWithSamePhoneNumberMustNotExist(String phoneNumber) {
        var existingStudent = studentRepository.findByPhoneNumber(phoneNumber);
        if (existingStudent.isPresent()) {
            throw new RuntimeException("Student with same phone number already exists");
        }
    }

    public void studentWithSamePhoneNumberMustNotExist(String phoneNumber, UUID currentId) {
        var existingStudent = studentRepository.findByPhoneNumber(phoneNumber);
        if (existingStudent.isPresent() && !existingStudent.get().getId().equals(currentId)) {
            throw new RuntimeException("Student with same phone number already exists");
        }
    }
}