package ua.deti.tqs.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import ua.deti.tqs.backend.services.ExchangeRateClient;

@CrossOrigin
@RestController
@RequestMapping("/api/exchange")
public class ExhangedRateClientController {
    @Autowired
    private ExchangeRateClient exchangeRateClient;

    @GetMapping("/")
    public Double convert(@RequestParam Double amount, @RequestParam String currency) {
        Double rate = exchangeRateClient.getExchangeRate(currency);
        return amount * rate;
    }
}
