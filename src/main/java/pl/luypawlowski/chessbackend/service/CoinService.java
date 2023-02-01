package pl.luypawlowski.chessbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import pl.luypawlowski.chessbackend.model.coinapi.ExchangeRateModel;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CoinService {
@Autowired
    ObjectMapper objectMapper;
    WebClient client = WebClient.builder()
            .baseUrl("https://rest.coinapi.io/v1")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-CoinAPI-Key", "679F3404-CB47-4970-ABCF-1638847F0663")
            .build();
    private Map<String, Double> coinsPrices = new HashMap<>();

    @PostConstruct
    private void initializeData() {
        getCurrentRate("BTC");
        getCurrentRate("XRP");
        getCurrentRate("ETH");
        getCurrentRate("ATOM");
        getCurrentRate("USDT");
    }
//
    private void getCurrentRate(String currency) {
        ExchangeRateModel rate = client.get()
                .uri("https://rest.coinapi.io/v1/exchangerate/"+currency+"/USD")
                .retrieve()
                .bodyToMono(ExchangeRateModel.class)
                .block();
        coinsPrices.put(currency,rate.getRate());
    }

    public Double getPriceOfCoin(String coinName) {
        getCurrentRate(coinName);
        return coinsPrices.get(coinName);
    }
}
