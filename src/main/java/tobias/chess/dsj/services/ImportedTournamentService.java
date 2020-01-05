package tobias.chess.dsj.services;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobias.chess.dsj.models.quota.*;
import tobias.chess.dsj.repositories.quota.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportedTournamentService {

    private ImportedTournamentRepository importedTournamentRepository;
    private StateRepository stateRepository;
    private ImportedTournamentEntryRepository importedTournamentEntryRepository;
    private QuotaTournamentRepository quotaTournamentRepository;

    @Autowired
    ImportedTournamentService(ImportedTournamentRepository importedTournamentRepository, RegionalGroupRepository regionalGroupRepository,
                              StateRepository stateRepository, ImportedTournamentEntryRepository importedTournamentEntryRepository,
                              QuotaTournamentRepository quotaTournamentRepository) {
        this.importedTournamentRepository = importedTournamentRepository;
        this.stateRepository = stateRepository;
        this.importedTournamentEntryRepository = importedTournamentEntryRepository;
        this.quotaTournamentRepository = quotaTournamentRepository;
    }

    public ImportedTournament createImportedTournament(Integer tournamentYear, QuotaTournament quotaTournament, State state) {
        ImportedTournament importedTournament = importedTournamentRepository.save(new ImportedTournament(tournamentYear, quotaTournament, state));
        quotaTournament.getImportedTournaments().add(importedTournament);
        quotaTournamentRepository.save(quotaTournament);
        return importedTournament;
    }

    public ImportedTournament importTournament(InputStream importStream, Long importedTournamentId) throws IOException {

        // First find tournament to import to.
        ImportedTournament importedTournament =  new ImportedTournament();
        if (importedTournamentRepository.findById(importedTournamentId).isPresent())
            importedTournament = importedTournamentRepository.findById(importedTournamentId).get();

        // Generate CSV from the file.
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(ImportedTournamentEntryInCsv.class).withHeader().withColumnSeparator(';').withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(ImportedTournamentEntryInCsv.class).with(schema);
        List<ImportedTournamentEntryInCsv> importedTournamentEntriesInCsv = reader.<ImportedTournamentEntryInCsv>readValues(importStream).readAll();

        // Generate the correct ImportedTournamentEntries
        for (ImportedTournamentEntryInCsv importedTournamentEntryInCsv : importedTournamentEntriesInCsv) {
            ImportedTournamentEntry importedTournamentEntry = this.createEntryFromCsvEntry(importedTournament, importedTournamentEntryInCsv);
            importedTournament.getImportedTournamentEntries().add(importedTournamentEntry);
        }

        return importedTournamentRepository.save(importedTournament);
    }

    public Double getDmpForImportedTournamentAndState(ImportedTournament importedTournament, State state) {

        // Initialize DMP.
        double dmp;

        // Find all ImportedTournamentEntries for the ImportedTournament and State.
        List<ImportedTournamentEntry> importedTournamentEntries = importedTournament.getImportedTournamentEntries().stream().filter(entry -> entry.getState().getName().equals(state.getName())).collect(Collectors.toList());
        List<ImportedTournamentEntry> importedTournamentEntriesOfTop40 = new ArrayList<>(importedTournamentEntries);

        // Calculate DMP as follows
        // - Only use the Top-40 teams
        // - If more than 5 teams, then only use the 5 best teams
        // - TODO: Ignore the worst entry for the state/regionalGroup who hosted the importedTournament
        //          (only in case it was not the only entry for this state/regionalGroup and only if the
        //          importedTournament was not an open tournament!).
        // - Build the sum of all entries and divide by the number of entries.

        // Only use Top-40-Teams.
        for (ImportedTournamentEntry entry : importedTournamentEntries) {
            if (entry.getPlace() > 40)
                importedTournamentEntriesOfTop40.remove(entry);
        }

        List<ImportedTournamentEntry> importedTournamentEntriesOfTop40AndTop5OfState = new ArrayList<>(importedTournamentEntriesOfTop40);

        // Only use Top 5 teams of state. Therefore list has to be order according to place ascending!
        importedTournamentEntriesOfTop40.sort(Comparator.comparingInt(ImportedTournamentEntry::getPlace));
        if (importedTournamentEntriesOfTop40.size() > 5) {
            int i = 1;
            for (ImportedTournamentEntry entry : importedTournamentEntriesOfTop40) {
                if (i > 5) {
                    importedTournamentEntriesOfTop40AndTop5OfState.remove(entry);
                }
                i++;
            }
        }

        // Build the sum of points
        double points = importedTournamentEntriesOfTop40AndTop5OfState.stream()
                .mapToDouble(ImportedTournamentEntry::getPoints).sum();

        // And divide the sum by the number of teams (if either points or size is 0 than return 0 instead.
        if (points != 0 && importedTournamentEntriesOfTop40AndTop5OfState.size() != 0)
            dmp = points / importedTournamentEntriesOfTop40AndTop5OfState.size();
        else
            dmp = 0;

        return dmp;
    }

    private ImportedTournamentEntry createEntryFromCsvEntry(ImportedTournament importedTournament, ImportedTournamentEntryInCsv importedTournamentEntryInCsv) {

        // Initialize the ImportedTournamentEntry and fill with the information from CSV-File.
        ImportedTournamentEntry importedTournamentEntry = new ImportedTournamentEntry();
        importedTournamentEntry.setPlace(importedTournamentEntryInCsv.getPlace());
        importedTournamentEntry.setTeam(importedTournamentEntryInCsv.getTeam());
        importedTournamentEntry.setAvgDwz(importedTournamentEntryInCsv.getAvgDwz());
        importedTournamentEntry.setState(stateRepository.findFirstByName(importedTournamentEntryInCsv.getState()));
        importedTournamentEntry.setGamesWon(importedTournamentEntryInCsv.getGamesWon());
        importedTournamentEntry.setGamesDrawn(importedTournamentEntryInCsv.getGamesDrawn());
        importedTournamentEntry.setGamesLost(importedTournamentEntryInCsv.getGamesLost());
        importedTournamentEntry.setPoints(importedTournamentEntryInCsv.getPoints());
        importedTournamentEntry.setSoBerg(importedTournamentEntryInCsv.getSoBerg());
        importedTournamentEntry.setBoardPoints(importedTournamentEntryInCsv.getBoardPoints());

        // Finally also set the imported tournament to its newly created entry.
        importedTournamentEntry.setImportedTournament(importedTournament);

        return importedTournamentEntryRepository.save(importedTournamentEntry);

    }

}
