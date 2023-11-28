package com.stock.project.dto;

public class TransactionData {
    String stock; //short name of stock (or) Use work/Hard Work
    boolean isIncome; //this is representing credit or debit
    int money; //cost of each stock
    int quantity; //number of units purchased or sold
    int total;  //money * Quantity + Tax

    public TransactionData(String stock, boolean isIncome, int money, int quantity, int total) {
        this.stock = stock;
        this.isIncome = isIncome;
        this.money = money;
        this.quantity = quantity;
        this.total = total;
    }
}
