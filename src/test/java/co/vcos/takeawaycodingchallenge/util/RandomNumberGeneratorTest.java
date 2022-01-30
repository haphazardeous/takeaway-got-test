package co.vcos.takeawaycodingchallenge.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomNumberGeneratorTest {

    @Test
    public void givenUpperLimit_whenGetRandomNumber_thenReturnRandomNumber() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator(5);
        assertTrue(randomNumberGenerator.getRandomNumber() <= 5);
    }

}
