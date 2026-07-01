package me.sathish.sathishprojectconfigserver.endpoints;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("customConfigHealthIndicator")
public class CustomConfigHealthIndicator implements HealthIndicator {

    @Override
    public Health getHealth(boolean includeDetails) {
        return Health.up().withDetail("service", "sathish-config-server").build();
    }

    @Override
    public Health health() {
        return getHealth(true);
    }
}
