package com.stock.project.trade;

import com.stock.project.constants.Stock;
import com.stock.project.constants.StockTradeActions;
import com.stock.project.user.User;
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
    private Stock stock;
    private Integer units;
    private StockTradeActions tradeAction;
    @ManyToOne
    private User user;

    public Trade() {

    }

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

    public StockTradeActions getTradeAction() {
        return tradeAction;
    }

    public void setTradeAction(StockTradeActions tradeAction) {
        this.tradeAction = tradeAction;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trade(Stock stock, StockTradeActions tradeAction, Integer units, User user) {
        this.stock = stock;
        this.tradeAction = tradeAction;
        this.units = units;
        this.user = user;
    }
}
