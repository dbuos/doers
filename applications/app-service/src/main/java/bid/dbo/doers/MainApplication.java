package bid.dbo.doers;

import org.reactivecommons.async.impl.config.annotations.EnableDomainEventBus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDomainEventBus
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }


    @Bean
    public CommandLineRunner lineRunner() {
        return args -> {
            MemUtils.printInfo();
        };
    }
}
