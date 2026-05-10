package com.turkcell.library_management_cqrs.domain.penalty;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.turkcell.library_management_cqrs.domain.borrowing.Borrowing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "penalties")
public class Penalty {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "borrowing_id", nullable = false, unique = true)
    private Borrowing borrowing;

    @Column(name = "amount_try", nullable = false)
    private BigDecimal amountTry;

    @Column(name = "penalty_date", nullable = false)
    private Instant penaltyDate;

    @Column(name = "paid_at")
    private Instant paidAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "description")
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Borrowing borrowing) {
        this.borrowing = borrowing;
    }

    public BigDecimal getAmountTry() {
        return amountTry;
    }

    public void setAmountTry(BigDecimal amountTry) {
        this.amountTry = amountTry;
    }

    public Instant getPenaltyDate() {
        return penaltyDate;
    }

    public void setPenaltyDate(Instant penaltyDate) {
        this.penaltyDate = penaltyDate;
    }

    public Instant getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Instant paidAt) {
        this.paidAt = paidAt;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    

}