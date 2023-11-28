package com.stock.project.dto;

public class StockMarket {
    String stock; //short name of stock
    String stockName; //full name of stock
    String url; //URL to demonstrate stock data
    StockMarket(String stock,String stockName,String url){
        this.stock=stock;
        this.stockName=stockName;
        this.url=url;
    }
}
