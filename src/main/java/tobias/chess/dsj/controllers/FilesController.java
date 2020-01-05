package tobias.chess.dsj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tobias.chess.dsj.models.playerInformation.Player;
import tobias.chess.dsj.models.quota.ImportedTournament;
import tobias.chess.dsj.models.quota.QuotaTournament;
import tobias.chess.dsj.repositories.playerInformation.PlayerRepository;
import tobias.chess.dsj.repositories.playerInformation.PlayerTournamentRepository;
import tobias.chess.dsj.repositories.quota.QuotaTournamentRepository;
import tobias.chess.dsj.services.ImportedTournamentService;
import tobias.chess.dsj.utils.PlayerForCsv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FilesController {

    private PlayerRepository playerRepository;
    private PlayerTournamentRepository playerTournamentRepository;
    private ImportedTournamentService importedTournamentService;
    private QuotaTournamentRepository quotaTournamentRepository;

    @Autowired
    public FilesController(PlayerRepository playerRepository,
                           PlayerTournamentRepository playerTournamentRepository,
                           ImportedTournamentService importedTournamentService,
                           QuotaTournamentRepository quotaTournamentRepository) {
        this.playerRepository = playerRepository;
        this.playerTournamentRepository = playerTournamentRepository;
        this.importedTournamentService = importedTournamentService;
        this.quotaTournamentRepository = quotaTournamentRepository;
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
        CsvSchema schema = mapper.schemaFor(PlayerForCsv.class).withHeader().withColumnSeparator(';');
        List<Player> players = playerRepository.findAll();
        List<PlayerForCsv> playersForCsv = new ArrayList<>();
        for(Player player : players) {
            PlayerForCsv tempPlayer = new PlayerForCsv(player);
            playersForCsv.add(tempPlayer);
        }
        return mapper.writer(schema).writeValueAsString(playersForCsv);
    }

    @PostMapping("api/quota/uploadTournamentForImport")
    public ImportedTournament uploadTournamentForImport(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tournamentYear") Integer tournamentYear,
            @RequestParam("quotaTournamentId") Long quotaTournamentId) throws IOException {

        // Extract QuotaTournament from the provided Id.
        QuotaTournament quotaTournament;
        if (quotaTournamentRepository.findById(quotaTournamentId).isPresent())
            quotaTournament = quotaTournamentRepository.findById(quotaTournamentId).get();
        else
            throw new IOException("The QuotaTournament cannot be found!");

        // Verify the year is not empty and a valid year.
        if (!(tournamentYear > 0 && tournamentYear < 3000))
            throw new IOException("The Tournament-Year is not valid!");

        // Initialize ImportedTournament
        ImportedTournament importedTournament = importedTournamentService.createImportedTournament(tournamentYear, quotaTournament);

        // Return the updated ImportedTournament which has been filled with the data from the provided file.
        return importedTournamentService.importTournament(file.getInputStream(), importedTournament.getId());
    }

}
