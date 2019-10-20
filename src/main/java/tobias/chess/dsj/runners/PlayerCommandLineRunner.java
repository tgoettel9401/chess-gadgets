package tobias.chess.dsj.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tobias.chess.dsj.models.playerInformation.Player;
import tobias.chess.dsj.models.quota.ImportedTournament;
import tobias.chess.dsj.models.quota.QuotaTournament;
import tobias.chess.dsj.models.quota.RegionalGroup;
import tobias.chess.dsj.models.quota.State;
import tobias.chess.dsj.repositories.playerInformation.PlayerRepository;
import tobias.chess.dsj.repositories.quota.ImportedTournamentRepository;
import tobias.chess.dsj.repositories.quota.QuotaTournamentRepository;
import tobias.chess.dsj.repositories.quota.RegionalGroupRepository;
import tobias.chess.dsj.repositories.quota.StateRepository;
import tobias.chess.dsj.services.ImportedTournamentService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerCommandLineRunner implements CommandLineRunner {

    private PlayerRepository playerRepository;
    private QuotaTournamentRepository quotaTournamentRepository;
    private ImportedTournamentRepository importedTournamentRepository;
    private RegionalGroupRepository regionalGroupRepository;
    private StateRepository stateRepository;
    private ImportedTournamentService importedTournamentService;

    @Autowired
    public PlayerCommandLineRunner(PlayerRepository playerRepository, QuotaTournamentRepository quotaTournamentRepository,
                                   ImportedTournamentRepository importedTournamentRepository,
                                   RegionalGroupRepository regionalGroupRepository, StateRepository stateRepository,
                                   ImportedTournamentService importedTournamentService) {
        this.playerRepository = playerRepository;
        this.quotaTournamentRepository = quotaTournamentRepository;
        this.importedTournamentRepository = importedTournamentRepository;
        this.regionalGroupRepository = regionalGroupRepository;
        this.stateRepository = stateRepository;
        this.importedTournamentService = importedTournamentService;
    }

    @Override
    public void run(String... strings) {
        this.addPlayers();
        this.addRegionalGroupsAndStates();
        this.addQuotaTournaments();
    }

    private void addPlayers() {
        Player player1 = new Player("Tobias", "Göttel", 1994, "VfL Sindelfingen");
        Player player2 = new Player("Lukas", "Göttel", 1998, "SC Niedermohr");
        playerRepository.save(player1);
        playerRepository.save(player2);
    }

    private void addQuotaTournaments() {

        // Initially add the QuotaTournament.
        QuotaTournament quotaTournament = new QuotaTournament("DVM U10", 2019);
        quotaTournamentRepository.save(quotaTournament);

        // Add the ImportedTournaments to QuotaTournaments and update the same.
        quotaTournament.setImportedTournaments(addImportedTournaments(quotaTournament));
        quotaTournamentRepository.save(quotaTournament);

    }

    private Set<ImportedTournament> addImportedTournaments(QuotaTournament quotaTournament) {

        // Import the tournaments for 2017 and 2018. 2018 also gets an import from a file automatically.
        ImportedTournament importedTournament2018 = new ImportedTournament("2018", quotaTournament);
        ImportedTournament importedTournament2017 = new ImportedTournament("2017", quotaTournament);
        ImportedTournament importedTournament2016 = new ImportedTournament("2016", quotaTournament);
        Set<ImportedTournament> importedTournaments = new HashSet<>(Arrays.asList(importedTournament2018, importedTournament2017, importedTournament2016));
        importedTournamentRepository.saveAll(importedTournaments);

        for (ImportedTournament importedTournament : importedTournaments)
            this.addImportedTournamentEntries(importedTournament);

        return importedTournaments;
    }

    private void addImportedTournamentEntries(ImportedTournament importedTournament) {

        Resource resource = new ClassPathResource("csv/U10-" + importedTournament.getName() + ".csv");

        ImportedTournament importedTournamentFromCsv = new ImportedTournament();

        try {
            importedTournamentFromCsv = importedTournamentService.importTournament(resource.getInputStream(), importedTournament.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        importedTournament.setImportedTournamentEntries(importedTournamentFromCsv.getImportedTournamentEntries());






    }

    private void addRegionalGroupsAndStates() {

        // Initialize RegionalGroups
        RegionalGroup rgNord = new RegionalGroup("Nord");
        RegionalGroup rgWest = new RegionalGroup("West");
        RegionalGroup rgMitte = new RegionalGroup("Mitte");
        RegionalGroup rgSuedost = new RegionalGroup("Süd-Ost");
        RegionalGroup rgSued = new RegionalGroup("Süd");
        regionalGroupRepository.saveAll(new HashSet<>(Arrays.asList(rgNord, rgWest, rgMitte, rgSuedost, rgSued)));

        // Initialize States
        State sBer = new State("Berlin", rgNord);
        State sBra = new State("Brandenburg", rgNord);
        State sBre = new State("Bremen", rgNord);
        State sHam = new State("Hamburg", rgNord);
        State sMvp = new State("Mecklenburg-Vorpommern", rgNord);
        State sNds = new State("Niedersachsen", rgNord);
        State sSah = new State("Sachsen-Anhalt", rgNord);
        State sSho = new State("Schleswig-Holstein", rgNord);
        State sNrw = new State("Nordrhein-Westfalen", rgWest);
        State sHes = new State("Hessen", rgMitte);
        State sThu = new State("Thüringen", rgMitte);
        State sRlp = new State("Rheinland-Pfalz", rgMitte);
        State sSaa = new State("Saarland", rgMitte);
        State sBay = new State("Bayern", rgSuedost);
        State sSac = new State("Sachsen", rgSuedost);
        State sBad = new State("Baden", rgSued);
        State sWur = new State("Württemberg", rgSued);
        stateRepository.saveAll(new HashSet<>(Arrays.asList(sBer, sBra, sBre, sHam, sMvp, sNds, sSah, sSho, sNrw, sHes,
                sThu, sRlp, sSaa, sBay, sSac, sBad, sWur)));

        // Add states to RegionalGroups as well and update the same.
        rgNord.setStates(new HashSet<>(Arrays.asList(sBer, sBra, sBre, sHam, sMvp, sNds, sSah, sSho)));
        rgWest.setStates(new HashSet<>(Collections.singletonList(sNrw)));
        rgMitte.setStates(new HashSet<>(Arrays.asList(sHes, sThu, sRlp, sSaa)));
        rgSuedost.setStates(new HashSet<>(Arrays.asList(sBay, sSac)));
        rgSued.setStates(new HashSet<>(Arrays.asList(sBad, sWur)));
        regionalGroupRepository.saveAll(new HashSet<>(Arrays.asList(rgNord, rgWest, rgMitte, rgSuedost, rgSued)));

    }

}
