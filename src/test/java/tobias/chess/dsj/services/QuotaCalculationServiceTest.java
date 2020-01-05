package tobias.chess.dsj.services;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.math3.util.Precision;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import tobias.chess.dsj.models.quota.*;
import tobias.chess.dsj.repositories.quota.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class QuotaCalculationServiceTest {

    @Test
    public void testOldQuotaCalculation() {

        // Mock Repositories necessary for ImportedTournamentService
        ImportedTournamentRepository importedTournamentRepository = Mockito.mock(ImportedTournamentRepository.class);
        RegionalGroupRepository regionalGroupRepository = Mockito.mock(RegionalGroupRepository.class);
        StateRepository stateRepository = Mockito.mock(StateRepository.class);
        ImportedTournamentEntryRepository importedTournamentEntryRepository = Mockito.mock(ImportedTournamentEntryRepository.class);
        QuotaTournamentRepository quotaTournamentRepository = Mockito.mock(QuotaTournamentRepository.class);


        // Initialize ImportedTournamentService and QuotaCalculationService
        ImportedTournamentService importedTournamentService = new ImportedTournamentService(importedTournamentRepository,
                regionalGroupRepository, stateRepository, importedTournamentEntryRepository, quotaTournamentRepository);
        QuotaCalculationService quotaCalculationService = new QuotaCalculationService(importedTournamentService);

        // Initialize RegionalGroups, States
        List<RegionalGroup> regionalGroups = this.initializeRegionalGroups();
        List<State> states = this.initializeStates(regionalGroups);

        // Initialize QuotaTournament as Mock
        QuotaTournament quotaTournament = Mockito.mock(QuotaTournament.class);
        Mockito.when(quotaTournament.getYear()).thenReturn(2019);
        List<ImportedTournament> importedTournaments = this.initializeImportedTournaments(quotaTournament, states);

        // Initialize Membership-Figures for DVM-U10-2019, Mock quotaTournament to return the Membership-Figures
        List<MembershipFigure> membershipFigures = this.initializeMembershipFigures(quotaTournament, states);
        Mockito.when(quotaTournament.getMembershipFigures()).thenReturn(membershipFigures);
        Mockito.when(quotaTournament.getImportedTournaments()).thenReturn(new HashSet<>(importedTournaments));

        // Calculate the Quota by Service
        List<StateQuotaWithIntermediates> stateQuotaWithIntermediates = quotaCalculationService.getQuotaCalculation(quotaTournament, "old");

        // Get QuotaForState
        StateQuotaWithIntermediates quotaBay = this.getQuotaForState(stateQuotaWithIntermediates, "Bayern");
        StateQuotaWithIntermediates quotaSac = this.getQuotaForState(stateQuotaWithIntermediates, "Sachsen");

        StateQuotaWithIntermediates quotaNrw = this.getQuotaForState(stateQuotaWithIntermediates, "Nordrhein-Westfalen");
        StateQuotaWithIntermediates quotaNds = this.getQuotaForState(stateQuotaWithIntermediates, "Niedersachsen");
        StateQuotaWithIntermediates quotaBer = this.getQuotaForState(stateQuotaWithIntermediates, "Berlin");
        StateQuotaWithIntermediates quotaBre = this.getQuotaForState(stateQuotaWithIntermediates, "Bremen");
        StateQuotaWithIntermediates quotaBra = this.getQuotaForState(stateQuotaWithIntermediates, "Brandenburg");
        StateQuotaWithIntermediates quotaHam = this.getQuotaForState(stateQuotaWithIntermediates, "Hamburg");
        StateQuotaWithIntermediates quotaMvp = this.getQuotaForState(stateQuotaWithIntermediates, "Mecklenburg-Vorpommern");
        StateQuotaWithIntermediates quotaSah = this.getQuotaForState(stateQuotaWithIntermediates, "Sachsen-Anhalt");
        StateQuotaWithIntermediates quotaSho = this.getQuotaForState(stateQuotaWithIntermediates, "Schleswig-Holstein");
        StateQuotaWithIntermediates quotaHes = this.getQuotaForState(stateQuotaWithIntermediates, "Hessen");
        StateQuotaWithIntermediates quotaThu = this.getQuotaForState(stateQuotaWithIntermediates, "Thüringen");
        StateQuotaWithIntermediates quotaRlp = this.getQuotaForState(stateQuotaWithIntermediates, "Rheinland-Pfalz");
        StateQuotaWithIntermediates quotaSaa = this.getQuotaForState(stateQuotaWithIntermediates, "Saarland");
        StateQuotaWithIntermediates quotaBad = this.getQuotaForState(stateQuotaWithIntermediates, "Baden");
        StateQuotaWithIntermediates quotaWur = this.getQuotaForState(stateQuotaWithIntermediates, "Württemberg");

        // Assert that quota is calculated correctly (Before and After HareNiemayer.

        assert (Precision.round(quotaBay.getCalculationQuotaBeforeHareNiemayer(), 3) == 4.741);
        assert (quotaBay.getCalculationQuotaAfterHareNiemayer().equals(5.0));

        assert (Precision.round(quotaSac.getCalculationQuotaBeforeHareNiemayer(), 3) == 2.229);
        assert (quotaSac.getCalculationQuotaAfterHareNiemayer().equals(2.0));

        assert (Precision.round(quotaNrw.getCalculationQuotaBeforeHareNiemayer(), 3) == 4.810);
        assert (quotaNrw.getCalculationQuotaAfterHareNiemayer().equals(5.0));

        assert (Precision.round(quotaNds.getCalculationQuotaBeforeHareNiemayer(), 3) == 2.011);
        assert (quotaNds.getCalculationQuotaAfterHareNiemayer().equals(2.0));

        assert (Precision.round(quotaBer.getCalculationQuotaBeforeHareNiemayer(), 3) == 1.600);
        assert (quotaBer.getCalculationQuotaAfterHareNiemayer().equals(1.0));

        assert (Precision.round(quotaBre.getCalculationQuotaBeforeHareNiemayer(), 3) == 0.221);
        assert (quotaBre.getCalculationQuotaAfterHareNiemayer().equals(1.0));

        assert (Precision.round(quotaBra.getCalculationQuotaBeforeHareNiemayer(), 3) == 1.716);
        assert (quotaBra.getCalculationQuotaAfterHareNiemayer().equals(2.0));

        assert (Precision.round(quotaHam.getCalculationQuotaBeforeHareNiemayer(), 3) == 1.811);
        assert (quotaHam.getCalculationQuotaAfterHareNiemayer().equals(2.0));

        assert (Precision.round(quotaMvp.getCalculationQuotaBeforeHareNiemayer(), 3) == 0.380);
        assert (quotaMvp.getCalculationQuotaAfterHareNiemayer().equals(1.0));

        assert (Precision.round(quotaSah.getCalculationQuotaBeforeHareNiemayer(), 3) == 3.523);
        assert (quotaSah.getCalculationQuotaAfterHareNiemayer().equals(3.0));

        assert (Precision.round(quotaSho.getCalculationQuotaBeforeHareNiemayer(), 3) == 1.212);
        assert (quotaSho.getCalculationQuotaAfterHareNiemayer().equals(1.0));

        assert (Precision.round(quotaHes.getCalculationQuotaBeforeHareNiemayer(), 3) == 2.531);
        assert (quotaHes.getCalculationQuotaAfterHareNiemayer().equals(2.0));

        assert (Precision.round(quotaThu.getCalculationQuotaBeforeHareNiemayer(), 3) == 1.458);
        assert (quotaThu.getCalculationQuotaAfterHareNiemayer().equals(1.0));

        assert (Precision.round(quotaRlp.getCalculationQuotaBeforeHareNiemayer(), 3) == 1.203);
        assert (quotaRlp.getCalculationQuotaAfterHareNiemayer().equals(1.0));

        assert (Precision.round(quotaSaa.getCalculationQuotaBeforeHareNiemayer(), 3) == 0.234);
        assert (quotaSaa.getCalculationQuotaAfterHareNiemayer().equals(1.0));

        assert (Precision.round(quotaBad.getCalculationQuotaBeforeHareNiemayer(), 3) == 2.794);
        assert (quotaBad.getCalculationQuotaAfterHareNiemayer().equals(3.0));

        assert (Precision.round(quotaWur.getCalculationQuotaBeforeHareNiemayer(), 3) == 2.526);
        assert (quotaWur.getCalculationQuotaAfterHareNiemayer().equals(2.0));

    }

    private StateQuotaWithIntermediates getQuotaForState (List<StateQuotaWithIntermediates> stateQuotaWithIntermediates, String stateName) {
        return stateQuotaWithIntermediates.stream().filter(stateQuotaWithIntermediate -> stateQuotaWithIntermediate.getStateName().equals(stateName)).findFirst().orElseThrow();
    }

    private List<RegionalGroup> initializeRegionalGroups() {
        RegionalGroup rgNord = new RegionalGroup("Nord");
        RegionalGroup rgWest = new RegionalGroup("West");
        RegionalGroup rgMitte = new RegionalGroup("Mitte");
        RegionalGroup rgSuedost = new RegionalGroup("Süd-Ost");
        RegionalGroup rgSued = new RegionalGroup("Süd");
        return Arrays.asList(rgNord, rgWest, rgMitte, rgSuedost, rgSued);
    }

    private List<State> initializeStates(List<RegionalGroup> regionalGroups) {

        // Get Regional Groups
        RegionalGroup rgNord = regionalGroups.stream().filter(regionalGroup -> regionalGroup.getName().equals("Nord")).findFirst().orElseThrow();
        RegionalGroup rgWest = regionalGroups.stream().filter(regionalGroup -> regionalGroup.getName().equals("West")).findFirst().orElseThrow();
        RegionalGroup rgMitte = regionalGroups.stream().filter(regionalGroup -> regionalGroup.getName().equals("Mitte")).findFirst().orElseThrow();
        RegionalGroup rgSuedost = regionalGroups.stream().filter(regionalGroup -> regionalGroup.getName().equals("Süd-Ost")).findFirst().orElseThrow();
        RegionalGroup rgSued = regionalGroups.stream().filter(regionalGroup -> regionalGroup.getName().equals("Süd")).findFirst().orElseThrow();

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

        // Create and return list of all states.
        return Arrays.asList(sBer, sBra, sBre, sHam, sMvp, sNds, sSah, sSho, sNrw, sHes, sThu, sRlp, sSaa, sBay, sSac,
                sBad, sWur);
    }

    private List<MembershipFigure> initializeMembershipFigures(QuotaTournament quotaTournament, List<State> states) {

        // Initialize states
        State sBer = states.stream().filter(state -> state.getName().equals("Berlin")).findFirst().orElseThrow();
        State sBra = states.stream().filter(state -> state.getName().equals("Brandenburg")).findFirst().orElseThrow();
        State sBre = states.stream().filter(state -> state.getName().equals("Bremen")).findFirst().orElseThrow();
        State sHam = states.stream().filter(state -> state.getName().equals("Hamburg")).findFirst().orElseThrow();
        State sMvp = states.stream().filter(state -> state.getName().equals("Mecklenburg-Vorpommern")).findFirst().orElseThrow();
        State sNds = states.stream().filter(state -> state.getName().equals("Niedersachsen")).findFirst().orElseThrow();
        State sSah = states.stream().filter(state -> state.getName().equals("Sachsen-Anhalt")).findFirst().orElseThrow();
        State sSho = states.stream().filter(state -> state.getName().equals("Schleswig-Holstein")).findFirst().orElseThrow();
        State sNrw = states.stream().filter(state -> state.getName().equals("Nordrhein-Westfalen")).findFirst().orElseThrow();
        State sHes = states.stream().filter(state -> state.getName().equals("Hessen")).findFirst().orElseThrow();
        State sThu = states.stream().filter(state -> state.getName().equals("Thüringen")).findFirst().orElseThrow();
        State sRlp = states.stream().filter(state -> state.getName().equals("Rheinland-Pfalz")).findFirst().orElseThrow();
        State sSaa = states.stream().filter(state -> state.getName().equals("Saarland")).findFirst().orElseThrow();
        State sBay = states.stream().filter(state -> state.getName().equals("Bayern")).findFirst().orElseThrow();
        State sSac = states.stream().filter(state -> state.getName().equals("Sachsen")).findFirst().orElseThrow();
        State sBad = states.stream().filter(state -> state.getName().equals("Baden")).findFirst().orElseThrow();
        State sWur = states.stream().filter(state -> state.getName().equals("Württemberg")).findFirst().orElseThrow();

        // Initialization with membership figures of DVM-U10-2019!
        MembershipFigure membershipFigure1 = new MembershipFigure(quotaTournament, sBer, 187.0);
        MembershipFigure membershipFigure2 = new MembershipFigure(quotaTournament, sBra, 252.0);
        MembershipFigure membershipFigure3 = new MembershipFigure(quotaTournament, sBre, 50.0);
        MembershipFigure membershipFigure4 = new MembershipFigure(quotaTournament, sHam, 227.0);
        MembershipFigure membershipFigure5 = new MembershipFigure(quotaTournament, sMvp, 86.0);
        MembershipFigure membershipFigure6 = new MembershipFigure(quotaTournament, sNds, 298.0);
        MembershipFigure membershipFigure7 = new MembershipFigure(quotaTournament, sSah, 656.0);
        MembershipFigure membershipFigure8 = new MembershipFigure(quotaTournament, sSho, 138.0);
        MembershipFigure membershipFigure9 = new MembershipFigure(quotaTournament, sNrw, 932.0);
        MembershipFigure membershipFigure10 = new MembershipFigure(quotaTournament, sHes, 426.0);
        MembershipFigure membershipFigure11 = new MembershipFigure(quotaTournament, sThu, 190.0);
        MembershipFigure membershipFigure12 = new MembershipFigure(quotaTournament, sRlp, 215.0);
        MembershipFigure membershipFigure13 = new MembershipFigure(quotaTournament, sSaa, 53.0);
        MembershipFigure membershipFigure14 = new MembershipFigure(quotaTournament, sBay, 929.0);
        MembershipFigure membershipFigure15 = new MembershipFigure(quotaTournament, sSac, 341.0);
        MembershipFigure membershipFigure16 = new MembershipFigure(quotaTournament, sBad, 473.0);
        MembershipFigure membershipFigure17 = new MembershipFigure(quotaTournament, sWur, 493.0);

        return Arrays.asList(membershipFigure1, membershipFigure2, membershipFigure3, membershipFigure4, membershipFigure5,
                membershipFigure6, membershipFigure7, membershipFigure8, membershipFigure9, membershipFigure10,
                membershipFigure11, membershipFigure12, membershipFigure13, membershipFigure14, membershipFigure15,
                membershipFigure16, membershipFigure17);
    }

    private List<ImportedTournament> initializeImportedTournaments(QuotaTournament quotaTournament, List<State> states) {

        // Initialize tournaments 2016, 2017 and 2018 for DVM-U10-2019.
        ImportedTournament importedTournament2016 = new ImportedTournament(2016, quotaTournament);
        ImportedTournament importedTournament2017 = new ImportedTournament(2017, quotaTournament);
        ImportedTournament importedTournament2018 = new ImportedTournament(2018, quotaTournament);
        List<ImportedTournament> importedTournaments;

        // Initialize ressources for importing the tournaments.
        Resource resource2016 = new ClassPathResource("csv/U10-" + importedTournament2016.getYear() + ".csv");
        Resource resource2017 = new ClassPathResource("csv/U10-" + importedTournament2017.getYear() + ".csv");
        Resource resource2018 = new ClassPathResource("csv/U10-" + importedTournament2018.getYear() + ".csv");

        // Declare and initialize inputStreams
        InputStream inputStream2016 = null;
        InputStream inputStream2017 = null;
        InputStream inputStream2018 = null;

        // Get Input Streams for resources
        try {
            inputStream2016 = resource2016.getInputStream();
            inputStream2017 = resource2017.getInputStream();
            inputStream2018 = resource2018.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate CSV Mapper
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(ImportedTournamentEntryInCsv.class).withHeader().withColumnSeparator(';').withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(ImportedTournamentEntryInCsv.class).with(schema);

        // Declare ImportedTournamentEntryInCsv-Lists
        List<ImportedTournamentEntryInCsv> importedTournamentEntriesInCsv2016 = new ArrayList<>();
        List<ImportedTournamentEntryInCsv> importedTournamentEntriesInCsv2017 = new ArrayList<>();
        List<ImportedTournamentEntryInCsv> importedTournamentEntriesInCsv2018 = new ArrayList<>();

        // Perform import of CSVs.
        try {
            importedTournamentEntriesInCsv2016 = reader.<ImportedTournamentEntryInCsv>readValues(inputStream2016).readAll();
            importedTournamentEntriesInCsv2017 = reader.<ImportedTournamentEntryInCsv>readValues(inputStream2017).readAll();
            importedTournamentEntriesInCsv2018 = reader.<ImportedTournamentEntryInCsv>readValues(inputStream2018).readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize ImportedTournamentEntry-lists
        List<ImportedTournamentEntry> importedTournamentEntries2016 = new ArrayList<>();
        List<ImportedTournamentEntry> importedTournamentEntries2017 = new ArrayList<>();
        List<ImportedTournamentEntry> importedTournamentEntries2018 = new ArrayList<>();

        // Import 2016 and add to respective tournaments.
        for (ImportedTournamentEntryInCsv importedTournamentEntryInCsv : importedTournamentEntriesInCsv2016) {
            ImportedTournamentEntry importedTournamentEntry = this.createEntryFromCsvEntry(importedTournament2016, importedTournamentEntryInCsv, states);
            importedTournamentEntries2016.add(importedTournamentEntry);
        }
        importedTournament2016.getImportedTournamentEntries().addAll(importedTournamentEntries2016);

        // Import 2017
        for (ImportedTournamentEntryInCsv importedTournamentEntryInCsv : importedTournamentEntriesInCsv2017) {
            ImportedTournamentEntry importedTournamentEntry = this.createEntryFromCsvEntry(importedTournament2017, importedTournamentEntryInCsv, states);
            importedTournamentEntries2017.add(importedTournamentEntry);
        }
        importedTournament2017.getImportedTournamentEntries().addAll(importedTournamentEntries2017);

        // Import 2018
        for (ImportedTournamentEntryInCsv importedTournamentEntryInCsv : importedTournamentEntriesInCsv2018) {
            ImportedTournamentEntry importedTournamentEntry = this.createEntryFromCsvEntry(importedTournament2018, importedTournamentEntryInCsv, states);
            importedTournamentEntries2018.add(importedTournamentEntry);
        }
        importedTournament2018.getImportedTournamentEntries().addAll(importedTournamentEntries2018);

        // Add tournaments to list of importedTournaments.
        importedTournaments = Arrays.asList(importedTournament2016, importedTournament2017, importedTournament2018);

        return importedTournaments;

    }

    private ImportedTournamentEntry createEntryFromCsvEntry(ImportedTournament importedTournament, ImportedTournamentEntryInCsv importedTournamentEntryInCsv, List<State> states) {

        // Find state of player in entry.
        State state = states.stream().filter(innerState -> innerState.getName().equals(importedTournamentEntryInCsv.getState())).findFirst().orElseThrow();

        // Initialize the ImportedTournamentEntry and fill with the information from CSV-File.
        ImportedTournamentEntry importedTournamentEntry = new ImportedTournamentEntry();
        importedTournamentEntry.setPlace(importedTournamentEntryInCsv.getPlace());
        importedTournamentEntry.setTeam(importedTournamentEntryInCsv.getTeam());
        importedTournamentEntry.setAvgDwz(importedTournamentEntryInCsv.getAvgDwz());
        importedTournamentEntry.setState(state);
        importedTournamentEntry.setGamesWon(importedTournamentEntryInCsv.getGamesWon());
        importedTournamentEntry.setGamesDrawn(importedTournamentEntryInCsv.getGamesDrawn());
        importedTournamentEntry.setGamesLost(importedTournamentEntryInCsv.getGamesLost());
        importedTournamentEntry.setPoints(importedTournamentEntryInCsv.getPoints());
        importedTournamentEntry.setSoBerg(importedTournamentEntryInCsv.getSoBerg());
        importedTournamentEntry.setBoardPoints(importedTournamentEntryInCsv.getBoardPoints());

        // Finally also set the imported tournament to its newly created entry.
        importedTournamentEntry.setImportedTournament(importedTournament);

        return importedTournamentEntry;

    }



}