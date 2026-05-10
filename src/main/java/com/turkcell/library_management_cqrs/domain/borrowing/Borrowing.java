package com.turkcell.library_management_cqrs.domain.borrowing;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.turkcell.library_management_cqrs.domain.bookcopy.BookCopy;
import com.turkcell.library_management_cqrs.domain.librarian.Librarian;
import com.turkcell.library_management_cqrs.domain.penalty.Penalty;
import com.turkcell.library_management_cqrs.domain.student.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowings")
public class Borrowing {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "borrowed_by_librarian_id")
    private Librarian borrowedByLibrarian;

    @ManyToOne
    @JoinColumn(name = "returned_to_librarian_id", nullable = true)
    private Librarian returnedToLibrarian;

    @Column(name = "borrowed_at")
    private Instant borrowedAt;

    @Column(name = "due_at", nullable = true)
    private Instant dueAt;

    @Column(name = "returned_at", nullable = true)
    private Instant returnedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BorrowStatus borrowStatus;


    @OneToOne(mappedBy = "borrowing")
    private Penalty penalty;


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public BookCopy getBookCopy() {
        return bookCopy;
    }


    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }


    public Student getStudent() {
        return student;
    }


    public void setStudent(Student student) {
        this.student = student;
    }


    public Librarian getBorrowedByLibrarian() {
        return borrowedByLibrarian;
    }


    public void setBorrowedByLibrarian(Librarian borrowedByLibrarian) {
        this.borrowedByLibrarian = borrowedByLibrarian;
    }


    public Librarian getReturnedToLibrarian() {
        return returnedToLibrarian;
    }


    public void setReturnedToLibrarian(Librarian returnedToLibrarian) {
        this.returnedToLibrarian = returnedToLibrarian;
    }


    public Instant getBorrowedAt() {
        return borrowedAt;
    }


    public void setBorrowedAt(Instant borrowedAt) {
        this.borrowedAt = borrowedAt;
    }


    public Instant getDueAt() {
        return dueAt;
    }


    public void setDueAt(Instant dueAt) {
        this.dueAt = dueAt;
    }


    public Instant getReturnedAt() {
        return returnedAt;
    }


    public void setReturnedAt(Instant returnedAt) {
        this.returnedAt = returnedAt;
    }


    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }


    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }


    public Penalty getPenalty() {
        return penalty;
    }


    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    
}
