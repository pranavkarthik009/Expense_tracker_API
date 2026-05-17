package com.example.Expense_tracker_API.service;

import com.example.Expense_tracker_API.Expenses;
import com.example.Expense_tracker_API.User;
import com.example.Expense_tracker_API.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }
    public List<Expenses> getExpenses(User user){
        return expenseRepository.findByUser(user);
    }

    public int getTotalAmountByCategory(User user, String Category){
        return expenseRepository.getTotalAmountByCategoryAndUser(user, Category);
    }

    public void registerNewExpense(Expenses exp, User user){
       exp.setUser(user);
        expenseRepository.save(exp);
    }

    public Expenses getExpensesById(Long expid, User user){
        Optional<Expenses> op=expenseRepository.findById(expid);
        if(op.isEmpty() || !op.get().getUser().getId().equals(user.getId())) 
            throw new IllegalStateException("No Expense found by id for this user");
        return op.get();
    }

    public List<Expenses> getExpenseByTimestamp(User user, LocalDate startDate,LocalDate endDate){
        return expenseRepository.getExpenseByTimestampAndUser(user, startDate, endDate);
    }

    public void deleteExpenseById(Long expid, User user){
        Optional<Expenses> op = expenseRepository.findById(expid);
        if(op.isEmpty() || !op.get().getUser().getId().equals(user.getId()))
            throw new IllegalStateException(expid + " Id doesn't exist for this user");
        expenseRepository.deleteById(expid);
    }

    @Transactional
    public void updateExpenseById(Long expid,String expense_name, User user){
        Optional<Expenses> op = expenseRepository.findById(expid);
        if(op.isEmpty() || !op.get().getUser().getId().equals(user.getId()))
            throw  new IllegalStateException(expid + " ID doesn't exist for this user");
        Expenses exp=op.get();
        exp.setExpense_name(expense_name);
    }

}
