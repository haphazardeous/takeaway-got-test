package co.vcos.takeawaycodingchallenge.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayOperationTest {

    @Test
    public void givenAllCases_whenFromCharacter_thenReturnCorrectEnum() {
        assertEquals(PlayOperation.MINUS, PlayOperation.fromCharacter('-'));
        assertEquals(PlayOperation.PLUS, PlayOperation.fromCharacter('+'));
        assertEquals(PlayOperation.ZERO, PlayOperation.fromCharacter('0'));
        assertThrows(IllegalArgumentException.class, () -> PlayOperation.fromCharacter('A'));
    }

    @Test
    public void givenAllCases_whenGetCharacter_thenReturnCorrectCharacter() {
        assertEquals('-', PlayOperation.MINUS.getOperationChar());
        assertEquals('+', PlayOperation.PLUS.getOperationChar());
        assertEquals('0', PlayOperation.ZERO.getOperationChar());
    }

    @Test
    public void givenAllCases_whenApply_thenReturnCorrectValue() {
        int number = 56;
        assertEquals(56 - 1, PlayOperation.MINUS.apply(number));
        assertEquals(56 + 1, PlayOperation.PLUS.apply(number));
        assertEquals(56, PlayOperation.ZERO.apply(number));
    }

}
