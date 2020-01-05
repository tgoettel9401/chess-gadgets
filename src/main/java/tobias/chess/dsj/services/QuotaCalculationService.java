package tobias.chess.dsj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tobias.chess.dsj.models.quota.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class QuotaCalculationService {

    private ImportedTournamentService importedTournamentService;

    @Autowired
    public QuotaCalculationService(ImportedTournamentService importedTournamentService) {
        this.importedTournamentService = importedTournamentService;
    }

    // So far the values for U10 are fixed! TODO: Move to QuotaTournament-BaseData and use this information.
    private final Integer NUMBER_OF_WILDCARDS = 5;
    private final Integer NUMBER_OF_PARTICIPANTS = 40;

    // Factor old is Quality 1 : Quantity 3 (35 => 26,25 Quantity / 8,75 Quality)
    private final Integer FACTOR_QUANTITY_OLD = 3;
    private final Integer FACTOR_QUALITY_OLD = 1;

    public List<StateQuotaWithIntermediates> getQuotaCalculation(QuotaTournament quotaTournament, String type) {

        if (type.equals("old"))
            return this.getQuotaCalculationOld(quotaTournament, quotaTournament.getMembershipFigures());

        else // Only old and new is possible!
            return this.getQuotaCalculationNew(quotaTournament);

    }

    private List<StateQuotaWithIntermediates> getQuotaCalculationOld(QuotaTournament quotaTournament, List<MembershipFigure> membershipFigures) {

        // First calculate the number of participants that is to used for the quota.
        int totalNumber = NUMBER_OF_PARTICIPANTS - NUMBER_OF_WILDCARDS;

        // Distribute the totalNumber to Quantity and Quality.
        double totalNumberQuantity = (totalNumber * (double) FACTOR_QUANTITY_OLD / (FACTOR_QUANTITY_OLD + FACTOR_QUALITY_OLD));
        double totalNumberQuality = (totalNumber * (double) FACTOR_QUALITY_OLD / (FACTOR_QUANTITY_OLD + FACTOR_QUALITY_OLD));

        // 1) Calculate the figures by quantity

        // Get the CalculationFigures for Quantity.
        List<StateFigure> calculationFiguresForQuantity = this.getCalculationFigureForStateFigures(membershipFigures.stream().map(MembershipFigure::getStateFigure).collect(Collectors.toList()), totalNumberQuantity);

        // 2) Calculate the figures by quality

        // Get all the states.
        List<State> states = quotaTournament.getMembershipFigures().stream().map(MembershipFigure::getState).collect((Collectors.toList()));

        // Find the tournaments year-1, year-2 and year-3
        ImportedTournament importedTournamentYearMinus1 = quotaTournament.getImportedTournaments().stream().filter(importedTournament -> importedTournament.getYear().equals(quotaTournament.getYear()-1)).findFirst().orElseThrow();
        ImportedTournament importedTournamentYearMinus2 = quotaTournament.getImportedTournaments().stream().filter(importedTournament -> importedTournament.getYear().equals(quotaTournament.getYear()-2)).findFirst().orElseThrow();
        ImportedTournament importedTournamentYearMinus3 = quotaTournament.getImportedTournaments().stream().filter(importedTournament -> importedTournament.getYear().equals(quotaTournament.getYear()-3)).findFirst().orElseThrow();

        List<StateFigure> averageTeamPointsForAllStates = new ArrayList<>();

        // Get the DMP for all states and relevant importedTournaments.
        for (State state : states) {

            // Get the DMPs for all relevant years.
            Double dmpYearMinus1 = importedTournamentService.getDmpForImportedTournamentAndState(importedTournamentYearMinus1, state);
            Double dmpYearMinus2 = importedTournamentService.getDmpForImportedTournamentAndState(importedTournamentYearMinus2, state);
            Double dmpYearMinus3 = importedTournamentService.getDmpForImportedTournamentAndState(importedTournamentYearMinus3, state);

            // The DMPs year-1 and year-2 are count twice, year-3 only once.
            Double simpleDmp = dmpYearMinus1 * 2 + dmpYearMinus2 * 2 + dmpYearMinus3 * 1;

            // Create DMP and add to list for the DMPs.
            StateFigure averageTeamPoints = new StateFigure(state, simpleDmp);
            averageTeamPointsForAllStates.add(averageTeamPoints);

        }

        // Get the CalculationFigures for Quality.
        List<StateFigure> quotaCalculationFiguresForQuality = this.getCalculationFigureForStateFigures(averageTeamPointsForAllStates, totalNumberQuality);

        // For each state build the sum of Quantity and Quality
        List<StateFigure> quotaCalculationFigures = new ArrayList<>();
        for (State state : states) {
            double sum = 0.0;
            for (StateFigure quantityFigure : calculationFiguresForQuantity) {
                if (quantityFigure.getState().equals(state))
                    sum += quantityFigure.getFigure();
            }
            for (StateFigure qualityFigure : quotaCalculationFiguresForQuality) {
                if (qualityFigure.getState().equals(state))
                    sum += qualityFigure.getFigure();
            }
            StateFigure quotaCalculationFigure = new StateFigure(state, sum);
            quotaCalculationFigures.add(quotaCalculationFigure);
        }

        // Perform algorithm of Hare-Niemayer

        // 1) Round down to take complete places first (in case of 0, use 1 so that every team has at least 1 place!)
        List<StateFigure> quotaCalculationFiguresRoundedDown = new ArrayList<>();
        for (StateFigure stateFigure : quotaCalculationFigures) {
            double roundedDown;
            if (Math.floor(stateFigure.getFigure()) < 1)
                roundedDown = 1;
            else
                roundedDown = Math.floor(stateFigure.getFigure());
            StateFigure roundedDownStateFigure = new StateFigure(stateFigure.getState(), roundedDown);
            quotaCalculationFiguresRoundedDown.add(roundedDownStateFigure);
        }

        // 2) Calculate Figures for rest
        List<StateFigure> quotaCalculationFiguresOfRest = new ArrayList<>();
        for (StateFigure stateFigure : quotaCalculationFigures) {
            double diff = 0.0;
            for (StateFigure roundedStateFigure : quotaCalculationFiguresRoundedDown) {
                if (roundedStateFigure.getState().equals(stateFigure.getState())) {
                    diff = stateFigure.getFigure() - roundedStateFigure.getFigure();
                    // Bring to 0 if diff < 0.
                    if (diff < 0)
                        diff = 0.0;
                }
            }
            StateFigure restStateFigure = new StateFigure(stateFigure.getState(), diff);
            quotaCalculationFiguresOfRest.add(restStateFigure);
        }

        // 3) Calculate number places that have not been allocated yet.
        int numberOfRestPlaces = totalNumber - (int) quotaCalculationFiguresRoundedDown.stream().mapToDouble(StateFigure::getFigure).sum();

        // 4) Create List of states with highest numbers (maximum is numberOfRestPlaces though!)
        List<StateFigure> quotaCalculationFiguresOfRestTopN = new ArrayList<>();
        for (StateFigure stateFigure : quotaCalculationFiguresOfRest) {
            if (quotaCalculationFiguresOfRestTopN.size() >= numberOfRestPlaces) {
                StateFigure min = quotaCalculationFiguresOfRestTopN.stream().min(Comparator.comparing(StateFigure::getFigure)).orElseThrow(NoSuchElementException::new);
                if (stateFigure.getFigure() > min.getFigure()) {
                    quotaCalculationFiguresOfRestTopN.remove(min);
                    quotaCalculationFiguresOfRestTopN.add(stateFigure);
                }
            }
            else // The first n places are added automatically.
                quotaCalculationFiguresOfRestTopN.add(stateFigure);
        }

        // 5) Add 1 to every calculation figure being part of Top-N-List.
        List<StateFigure> finalQuotaCalculationFigures = new ArrayList<>();
        for (StateFigure stateFigure : quotaCalculationFiguresRoundedDown) {
            double figure = stateFigure.getFigure();
            for (StateFigure topNStateFigure : quotaCalculationFiguresOfRestTopN) {
                if (stateFigure.getState() == topNStateFigure.getState())
                    figure += 1;
            }
            StateFigure finalFigure = new StateFigure(stateFigure.getState(), figure);
            finalQuotaCalculationFigures.add(finalFigure);
        }

        // Create return object
        List<StateQuotaWithIntermediates> stateQuotaWithIntermediatesList = new ArrayList<>();
        for (State state : states) {
            Double membershipFigure = membershipFigures.stream().filter(stateFigure -> stateFigure.getState() == state).findFirst().orElseThrow(NoSuchElementException::new).getFigure();
            Double calculationQuotaQuantity = this.findFirstByState(calculationFiguresForQuantity, state).getFigure();
            Double averageTeamPoints = this.findFirstByState(averageTeamPointsForAllStates, state).getFigure();
            Double calculationQuotaQuality = this.findFirstByState(quotaCalculationFiguresForQuality, state).getFigure();
            Double calculationQuotaBeforeHareNiemayer = this.findFirstByState(quotaCalculationFigures, state).getFigure();
            Double calculationQuotaAfterHareNiemayer = this.findFirstByState(finalQuotaCalculationFigures, state).getFigure();
            StateQuotaWithIntermediates stateQuotaWithIntermediates = new StateQuotaWithIntermediates(state, membershipFigure, calculationQuotaQuantity, averageTeamPoints, calculationQuotaQuality, calculationQuotaBeforeHareNiemayer, calculationQuotaAfterHareNiemayer);
            stateQuotaWithIntermediatesList.add(stateQuotaWithIntermediates);
        }

        return stateQuotaWithIntermediatesList;
    }

    private List<StateQuotaWithIntermediates> getQuotaCalculationNew(QuotaTournament quotaTournament) {
        List<StateQuotaWithIntermediates> quotaCalculationFigures = new ArrayList<>();


        return quotaCalculationFigures;
    }

    private List<StateFigure> getCalculationFigureForStateFigures(List<StateFigure> stateFigures, Double totalNumberOfPlaces) {

        // Initialize the list of CalculationFigures
        List<StateFigure> calculationFigures = new ArrayList<>();

        // First calculate sum of all StateFigures.
        double sumOfAllFigures = stateFigures.stream().mapToDouble(StateFigure::getFigure).sum();

        // For every state, get the percentage and the resulting totalNumberOfPlaces.

        // TODO: Refactor to use either RegionalGroup or State!
        for (StateFigure stateFigure : stateFigures) {
            double figure = (stateFigure.getFigure() / sumOfAllFigures) * totalNumberOfPlaces;
            StateFigure quotaCalculationFigure = new StateFigure(stateFigure.getState(), figure);
            calculationFigures.add(quotaCalculationFigure);
        }

        return calculationFigures;
    }

    private StateFigure findFirstByState(List<StateFigure> stateFigures, State state) {
        return stateFigures.stream().filter(stateFigure -> stateFigure.getState() == state).findFirst().orElseThrow(NoSuchElementException::new);
    }

}
