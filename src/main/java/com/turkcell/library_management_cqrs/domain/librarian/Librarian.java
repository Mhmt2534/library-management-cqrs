package com.turkcell.library_management_cqrs.domain.librarian;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.turkcell.library_management_cqrs.domain.borrowing.Borrowing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "librarians")
public class Librarian {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "identity_number", unique = true)
    private String identityNumber;

    @Column(name = "librarian_name")
    private String librarianName;

    @Column(name = "librarian_surname")
    private String librarianSurname;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber ;

    @Enumerated(EnumType.STRING)
    @Column(name = "shift")
    private Shift shift;

    @OneToMany(mappedBy = "borrowedByLibrarian")
    private Set<Borrowing> borrowingsGiven;

    @OneToMany(mappedBy = "returnedToLibrarian")
    private Set<Borrowing> borrowingsReceived;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getLibrarianName() {
        return librarianName;
    }

    public void setLibrarianName(String librarianName) {
        this.librarianName = librarianName;
    }

    public String getLibrarianSurname() {
        return librarianSurname;
    }

    public void setLibrarianSurname(String librarianSurname) {
        this.librarianSurname = librarianSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Set<Borrowing> getBorrowingsGiven() {
        return borrowingsGiven;
    }

    public void setBorrowingsGiven(Set<Borrowing> borrowingsGiven) {
        this.borrowingsGiven = borrowingsGiven;
    }

    public Set<Borrowing> getBorrowingsReceived() {
        return borrowingsReceived;
    }

    public void setBorrowingsReceived(Set<Borrowing> borrowingsReceived) {
        this.borrowingsReceived = borrowingsReceived;
    }


    

}
