package academy.programming.controller;

import academy.programming.service.GameService;
import academy.programming.util.AttributeNames;
import academy.programming.util.Mappings;
import academy.programming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {

    // == fields ==
    private final GameService gameService;

    // == constructor ==
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    // == request methods ==
    @GetMapping(Mappings.PLAY)
    public String play(Model model) {
        model.addAttribute(AttributeNames.MAIN_MESSAGE, gameService.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, gameService.getResultMessage());
        log.info("model = {}", model);

        if (gameService.isGameOver()) {
            log.info("is won = {}", gameService.isGameOver());
            return ViewNames.GAME_OVER;
        }
        return ViewNames.PLAY;
    }

    @PostMapping(Mappings.PLAY)
    public String processMessage(@RequestParam Integer guess) {
        log.info("guess = {}", guess);
        gameService.checkGuess(guess);
        return Mappings.REDIRECT_PLAY;
    }

    @GetMapping(Mappings.RESTART)
    public String restart() {
        gameService.reset();
        return Mappings.REDIRECT_PLAY;
    }
}
