package edu.wctc.dice;

import edu.wctc.dice.iface.DieRoller;
import edu.wctc.dice.iface.GameInput;
import edu.wctc.dice.iface.GameOutput;
import edu.wctc.dice.impl.*;
import edu.wctc.dice.impl.D12;
import edu.wctc.dice.impl.D20;
import edu.wctc.dice.impl.D6;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@ComponentScan("edu.wctc.dice")
public class AppConfig {
    @Bean
    public GameOutput gameOutput() {
//        return new ConsoleOutput();
        return new PopupOutput();
    }

    @Bean
    public GameInput gameInput() {
//        return new ConsoleInput();
        return new PopupInput();
    }

    @Bean
    public DieRoller dieRoller() {
        DieRoller[] rolls = { new D6(), new D12(), new D20() };
        Random random = new Random();
        return rolls[random.nextInt(rolls.length) + 1];
    }
}
