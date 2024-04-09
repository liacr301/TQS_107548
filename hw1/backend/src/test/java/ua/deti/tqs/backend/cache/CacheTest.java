package ua.deti.tqs.backend.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import ua.deti.tqs.backend.services.ExchangeRateClient;

import static org.mockito.BDDMockito.given;

import java.util.Map;

@SpringBootTest
class ExchangeRateClientTest {

    @Autowired
    private ExchangeRateClient exchangeRateClient;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        ExchangeRateClient.ExchangeRateResponse mockResponse = new ExchangeRateClient.ExchangeRateResponse();
        mockResponse.setConversion_rates(Map.of("USD", 1.1, "GBP", 0.8));
        given(restTemplate.getForObject("https://v6.exchangerate-api.com/v6/a439deea942651e54ad0ed50/latest/EUR", ExchangeRateClient.ExchangeRateResponse.class))
            .willReturn(mockResponse);
    }

    @Test
    void whenCalledTwice_thenSecondCallUsesCache() {
        Double firstCallRate = exchangeRateClient.getExchangeRate("USD");
        assertNotNull(firstCallRate);

        Double secondCallRate = exchangeRateClient.getExchangeRate("USD");
        assertNotNull(secondCallRate);

        assertEquals(firstCallRate, secondCallRate);
    }
}