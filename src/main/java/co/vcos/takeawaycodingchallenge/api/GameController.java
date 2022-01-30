package co.vcos.takeawaycodingchallenge.api;

import co.vcos.takeawaycodingchallenge.event.PlayEvent;
import co.vcos.takeawaycodingchallenge.event.StartGameEvent;
import co.vcos.takeawaycodingchallenge.model.PlayRequest;
import co.vcos.takeawaycodingchallenge.service.EventPublisher;
import co.vcos.takeawaycodingchallenge.util.RandomNumberGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vedat
 */
@RestController
public class GameController {

    private final EventPublisher eventPublisher;
    private final RandomNumberGenerator randomNumberGenerator;

    public GameController(EventPublisher eventPublisher, RandomNumberGenerator randomNumberGenerator) {
        this.eventPublisher = eventPublisher;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @GetMapping(value = "start")
    public ResponseEntity<Void> start(
            @RequestParam(required = false, value = "firstNumber") Integer firstNumber) {

        if (firstNumber == null) {
            firstNumber = randomNumberGenerator.getRandomNumber();
        }

        eventPublisher.publishEvent(new StartGameEvent(firstNumber));
        return ResponseEntity.ok().build();
    }

    @PostMapping(value ="play")
    public ResponseEntity<Void> play(@RequestBody PlayRequest playRequest) {
        if (playRequest == null || playRequest.getNumber() == null) {
            return ResponseEntity.badRequest().build();
        }

        eventPublisher.publishEvent(new PlayEvent(playRequest.getNumber()));
        return ResponseEntity.ok().build();
    }
}
