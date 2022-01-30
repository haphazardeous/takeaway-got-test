package co.vcos.takeawaycodingchallenge.listener;

import co.vcos.takeawaycodingchallenge.event.StartGameEvent;
import co.vcos.takeawaycodingchallenge.service.GamePlayService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author vedat
 */
@Component
public class StartGameEventListener {
    private final GamePlayService opponentService;

    public StartGameEventListener(GamePlayService opponentService) {
        this.opponentService = opponentService;
    }

    @Async
    @EventListener
    public void handleStartGameEvent(StartGameEvent startGameEvent) {
        opponentService.start(startGameEvent.firstNumber());
    }
}
