package br.com.letscode.starwarsnetwork;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@SpringBootApplication
public class StarWarsResistenceSocialNetworkApplication {

    private static final String ACCESS_URLS_MESSAGE_LOG =
            "\n\n Access URLs:\n----------------------------------------------------------\n\tExternal:\thttp://{}:{}{}/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config \n\tProfiles:\t{} \n\tStart time: {} \n\tRun time duration:\t{}\n----------------------------------------------------------\n";

    public static void main(String... args) {
        try {
            System.setProperty("spring.devtools.restart.enabled", "false");
            final SpringApplication app =
                    new SpringApplication(StarWarsResistenceSocialNetworkApplication.class);
            final Environment env = app.run(args).getEnvironment();
            final ProcessHandle.Info info = ProcessHandle.current().info();
            log.info(
                    ACCESS_URLS_MESSAGE_LOG,
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"),
                    Optional.ofNullable(env.getProperty("server.servlet.context-path")).orElse(""),
                    env.getActiveProfiles(),
                    info.startInstant().orElse(Instant.now()).toString(),
                    String.format(
                            "%sms%n",
                            info.totalCpuDuration().orElse(Duration.ofMillis(0)).toMillis()));
        } catch (Exception e) {
            log.error("Start Error.", e);
        }
    }
}
