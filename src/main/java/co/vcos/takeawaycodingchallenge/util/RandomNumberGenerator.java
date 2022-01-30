package co.vcos.takeawaycodingchallenge.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author vedat
 */
@Component
public class RandomNumberGenerator {

    private final int upperLimit;
    private final Random random;

    public RandomNumberGenerator(@Value("${got.startingNumber.upperLimit:200}") int upperLimit) {
        this.upperLimit = upperLimit;
        random = new Random();
    }

    public int getRandomNumber() {
        return random.nextInt(upperLimit);
    }
}
