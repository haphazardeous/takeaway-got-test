package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpringApplicationEventPublisherTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    private SpringApplicationEventPublisher springApplicationEventPublisher;

    @BeforeEach
    public void setUp() {
        springApplicationEventPublisher = new SpringApplicationEventPublisher(applicationEventPublisher);
    }

    @Test
    public void whenPublishEvent_thenInvokeApplicationEventPublisher() {
        Event expected = mock(Event.class);

        springApplicationEventPublisher.publishEvent(expected);

        verify(applicationEventPublisher, times(1)).publishEvent(eq(expected));
    }

}
