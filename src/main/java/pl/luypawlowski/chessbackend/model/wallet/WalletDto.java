package pl.luypawlowski.chessbackend.model.wallet;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.luypawlowski.chessbackend.model.coin.CoinUserDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private Long id;
    private Long totalValue;
    private Long trueTotalValue;
    private List<CoinUserDto> allUserCoins;
}
