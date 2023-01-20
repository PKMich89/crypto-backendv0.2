package pl.luypawlowski.chessbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ChessBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessBackendApplication.class, args);
	}

}
