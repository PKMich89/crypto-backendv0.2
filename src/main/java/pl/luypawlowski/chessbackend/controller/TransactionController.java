package pl.luypawlowski.chessbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.luypawlowski.chessbackend.model.crypto.TransactionDto;
import pl.luypawlowski.chessbackend.service.TransactionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Long saveTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.saveTransaction(transactionDto);
    }

    @GetMapping("/get-all")
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
