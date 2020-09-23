package academy.programming;

import academy.programming.config.GameConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        log.info(" main method runned ");

        SpringApplication.run(Main.class, args);
    }
}
