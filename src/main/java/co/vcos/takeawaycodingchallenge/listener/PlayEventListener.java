package co.vcos.takeawaycodingchallenge.listener;

import co.vcos.takeawaycodingchallenge.event.PlayEvent;
import co.vcos.takeawaycodingchallenge.service.GamePlayService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author vedat
 */
@Component
public class PlayEventListener {
    private final GamePlayService opponentService;

    public PlayEventListener(GamePlayService opponentService) {
        this.opponentService = opponentService;
    }

    @Async
    @EventListener
    public void handlePlayEvent(PlayEvent playEvent) {
        opponentService.play(playEvent.number());
    }
}
