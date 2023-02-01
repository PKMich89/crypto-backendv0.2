package pl.luypawlowski.chessbackend.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CoinService {
    private Map<String, Double> coinsPrices = new HashMap<>();

    @PostConstruct
    private void initializeData() {
        coinsPrices.put("BTC", 1000.00);
        coinsPrices.put("XRP", 2.0);
        coinsPrices.put("Luna", 1.007);
        coinsPrices.put("ETH", 2000.00);
        coinsPrices.put("CARDANO", 1.9);
    }

    public Double getPriceOfCoin(String coinName) {
        return coinsPrices.get(coinName);
    }
}
