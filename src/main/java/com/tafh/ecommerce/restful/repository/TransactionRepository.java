package com.tafh.ecommerce.restful.repository;

import com.tafh.ecommerce.restful.entity.Customer;
import com.tafh.ecommerce.restful.entity.Product;
import com.tafh.ecommerce.restful.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    Optional<Transaction> findFirstByProductAndCustomer(Product product, Customer customer);
}
