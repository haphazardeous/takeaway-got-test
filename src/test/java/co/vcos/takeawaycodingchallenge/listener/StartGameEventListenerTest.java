package co.vcos.takeawaycodingchallenge.listener;

import co.vcos.takeawaycodingchallenge.event.StartGameEvent;
import co.vcos.takeawaycodingchallenge.service.GamePlayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartGameEventListenerTest {

    @Mock
    private GamePlayService opponentService;
    private StartGameEventListener startGameEventListener;

    @BeforeEach
    public void setUp() {
        startGameEventListener = new StartGameEventListener(opponentService);
    }

    @Test
    public void whenHandleStartGameEvent_thenInvokeOpponentServiceStart() {
        int expectedFirstNumber = 7;

        startGameEventListener.handleStartGameEvent(new StartGameEvent(expectedFirstNumber));

        verify(opponentService, times(1)).start(eq(expectedFirstNumber));
    }

}
