package co.vcos.takeawaycodingchallenge.listener;

import co.vcos.takeawaycodingchallenge.event.PlayEvent;
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
class PlayEventListenerTest {

    @Mock
    private GamePlayService opponentService;
    private PlayEventListener playEventListener;

    @BeforeEach
    public void setUp() {
        playEventListener = new PlayEventListener(opponentService);
    }

    @Test
    public void whenHandlePlayEvent_thenInvokeOpponentServicePlay() {
        int expectedNumber = 19;

        playEventListener.handlePlayEvent(new PlayEvent(expectedNumber));

        verify(opponentService, times(1)).play(eq(expectedNumber));

    }

}
