package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.model.PlayRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HttpHalfDuplexOpponentServiceTest {

    private final String opponentUrl = "http://localhost:8090";
    @Mock
    private RestTemplate restTemplate;
    private OpponentService opponentService;

    @BeforeEach
    public void setUp() {
        RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        opponentService = new HttpHalfDuplexOpponentService(opponentUrl, restTemplateBuilder);
    }

    @Test
    public void whenSend_thenInvokeRestTemplate() {
        int number = 10;
        PlayRequest expectedPlayRequest = new PlayRequest(number);
        String expectedUrl = opponentUrl + HttpHalfDuplexOpponentService.PLAY_URI;

        opponentService.send(number);

        verify(restTemplate).postForEntity(eq(expectedUrl), eq(expectedPlayRequest), eq(Void.class));
    }

}
