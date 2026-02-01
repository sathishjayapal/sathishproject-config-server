package me.sathish.sathishprojectconfigserver.endpoints;

import java.util.Random;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("customConfigHealthIndicator")
public class CustomConfigHealthIndicator implements HealthIndicator {

    @Override
    public Health getHealth(boolean includeDetails) {
        Random random = new Random();
        int value = random.nextInt(100);
        if (value < 50) {
            return Health.down().withDetail("Error", "Random value is less than 50").build();
        }
        return Health.up().build();
    }

    @Override
    public Health health() {
        return getHealth(true);
    }
}
