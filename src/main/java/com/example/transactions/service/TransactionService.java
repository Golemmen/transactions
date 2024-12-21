package com.example.transactions.service;

import com.example.transactions.entity.Product;
import com.example.transactions.entity.Sale;
import com.example.transactions.repository.ProductRepository;
import com.example.transactions.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public TransactionService(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Transactional
    public void addProductAndSale() {
        Product product = new Product();
        product.setName("Sample Product");
        product.setPrice(BigDecimal.valueOf(100.00));
        productRepository.save(product);

        Sale sale = new Sale();
        sale.setProductId(product.getId());
        sale.setQuantity(10);
        saleRepository.save(sale);
    }

    @Transactional
    public List<Product> fetchProducts() {
        return productRepository.findAll();
    }
}