package ua.deti.tqs.backend.services;

import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

@Component
public class ExchangeRateClient {
    private RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "a439deea942651e54ad0ed50";
    private final String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/EUR";
    private final Logger logger = LoggerFactory.getLogger(ExchangeRateClient.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Cacheable(value = "exchangeRates", key = "#toCurrency")
    public Double getExchangeRate(String toCurrency) {
        String url = baseUrl;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            logger.info("Response from the external API: {}", jsonResponse);
        } catch (Exception e) {
            logger.error("Error while converting the response to JSON", e);
        }
        return response.getConversion_rates().get(toCurrency); 
    }

    public static class ExchangeRateResponse {
        private String result;
        private Map<String, Double> conversion_rates;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public Map<String, Double> getConversion_rates() {
            return conversion_rates;
        }

        public void setConversion_rates(Map<String, Double> conversion_rates) {
            this.conversion_rates = conversion_rates;
        }
    }
}
