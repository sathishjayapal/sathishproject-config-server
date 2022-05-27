package me.sathish.sathishprojectconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SathishProjectsConfigServerApplication {
    public static void main(String[] args) {
        System.out.println("Config server running for all Sathish Projects");
        SpringApplication.run(SathishProjectsConfigServerApplication.class, args);
    }

}
