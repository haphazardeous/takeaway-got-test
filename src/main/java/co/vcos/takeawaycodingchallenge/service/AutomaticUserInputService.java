package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.model.PlayOperation;
import org.springframework.stereotype.Service;

import static co.vcos.takeawaycodingchallenge.util.Constants.MIN_NUMBER;

/**
 * @author vedat
 */
@Service
public class AutomaticUserInputService implements UserInputService {

    @Override
    public PlayOperation getUserInputForNumber(int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException("Cannot calculate operation on number less than 2");
        }

        int mod = number % 3;

        return switch (mod) {
            case 0 -> PlayOperation.ZERO;
            case 2 -> PlayOperation.PLUS;
            case 1 -> PlayOperation.MINUS;
            default -> throw new RuntimeException("Invalid result from mod operation - something is very wrong");
        };
    }
}
