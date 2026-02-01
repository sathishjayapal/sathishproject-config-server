package me.sathish.sathishprojectconfigserver.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "serving-projects")
public class ServingProjects {
    @ReadOperation
    public String getProjects() {
        return "Projects";
    }
}
