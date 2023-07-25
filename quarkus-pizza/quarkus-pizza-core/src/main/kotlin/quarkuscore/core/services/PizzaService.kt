package quarkuscore.core.services

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PizzaService {

    fun getPizza(): String {
        return "Pizza"
    }
}