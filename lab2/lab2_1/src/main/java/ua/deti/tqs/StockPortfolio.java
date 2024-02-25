package ua.deti.tqs;

import java.util.List;

public class StockPortfolio implements IStockmarketService{
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StockPortfolio(IStockmarketService stockmarket, List<Stock> stocks){
        this.stockmarket = stockmarket;
        this.stocks = stocks;
    }

    public void addStock(Stock stock){

    }

    public double totalValue(){
        double total_value = 0;
        return total_value;
    }

    public double lookUpPrice(String label){
        double price = 0;
        return price;
    }
}
