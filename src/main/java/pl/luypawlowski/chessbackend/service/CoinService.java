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
    coinsPrices.put("BTC", 22971.97);
    coinsPrices.put("XRP", 4.90);
    coinsPrices.put("Luna", 1.007);
    coinsPrices.put("ETH", 2451.97);
    coinsPrices.put("CARDANO", 1.9);
    }

    public Double getPriceOfCoin(String coinName) {
        return coinsPrices.get(coinName);
    }
}
