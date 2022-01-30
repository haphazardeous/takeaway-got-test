package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.event.Event;

/**
 * @author vedat
 */
public interface EventPublisher {
    void publishEvent(Event event);
}
