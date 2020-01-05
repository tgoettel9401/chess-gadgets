package tobias.chess.dsj.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tobias.chess.dsj.models.playerInformation.Player;
import tobias.chess.dsj.models.quota.*;
import tobias.chess.dsj.repositories.playerInformation.PlayerRepository;
import tobias.chess.dsj.repositories.quota.*;
import tobias.chess.dsj.services.ImportedTournamentService;

import java.io.IOException;
import java.util.*;

@Component
public class PlayerCommandLineRunner implements CommandLineRunner {

    private PlayerRepository playerRepository;
    private QuotaTournamentRepository quotaTournamentRepository;
    private ImportedTournamentRepository importedTournamentRepository;
    private RegionalGroupRepository regionalGroupRepository;
    private StateRepository stateRepository;
    private ImportedTournamentService importedTournamentService;
    private MembershipFigureRepository membershipFigureRepository;

    @Autowired
    public PlayerCommandLineRunner(PlayerRepository playerRepository, QuotaTournamentRepository quotaTournamentRepository,
                                   ImportedTournamentRepository importedTournamentRepository,
                                   RegionalGroupRepository regionalGroupRepository, StateRepository stateRepository,
                                   ImportedTournamentService importedTournamentService,
                                   MembershipFigureRepository membershipFigureRepository) {
        this.playerRepository = playerRepository;
        this.quotaTournamentRepository = quotaTournamentRepository;
        this.importedTournamentRepository = importedTournamentRepository;
        this.regionalGroupRepository = regionalGroupRepository;
        this.stateRepository = stateRepository;
        this.importedTournamentService = importedTournamentService;
        this.membershipFigureRepository = membershipFigureRepository;
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

        // Add the membership-figures for the tournament.
        quotaTournament.setMembershipFigures(addMembershipFigures(quotaTournament));

        quotaTournamentRepository.save(quotaTournament);

    }

    private Set<ImportedTournament> addImportedTournaments(QuotaTournament quotaTournament) {

        // Import the tournaments for 2017 and 2018. 2018 also gets an import from a file automatically.
        ImportedTournament importedTournament2018 = new ImportedTournament(2018, quotaTournament);
        ImportedTournament importedTournament2017 = new ImportedTournament(2017, quotaTournament);
        ImportedTournament importedTournament2016 = new ImportedTournament(2016, quotaTournament);
        Set<ImportedTournament> importedTournaments = new HashSet<>(Arrays.asList(importedTournament2018, importedTournament2017, importedTournament2016));
        importedTournamentRepository.saveAll(importedTournaments);

        for (ImportedTournament importedTournament : importedTournaments)
            this.addImportedTournamentEntries(importedTournament);

        return importedTournaments;
    }

    private List<MembershipFigure> addMembershipFigures(QuotaTournament quotaTournament) {

        // Import the membership-figures for DVM U10 2019.
        MembershipFigure membershipFigure1 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Berlin"), 187.0);
        MembershipFigure membershipFigure2 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Brandenburg"), 252.0);
        MembershipFigure membershipFigure3 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Bremen"), 50.0);
        MembershipFigure membershipFigure4 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Hamburg"), 227.0);
        MembershipFigure membershipFigure5 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Mecklenburg-Vorpommern"), 86.0);
        MembershipFigure membershipFigure6 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Niedersachsen"), 298.0);
        MembershipFigure membershipFigure7 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Sachsen-Anhalt"), 656.0);
        MembershipFigure membershipFigure8 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Schleswig-Holstein"), 138.0);
        MembershipFigure membershipFigure9 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Nordrhein-Westfalen"), 932.0);
        MembershipFigure membershipFigure10 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Hessen"), 426.0);
        MembershipFigure membershipFigure11 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Thüringen"), 190.0);
        MembershipFigure membershipFigure12 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Rheinland-Pfalz"), 215.0);
        MembershipFigure membershipFigure13 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Saarland"), 53.0);
        MembershipFigure membershipFigure14 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Bayern"), 929.0);
        MembershipFigure membershipFigure15 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Sachsen"), 341.0);
        MembershipFigure membershipFigure16 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Baden"), 473.0);
        MembershipFigure membershipFigure17 = new MembershipFigure(quotaTournament, stateRepository.findFirstByName("Württemberg"), 493.0);

        // Create a list of all membershipFigures.
        List<MembershipFigure> membershipFigures = Arrays.asList(membershipFigure1, membershipFigure2, membershipFigure3,
                membershipFigure4, membershipFigure5, membershipFigure6, membershipFigure7, membershipFigure8,
                membershipFigure9, membershipFigure10, membershipFigure11, membershipFigure12, membershipFigure13,
                membershipFigure14, membershipFigure15, membershipFigure16, membershipFigure17);

        // Save membershipFigures and return the saved instances including IDs.
        return membershipFigureRepository.saveAll(membershipFigures);

    }

    private void addImportedTournamentEntries(ImportedTournament importedTournament) {

        Resource resource = new ClassPathResource("csv/U10-" + importedTournament.getYear() + ".csv");

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
