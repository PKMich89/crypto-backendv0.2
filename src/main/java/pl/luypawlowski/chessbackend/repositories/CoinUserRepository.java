package pl.luypawlowski.chessbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.luypawlowski.chessbackend.entities.CoinUser;
import pl.luypawlowski.chessbackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface CoinUserRepository extends JpaRepository<CoinUser, Long> {
    @Query("Select c from CoinUser c where c.owner = ?1")
    List<CoinUser> getAllUserCoins(User user);

    Optional<CoinUser> findByOwnerAndName(User user, String name);
}
