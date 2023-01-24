package pl.luypawlowski.chessbackend.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserActiveToken {
    private String activeToken;
    private LocalDateTime validUtil;
}
