package com.example.transactions;

import com.example.transactions.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TransactionRunner implements CommandLineRunner {

    private final TransactionService transactionService;

    public TransactionRunner(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void run(String... args) {
        Thread thread1 = new Thread(() -> transactionService.addProductAndSale());
        Thread thread2 = new Thread(() -> {
            transactionService.fetchProducts().forEach(product -> {
                System.out.println("Product: " + product.getName());
            });
        });

        thread1.start();
        thread2.start();
    }
}