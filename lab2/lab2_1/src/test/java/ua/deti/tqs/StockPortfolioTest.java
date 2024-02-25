package ua.deti.tqs;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.deti.tqs.IStockmarketService;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class StockPortfolioTest {

    @InjectMocks
    StockPortfolio stockPortfolio;

    @Mock
    IStockmarketService stock_service;

    @Test
    @DisplayName("Get Total Value of the Stock Portfolio ")
    public void TotalValueTest(){
        Stock stock_1 = new Stock("ANA", 4);
        Stock stock_2 = new Stock("MSFT", 7);

        stockPortfolio.addStock(stock_1);
        stockPortfolio.addStock(stock_2);

        when(stock_service.lookUpPrice("ANA")).thenReturn(3.0);
        when(stock_service.lookUpPrice("MSFT")).thenReturn(6.0);

        Assertions.assertEquals(54, stockPortfolio.totalValue(), "Total Value incorrect! Expected 54!");

    }

}
