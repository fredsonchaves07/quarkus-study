package com.fredsonchaves07.acme.inventory.repository;

import com.fredsonchaves07.acme.inventory.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public Optional<Car> findByLicensePlateNumberOptional(
            String licensePlateNumber) {
        return find("licensePlateNumber", licensePlateNumber)
                .firstResultOptional();
    }
}
