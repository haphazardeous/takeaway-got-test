package co.vcos.takeawaycodingchallenge.api;

import co.vcos.takeawaycodingchallenge.event.PlayEvent;
import co.vcos.takeawaycodingchallenge.event.StartGameEvent;
import co.vcos.takeawaycodingchallenge.model.PlayRequest;
import co.vcos.takeawaycodingchallenge.service.EventPublisher;
import co.vcos.takeawaycodingchallenge.util.RandomNumberGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
class GameControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventPublisher eventPublisher;
    @MockBean
    private RandomNumberGenerator randomNumberGenerator;

    @Test
    public void givenNoFirstNumber_whenStart_GenerateRandomFirstNumberAndPublishEventAndReturnOK() throws Exception {
        int firstNumber = 56;
        when(randomNumberGenerator.getRandomNumber()).thenReturn(firstNumber);

        mockMvc.perform(get("/start"))
                .andExpect(status().isOk());

        verify(eventPublisher).publishEvent(eq(new StartGameEvent(firstNumber)));
    }

    @Test
    public void givenFirstNumber_whenStart_GenerateRandomFirstNumberAndPublishEventAndReturnOK() throws Exception {
        int firstNumber = 56;

        mockMvc.perform(get("/start?firstNumber=" + firstNumber))
                .andExpect(status().isOk());

        verify(eventPublisher).publishEvent(eq(new StartGameEvent(firstNumber)));
    }

    @Test
    public void givenValidPlayRequest_whenPlay_ThenPublishPlayEventAndReturnOK() throws Exception {
        PlayRequest playRequest = new PlayRequest(56);

        mockMvc.perform(post("/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playRequest)))
                .andExpect(status().isOk());

        verify(eventPublisher).publishEvent(eq(new PlayEvent(playRequest.getNumber())));
    }

    @Test
    public void givenNullPlayRequest_whenPlay_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(eventPublisher);
    }

    @Test
    public void givenEmptyPlayRequest_whenPlay_ThenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(eventPublisher);
    }

}
