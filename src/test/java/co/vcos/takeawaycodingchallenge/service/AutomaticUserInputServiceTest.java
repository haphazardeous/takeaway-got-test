package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.model.PlayOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AutomaticUserInputServiceTest {

    private UserInputService userInputService;

    @BeforeEach
    public void setUp() {
        userInputService = new AutomaticUserInputService();
    }


    @Test
    public void givenValidNumber_whenCalculateNextNumber_thenReturnCorrectNextNumber() {
        int number = 56;
        PlayOperation expectedPlayOperation = PlayOperation.PLUS;
        assertEquals(expectedPlayOperation, userInputService.getUserInputForNumber(number));

        number = 19;
        expectedPlayOperation = PlayOperation.MINUS;
        assertEquals(expectedPlayOperation, userInputService.getUserInputForNumber(number));

        number = 6;
        expectedPlayOperation = PlayOperation.ZERO;
        assertEquals(expectedPlayOperation, userInputService.getUserInputForNumber(number));

        number = 2;
        expectedPlayOperation = PlayOperation.PLUS;
        assertEquals(expectedPlayOperation, userInputService.getUserInputForNumber(number));
    }

    @Test
    public void givenInvalidNumber_whenCalculateNextNumber_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> userInputService.getUserInputForNumber(1));
    }

}
