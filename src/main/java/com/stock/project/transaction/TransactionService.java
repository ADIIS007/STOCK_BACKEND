package com.stock.project.transaction;

import com.stock.project.constants.Payment;
import com.stock.project.constants.TransactionType;
import com.stock.project.trade.Trade;
import com.stock.project.user.User;
import com.stock.project.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public void addJoiningBonus(User user) {
        try {
            Transaction transaction = new Transaction(null, user, 1L, 1500.0, TransactionType.HARD_WORK, Payment.IN);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addBonus(User user) {
        try {
            Transaction transaction = new Transaction(null, user, 1L, 500.0, TransactionType.HARD_WORK, Payment.IN);
            transactionRepository.save(transaction);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void addWork(User user) {
        try {
            Transaction transaction = new Transaction(null, user, 1L, 100.0, TransactionType.DAILY_WORK, Payment.IN);
            transactionRepository.save(transaction);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Transaction> list(User user) {
        return transactionRepository.findAllByUser(user);
    }

    public Long netIn(User user){
        List<Transaction> transactions = transactionRepository.findAllByUserAndFlow(user,Payment.IN);
        long l = 0L;
        if (transactions==null) return l;
        for(Transaction x:transactions){
            l+=(x.getQuantity()*x.getUnitCost());
        }
        return l;
    }
    public Long netOut(User user){
        List<Transaction> transactions = transactionRepository.findAllByUserAndFlow(user,Payment.OUT);
        long l = 0L;
        if (transactions==null) return l;
        for(Transaction x:transactions){
            l+=(x.getQuantity()*x.getUnitCost());
        }
        return l;
    }

    public boolean deductMoney(User user,Double amount){
        if(user.getBalance()>=amount){
            user.setBalance(user.getBalance()-amount);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void buyStock(String code, Integer quantity, double price, Trade trade,User user){
        deductMoney(user,quantity*price);
        Transaction transaction = new Transaction(trade,user, (long) quantity,price,TransactionType.STOCK,Payment.OUT);
        transactionRepository.save(transaction);
    }
}
