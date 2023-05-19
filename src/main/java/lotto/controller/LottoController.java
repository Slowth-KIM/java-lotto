package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;

public class LottoController {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        Money amount = inputView.promptPurchaseAmount();
        int ticketCount = inputView.promptManualTicketCount();
        List<LottoTicket> manualTickets = inputView.promptManualTicketNumbers(ticketCount);

        List<LottoTicket> lottoTickets = LottoMachine.buyLottoTickets(amount, manualTickets);
        resultView.viewBuyingResult(lottoTickets, manualTickets.size());

        WinningNumber winningNumber = new WinningNumber(inputView.promptWinningNumbers()
                , inputView.promptBonusNumber());
        LottoResultChecker lottoResultChecker = new LottoResultChecker(lottoTickets, winningNumber);

        resultView.viewWinningResult(lottoResultChecker);
        resultView.viewRateOfInvestment(amount, lottoResultChecker.getTotalPrizeMoney());
    }
}
