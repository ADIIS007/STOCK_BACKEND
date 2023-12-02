package com.stock.project.transaction;

import com.stock.project.constants.Payment;
import com.stock.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(User user);
    List<Transaction> findAllByUserAndFlow(User user, Payment flow);
}