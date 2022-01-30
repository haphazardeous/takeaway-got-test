package co.vcos.takeawaycodingchallenge.service;

import co.vcos.takeawaycodingchallenge.model.PlayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author vedat
 */
@Service
public class HttpHalfDuplexOpponentService implements OpponentService {
    static final String PLAY_URI = "/play";

    private final Logger logger = LoggerFactory.getLogger(HttpHalfDuplexOpponentService.class);
    private final String opponentUrl;
    private final RestTemplate restTemplate;

    public HttpHalfDuplexOpponentService(@Value("${got.opponent.url}") String opponentUrl,
                                         RestTemplateBuilder restTemplateBuilder) {
        this.opponentUrl = opponentUrl;
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    public void send(int number) {
        try {
            PlayRequest playRequest = new PlayRequest(number);
            String url = opponentUrl + PLAY_URI;

            restTemplate.postForEntity(url, playRequest, Void.class);

        } catch (Exception e) {
            logger.error("Cannot proceed - opponent is offline (reason: " + e.getMessage() + ")");
        }

    }
}
