package pl.luypawlowski.chessbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "coin_user")
@Setter
@Getter
@Entity
public class CoinUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double value;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public CoinUser(String name, Double value, Double amount, User owner) {
        this.name = name;
        this.value = value;
        this.amount = amount;
        this.owner = owner;
    }
}
