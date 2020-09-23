package academy.programming.config;

import academy.programming.GuessCount;
import academy.programming.MaxNumber;
import academy.programming.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
@ComponentScan(basePackages = "academy.programming")
public class GameConfig {

    // == fields ==
    @Value("${game.maxNumber:30}")
    private int maxNumber;

    @Value("${game.guessCount:6}")
    private int guessCount;

    @Value("${game.minNumber:5}")
    private int minNumber;

    // == bean metothods ==
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }

    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }
}
