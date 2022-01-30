package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.model.PlayOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GamePlayServiceImplTest {

    @Mock
    private UserInputService userInputService;
    @Mock
    private OpponentService opponentService;
    private GamePlayService gamePlayService;

    @BeforeEach
    public void setUp() {
        gamePlayService = new GamePlayServiceImpl(userInputService, opponentService);
    }

    @Test
    public void givenCorrectFirstNumber_whenStart_thenSendNumberStraightAway() {
        int firstNumber = 56;

        gamePlayService.start(firstNumber);

        verify(opponentService, times(1)).send(eq(firstNumber));
    }

    @Test
    public void givenIncorrectFirstNumber_whenStart_thenSendNumberStraightAway() {
        int firstNumber = 1;

        assertThrows(IllegalArgumentException.class, () -> gamePlayService.start(firstNumber));
    }

    @Test
    public void givenNextNumberIsNotOne_whenPlay_thenSendNumber() {
        int number = 19;
        int nextNumber = 6;

        when(userInputService.getUserInputForNumber(eq(number))).thenReturn(PlayOperation.MINUS);

        gamePlayService.play(number);

        verify(opponentService, times(1)).send(eq(nextNumber));
    }

    @Test
    public void givenNextNumberIsOne_whenPlay_thenFinishGame() {
        int number = 2;

        when(userInputService.getUserInputForNumber(eq(number))).thenReturn(PlayOperation.PLUS);

        gamePlayService.play(number);

        verifyNoInteractions(opponentService);
    }

}
