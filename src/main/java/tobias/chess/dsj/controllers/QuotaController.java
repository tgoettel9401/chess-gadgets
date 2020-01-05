package tobias.chess.dsj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tobias.chess.dsj.models.quota.QuotaTournament;
import tobias.chess.dsj.models.quota.StateQuotaWithIntermediates;
import tobias.chess.dsj.repositories.quota.QuotaTournamentRepository;
import tobias.chess.dsj.services.QuotaCalculationService;

import java.io.IOException;
import java.util.List;

@RestController
public class QuotaController {

    private QuotaCalculationService quotaCalculationService;
    private QuotaTournamentRepository quotaTournamentRepository;

    @Autowired
    public QuotaController(QuotaCalculationService quotaCalculationService, QuotaTournamentRepository quotaTournamentRepository) {
        this.quotaCalculationService = quotaCalculationService;
        this.quotaTournamentRepository = quotaTournamentRepository;
    }


    @GetMapping("api/quotaTournaments/{id}/getQuotaCalculation")
    public List<StateQuotaWithIntermediates> getQuotaCalculationForQuotaTournamentAndYear(
            @PathVariable("id") Long quotaTournamentId,
            @RequestParam("type") String type) throws IOException {

        // Only the types old and new are valid.
        if (!(type.equals("old") || type.equals("new")))
            throw new IOException("The provided type is not valid. Only 'old' and 'new' is allowed!");

        // Extract QuotaTournament from the provided Id.
        QuotaTournament quotaTournament;
        if (quotaTournamentRepository.findById(quotaTournamentId).isPresent())
            quotaTournament = quotaTournamentRepository.findById(quotaTournamentId).get();
        else
            throw new IOException("The QuotaTournament cannot be found!");

        return quotaCalculationService.getQuotaCalculation(quotaTournament, type);

    }

}
