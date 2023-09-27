package com.fredsonchaves07.acme.inventory.grpc;

import com.fredsonchaves07.acme.inventory.model.*;
import com.fredsonchaves07.acme.inventory.repository.CarRepository;
import io.quarkus.grpc.GrpcService;
import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

import java.util.Optional;

@GrpcService
public class GrpcInventoryService implements InventoryService {

    @Inject
    CarRepository carRepository;

    @Override
    @Blocking
    public Uni<CarResponse> remove(RemoveCarRequest request) {
        Optional<Car> optionalCar = carRepository
                .findByLicensePlateNumberOptional(
                        request.getLicensePlateNumber());
        if (optionalCar.isPresent()) {
            Car removedCar = optionalCar.get();
            carRepository.delete(removedCar);
            return Uni.createFrom().item(CarResponse.newBuilder()
                    .setLicensePlateNumber(removedCar.getLicensePlateNumber())
                    .setManufacturer(removedCar.getManufacturer())
                    .setModel(removedCar.getModel())
                    .setId(removedCar.getId())
                    .build());
        }
        return Uni.createFrom().nullItem();
    }

    @Override
    @Blocking
    public Multi<CarResponse> add(Multi<InsertCarRequest> requests) {
        return requests
                .map(request -> {
                    Car car = new Car();
                    car.setLicensePlateNumber(request.getLicensePlateNumber());
                    car.setManufacturer(request.getManufacturer());
                    car.setModel(request.getModel());
                    return car;
                }).onItem().invoke(car -> {
                    Log.info("Persisting " + car);
                    QuarkusTransaction.run( () ->
                            carRepository.persist(car)
                    );
                }).map(car -> CarResponse.newBuilder()
                        .setLicensePlateNumber(car.getLicensePlateNumber())
                        .setManufacturer(car.getManufacturer())
                        .setModel(car.getModel())
                        .setId(car.getId())
                        .build());
    }
}
