package com.stock.project.trade;

import com.stock.project.constants.StockTradeActions;
import com.stock.project.stock.Stock;
import com.stock.project.transaction.Transaction;
import jakarta.persistence.*;

@Entity
@Table(name = "Trade")
public class Trade {
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
    private Long tradeId;
    @ManyToOne
    @JoinColumn(name = "stockId")
    private Stock stock;
    @OneToOne
    private Transaction transaction;
    private StockTradeActions tradeAction;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public StockTradeActions getTradeAction() {
        return tradeAction;
    }

    public void setTradeAction(StockTradeActions tradeAction) {
        this.tradeAction = tradeAction;
    }
}
