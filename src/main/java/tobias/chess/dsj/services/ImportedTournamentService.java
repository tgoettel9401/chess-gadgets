package tobias.chess.dsj.services;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobias.chess.dsj.models.quota.ImportedTournament;
import tobias.chess.dsj.models.quota.ImportedTournamentEntry;
import tobias.chess.dsj.models.quota.ImportedTournamentEntryInCsv;
import tobias.chess.dsj.models.quota.QuotaTournament;
import tobias.chess.dsj.repositories.quota.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ImportedTournamentService {

    private ImportedTournamentRepository importedTournamentRepository;
    private RegionalGroupRepository regionalGroupRepository;
    private StateRepository stateRepository;
    private ImportedTournamentEntryRepository importedTournamentEntryRepository;
    private QuotaTournamentRepository quotaTournamentRepository;

    @Autowired
    ImportedTournamentService(ImportedTournamentRepository importedTournamentRepository, RegionalGroupRepository regionalGroupRepository,
                              StateRepository stateRepository, ImportedTournamentEntryRepository importedTournamentEntryRepository,
                              QuotaTournamentRepository quotaTournamentRepository) {
        this.importedTournamentRepository = importedTournamentRepository;
        this.regionalGroupRepository = regionalGroupRepository;
        this.stateRepository = stateRepository;
        this.importedTournamentEntryRepository = importedTournamentEntryRepository;
        this.quotaTournamentRepository = quotaTournamentRepository;
    }

    public ImportedTournament createImportedTournament(Integer tournamentYear, QuotaTournament quotaTournament) {
        ImportedTournament importedTournament = importedTournamentRepository.save(new ImportedTournament(tournamentYear, quotaTournament));
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
