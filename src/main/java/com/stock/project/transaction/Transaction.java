package com.stock.project.transaction;

import com.stock.project.auth.User;
import com.stock.project.constants.TransactionType;
import com.stock.project.trade.Trade;
import jakarta.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "trade_sequence",
            sequenceName = "trade_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trade_sequence"
    )
    private Long transactionId;
    @OneToOne(targetEntity = Trade.class,cascade = CascadeType.ALL)
    private Trade trade;
    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    private User user;
    private Long quantity;
    private Long unitCost;
    private TransactionType type;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Long unitCost) {
        this.unitCost = unitCost;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
