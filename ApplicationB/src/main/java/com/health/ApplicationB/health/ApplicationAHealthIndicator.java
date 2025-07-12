package com.health.ApplicationB.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component("ApplicationA")
public class ApplicationAHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8081/actuator/health").openConnection();
            connection.setConnectTimeout(1500);
            connection.setReadTimeout(1500);
            connection.setRequestMethod("GET");
            int code  = connection.getResponseCode();
            if(code == 200) {
                return Health.up().withDetail("Application A is in service", "Available").build();
            } else{
                return Health.outOfService().build();
            }
        } catch (Exception ignored) {
            return Health.outOfService().build();
        }


    }
}
