package ua.deti.tqs;

import java.util.ArrayList;
import java.util.List;

public class StockPortfolio{
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StockPortfolio(IStockmarketService stockmarket, List<Stock> stocks){
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock){
        this.stocks.add(stock);
    }

    public double totalValue(){
        double total_value = 0.0;
        for(Stock stock: stocks){
            total_value += stockmarket.lookUpPrice(stock.getLabel())*stock.getQuantity();
        }
        return total_value;
    }

}
