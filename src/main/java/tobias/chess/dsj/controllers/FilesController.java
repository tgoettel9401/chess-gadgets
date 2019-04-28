package tobias.chess.dsj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tobias.chess.dsj.models.Player;
import tobias.chess.dsj.repositories.PlayerRepository;
import tobias.chess.dsj.repositories.PlayerTournamentRepository;
import tobias.chess.dsj.utils.CsvUtils;
import tobias.chess.dsj.utils.PlayerForCsv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FilesController {

    private static final Logger logger = LoggerFactory.getLogger(FilesController.class);
    private PlayerRepository playerRepository;
    private PlayerTournamentRepository playerTournamentRepository;

    @Autowired
    public FilesController(PlayerRepository playerRepository, PlayerTournamentRepository playerTournamentRepository) {
        this.playerRepository = playerRepository;
        this.playerTournamentRepository = playerTournamentRepository;
    }

    @PostMapping("api/uploadFile")
    public List<Player> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        playerTournamentRepository.deleteAll();
        playerRepository.deleteAll();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(PlayerForCsv.class).withHeader().withColumnSeparator(';');
        ObjectReader reader = mapper.readerFor(PlayerForCsv.class).with(schema);
        List<PlayerForCsv> playersForCsv = reader.<PlayerForCsv>readValues(file.getInputStream()).readAll();
        List<Player> players = new ArrayList<>();
        for (PlayerForCsv playerForCsv : playersForCsv) {
            Player player = playerForCsv.returnAsPlayer();
            players.add(player);
        }
        return playerRepository.saveAll(players);
    }

    @GetMapping("api/downloadFile")
    public String toCsv() throws JsonProcessingException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(PlayerForCsv.class).withHeader();
        List<Player> players = playerRepository.findAll();
        List<PlayerForCsv> playersForCsv = new ArrayList<>();
        for(Player player : players) {
            PlayerForCsv tempPlayer = new PlayerForCsv(player);
            playersForCsv.add(tempPlayer);
        }
        return mapper.writer(schema).writeValueAsString(playersForCsv);
    }

}
