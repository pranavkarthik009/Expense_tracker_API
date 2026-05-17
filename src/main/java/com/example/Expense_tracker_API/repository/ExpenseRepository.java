package com.example.Expense_tracker_API.repository;

import com.example.Expense_tracker_API.Expenses;
import com.example.Expense_tracker_API.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses,Long> {

    List<Expenses> findByUser(User user);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expenses e WHERE e.user = ?1 AND e.category = ?2")
    int getTotalAmountByCategoryAndUser(User user, String Category);

    @Query("SELECT e FROM Expenses e WHERE e.user = ?1 AND e.timestamp BETWEEN ?2 AND ?3")
    List<Expenses> getExpenseByTimestampAndUser(User user, LocalDate startDate, LocalDate endDate);
}
