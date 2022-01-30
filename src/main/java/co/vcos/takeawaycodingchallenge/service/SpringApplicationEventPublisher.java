package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.event.Event;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author vedat
 */

@Service
public class SpringApplicationEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishEvent(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
