package co.vcos.takeawaycodingchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TakeawayCodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeawayCodingChallengeApplication.class, args);
    }

}
