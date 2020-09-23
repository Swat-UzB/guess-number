package academy.programming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants ==
    public static final String MAIN_MASSAGE = "game.main.message";
    public static final String WIN_MASSAGE = "game.win";
    public static final String LOST_MASSAGE = "game.lost";
    public static final String INVALID_RANGE = "game.invalid.range";
    public static final String FIRST_TIME = "game.begin";
    public static final String HIGHER = "game.higher";
    public static final String LOWER = "game.lower";
    public static final String REMAINING = "game.remaining";

    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructor ==
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == init ==
    @PostConstruct
    public void init() {
        log.info("game = {}", game);
    }

    // == public methods ==
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MASSAGE, game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return getMessage(WIN_MASSAGE, game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(LOST_MASSAGE, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_RANGE);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_TIME);
        } else {
            String direction = getMessage(LOWER);

            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }
            return  getMessage(REMAINING,direction, game.getRemainingGuesses());
        }
    }

    // == private methods ==
    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
