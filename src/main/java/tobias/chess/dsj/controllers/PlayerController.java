package tobias.chess.dsj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tobias.chess.dsj.models.Player;
import tobias.chess.dsj.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("players/{id}/updateAndGet")
    public List<Player> updateAndGet(@PathVariable long id) {

        return playerRepository.findAll();
    }
}
