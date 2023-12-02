package com.stock.project.trade;

import com.stock.project.constants.Stock;
import com.stock.project.constants.StockTradeActions;
import com.stock.project.transaction.TransactionService;
import com.stock.project.uplink_util.PriceAPI;
import com.stock.project.user.User;
import com.upstox.api.GetMarketQuoteLastTradedPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {
    private final PriceAPI priceAPI;
    private final TradeRepository tradeRepository;
    private final TransactionService transactionService;

    @Autowired
    public TradeService(PriceAPI priceAPI, TradeRepository tradeRepository,TransactionService transactionService) {
        this.priceAPI = priceAPI;
        this.tradeRepository = tradeRepository;
        this.transactionService = transactionService;
    }

    public GetMarketQuoteLastTradedPriceResponse testingApi(String org) {
        return priceAPI.ltp(org);
    }

    public boolean addTrade(String str, int quantity, double price, User user) {
        Stock stock = getStock(str);
        Trade trade = new Trade(stock, StockTradeActions.STOCK_BUY,quantity,user);
        tradeRepository.save(trade);
        transactionService.buyStock(str,quantity,price,trade,user);
        return true;
    }
    public Stock getStock(String str){
        String s = str.replace('|', '_');
        return Stock.valueOf(s);
    }

    public long checkQuantityAvailable(User user, String code) {
        Stock stock = getStock(code);
        List<Trade> data = tradeRepository.findAllByUserAndStock(user,stock);
        Long res = 0L;
        for(Trade t:data){
            if(t.getTradeAction()==StockTradeActions.STOCK_BUY) res+=t.getUnits();
            if(t.getTradeAction()==StockTradeActions.STOCK_SELL) res-=t.getUnits();
        }
        return res;
    }
}
