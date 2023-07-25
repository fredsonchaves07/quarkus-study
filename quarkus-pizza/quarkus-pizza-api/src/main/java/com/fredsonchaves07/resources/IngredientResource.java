package com.fredsonchaves07.resources;

import com.fredsonchaves07.entities.Ingredient;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/ingredient")
public class IngredientResource {

    @GET
    @Produces(APPLICATION_JSON)
    @Transactional
    public List<Ingredient> listAll() {
        return Ingredient.listAll();
    }

    @Transactional
    public void init(@Observes StartupEvent event) {
        Ingredient ingredient = new Ingredient("Cheese");
        ingredient.persist();
    }
}
