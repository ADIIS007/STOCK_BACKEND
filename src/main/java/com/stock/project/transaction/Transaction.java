package com.stock.project.transaction;

import com.stock.project.constants.Payment;
import com.stock.project.user.User;
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
    @OneToOne(targetEntity = Trade.class, cascade = CascadeType.ALL)
    private Trade trade;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @Column(nullable = false)
    private User user;
    @Column(nullable = false)
    private Long quantity;
    @Column(nullable = false)
    private Double unitCost;
    @Column(nullable = false)
    private TransactionType type;
    @Column(nullable = false)
    private Payment flow;

    public Transaction() {
    }

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

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Payment getFlow() {
        return flow;
    }

    public void setFlow(Payment flow) {
        this.flow = flow;
    }

    public Transaction(Trade trade, User user, Long quantity, Double unitCost, TransactionType type, Payment flow) {
        this.trade = trade;
        this.user = user;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.type = type;
        this.flow = flow;
    }
}
