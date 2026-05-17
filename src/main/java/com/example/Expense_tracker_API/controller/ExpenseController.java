package com.example.Expense_tracker_API.controller;

import com.example.Expense_tracker_API.Expenses;
import com.example.Expense_tracker_API.User;
import com.example.Expense_tracker_API.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService=expenseService;
    }
    @GetMapping
    public List<Expenses> getExpenses(@AuthenticationPrincipal User user) {
        return expenseService.getExpenses(user);
    }

    @GetMapping(path = "/bytime/{startDate}/{endDate}")
    public List<Expenses> getExpensesByCustomDate(@AuthenticationPrincipal User user, @PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        return expenseService.getExpenseByTimestamp(user, startDate, endDate);

    }

    @PostMapping(path = "/register")
    public void registerNewExpense(@AuthenticationPrincipal User user, @RequestBody Expenses exp){
        expenseService.registerNewExpense(exp, user);
    }

    @GetMapping("/{expid}")
    public Expenses getExpenseById(@AuthenticationPrincipal User user, @PathVariable Long expid){
        return expenseService.getExpensesById(expid, user);
    }

    @DeleteMapping("/deletebyid/{expid}")
    public void deleteExpenseById(@AuthenticationPrincipal User user, @PathVariable Long expid){
        expenseService.deleteExpenseById(expid, user);
    }

    @PutMapping("/update/{expid}")
    public void updateExpenseById(@AuthenticationPrincipal User user, @PathVariable Long expid,@RequestParam(required = true) String expense_name){
        expenseService.updateExpenseById(expid,expense_name, user);
    }

    @GetMapping("/gettotalamountbycategory/")
    public  int getTotalAmountByCategory(@AuthenticationPrincipal User user, @RequestParam(required = true
    ) String category){
        return expenseService.getTotalAmountByCategory(user, category);
    }
}
