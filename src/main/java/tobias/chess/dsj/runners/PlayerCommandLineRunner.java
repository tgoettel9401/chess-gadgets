package tobias.chess.dsj.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tobias.chess.dsj.models.Player;
import tobias.chess.dsj.repositories.PlayerRepository;

@Component
public class PlayerCommandLineRunner implements CommandLineRunner {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerCommandLineRunner(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... strings) {
        this.addPlayers();
    }

    private void addPlayers() {
        Player player = new Player("Jana", "Schneider", 2002, "Spvgg 1946 e.V. Stetten");
        playerRepository.save(player);
    }
}
