package com.tafh.ecommerce.restful.repository;

import com.tafh.ecommerce.restful.entity.Customer;
import com.tafh.ecommerce.restful.entity.Product;
import com.tafh.ecommerce.restful.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByProductAndCustomer(Product product, Customer customer);
}
