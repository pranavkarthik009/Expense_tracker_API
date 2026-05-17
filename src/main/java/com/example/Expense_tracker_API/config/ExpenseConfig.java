package com.example.Expense_tracker_API.config;

import com.example.Expense_tracker_API.repository.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfig {

    @Bean
    CommandLineRunner commandLineRunner(ExpenseRepository repository) {
        return args -> {
            /*
             * Expenses exp1=new Expenses(
             * "restaurant",
             * 320,
             * "food"
             * );
             * 
             * Expenses exp2=new Expenses(
             * "train",
             * 500,
             * "Travel"
             * );
             */

            // repository.saveAll(List.of(exp1,exp2));
        };
    }
}
