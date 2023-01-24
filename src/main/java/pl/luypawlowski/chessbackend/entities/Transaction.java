package pl.luypawlowski.chessbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Table(name = "transactions")
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String comment;
    private LocalDate date;
    private String coin;
    private Double amount;
    private Double value;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Transaction(String type, String comment, LocalDate date, String coin, Double amount, Double value) {
        this.type = type;
        this.comment = comment;
        this.date = date;
        this.coin = coin;
        this.amount = amount;
        this.value = value;
    }
}
