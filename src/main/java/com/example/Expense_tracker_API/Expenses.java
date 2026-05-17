package com.example.Expense_tracker_API;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Expenses")
@Table(name = "expenses")
public class Expenses {

    @Id
    @SequenceGenerator(name = "expense_sequence", sequenceName = "expense_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_sequence")
    @Column(name = "expid", updatable = false)
    private Long expid;

    @Column(name = "expense_name", nullable = false, columnDefinition = "TEXT")
    private String expense_name;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "category", nullable = false, columnDefinition = "TEXT")
    private String category;

    @Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expenses() {
    }

    public Expenses(String expense_name, int amount, String category, LocalDate timestamp, User user) {
        this.expense_name = expense_name;
        this.amount = amount;
        this.category = category;
        this.user = user;
        if (timestamp != null && timestamp != LocalDate.now()) {
            this.timestamp = timestamp;
        } else {
            this.timestamp = LocalDate.now();
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getExpid() {
        return expid;
    }

    public void setExpid(Long expid) {
        this.expid = expid;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocalDateTime(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getLocalDateTime() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "expid=" + expid +
                ", expense_name='" + expense_name + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                '}';
    }
}
