package pl.luypawlowski.chessbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.luypawlowski.chessbackend.entities.Transaction;
import pl.luypawlowski.chessbackend.entities.User;
import pl.luypawlowski.chessbackend.model.crypto.TransactionDto;
import pl.luypawlowski.chessbackend.model.user.UserDto;
import pl.luypawlowski.chessbackend.repositories.TransactionsRepository;
import pl.luypawlowski.chessbackend.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<TransactionDto> getAllTransactionsByUser(User user){
        transactionsRepository.getAllUserTransactions(user);
        return new ArrayList<>();
    }

    @Transactional
    public Long saveTransaction(TransactionDto transactionDto){
        Transaction transaction = transactionDto.toDomain();
        Long userId = transactionDto.getOwner().getId();
        User user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        user.addTransaction(transaction);
        Transaction save = transactionsRepository.save(transaction);

        return save.getId();
    }

    public List<TransactionDto> getAllTransactions() {
        return transactionsRepository.findAll().stream().map(TransactionDto::fromDomain).collect(Collectors.toList());
    }

}
