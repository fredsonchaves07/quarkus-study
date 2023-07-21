package com.github.fredsonchaves07.resources;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/health")
public class HealthCheckResource {

    @Inject
    DataSource dataSource;

    @Inject
    EntityManager entityManager;

    @GET
    @Produces(APPLICATION_JSON)
    public Map<String, String> get() {
        if (!isDBHealthy())
            return Map.of("status", "Error on database connection");
        if (!isEntityHealthy())
            return Map.of("status", "Connection is close");
        return Map.of("status", "Api running");
    }

    private boolean isDBHealthy() {
        try (Connection connection=  dataSource.getConnection()) {
            if (!connection.isValid(15))
                throw new SQLException();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private boolean isEntityHealthy() {
        return entityManager.isOpen();
    }
}
