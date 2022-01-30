package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.model.PlayOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static co.vcos.takeawaycodingchallenge.util.Constants.MIN_NUMBER;
import static co.vcos.takeawaycodingchallenge.util.Constants.WINNING_NUMBER;

/**
 * @author vedat
 */
@Service
public class GamePlayServiceImpl implements GamePlayService {
    private final Logger logger = LoggerFactory.getLogger(GamePlayServiceImpl.class);
    private final UserInputService userInputService;
    private final OpponentService opponentService;

    public GamePlayServiceImpl(UserInputService userInputService, OpponentService opponentService) {
        this.userInputService = userInputService;
        this.opponentService = opponentService;
    }

    @Override
    public void start(int firstNumber) {
        logger.info("+++ Starting game with number:" + firstNumber);

        if (firstNumber < MIN_NUMBER) {
            throw new IllegalArgumentException("First number cannot be less than " + MIN_NUMBER);
        }

        opponentService.send(firstNumber);
    }

    @Override
    public void play(int number) {
        validateCurrentNumber(number);

        PlayOperation playOperation = userInputService.getUserInputForNumber(number);
        int postPlay = playOperation.apply(number);
        int nextNumber = postPlay / 3;
        logger.info("... retrieved:" + number + "\tplayed:"  + playOperation.getOperationChar() +
                " (" + postPlay +"\t/ 3 =)" + "\tnextNumber:" + nextNumber);

        if (nextNumber == WINNING_NUMBER) {
            logger.info("--- YOU WON! The game has ended.");
        } else {
            opponentService.send(nextNumber);
        }
    }

    private void validateCurrentNumber(int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException("Current number " + number + " cannot be played");
        }
    }

}
