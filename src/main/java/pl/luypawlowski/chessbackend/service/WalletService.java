package pl.luypawlowski.chessbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.luypawlowski.chessbackend.entities.CoinUser;
import pl.luypawlowski.chessbackend.entities.Transaction;
import pl.luypawlowski.chessbackend.entities.User;
import pl.luypawlowski.chessbackend.model.coin.CoinUserDto;
import pl.luypawlowski.chessbackend.model.crypto.TransactionDto;
import pl.luypawlowski.chessbackend.model.wallet.WalletDto;
import pl.luypawlowski.chessbackend.repositories.CoinUserRepository;
import pl.luypawlowski.chessbackend.repositories.TransactionsRepository;
import pl.luypawlowski.chessbackend.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WalletService {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CoinUserRepository coinUserRepository;
    @Autowired
    private CoinService coinService;

    public WalletDto getUserWallet(User user) {
        List<CoinUserDto> userCoins = coinUserRepository.getAllUserCoins(user).stream().map(CoinUserDto::fromDomain).toList();
        userCoins.forEach(coinUserDto -> coinUserDto.setCurrentPrice(coinService.getPriceOfCoin(coinUserDto.getName())));

        for (CoinUserDto c : userCoins) {
            List<Transaction> allUserTransactionsOfCoin = transactionsRepository.getAllUserTransactionsForCoin(user, c.getName());
            double sum = allUserTransactionsOfCoin.stream().mapToDouble(transaction -> transaction.getAmount() * transaction.getPrice()).sum();
            //todo uzupełnic jaki jest zwrot na danum coinie w % i ogólnie
        }


        return WalletDto.of(user.getId(), userCoins);
    }

}
