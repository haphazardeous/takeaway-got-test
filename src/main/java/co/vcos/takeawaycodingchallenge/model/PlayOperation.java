package co.vcos.takeawaycodingchallenge.model;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author vedat
 */
public enum PlayOperation {
    MINUS('-', n -> n - 1),
    ZERO('0', n -> n),
    PLUS('+', n -> n + 1)
    ;

    private final char operationChar;
    private final Function<Integer, Integer> operationFunction;

    PlayOperation(char operationChar, Function<Integer, Integer> operationFunction) {
        this.operationChar = operationChar;
        this.operationFunction = operationFunction;
    }

    public static PlayOperation fromCharacter(char character) {
        return Arrays.stream(values())
                .filter(playOperation -> playOperation.operationChar == character)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Play operation char " + character + " is invalid"));
    }

    public Integer apply(int number) {
        return operationFunction.apply(number);
    }

    public char getOperationChar() {
        return operationChar;
    }
}
