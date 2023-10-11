package acme.reservation;

import acme.reservation.inventory.Car;
import acme.reservation.inventory.GraphQLInventoryClient;
import acme.reservation.reservation.entity.Reservation;
import acme.reservation.rest.ReservationResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.DisabledOnIntegrationTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ReservationResourceTest {

    @TestHTTPEndpoint(ReservationResource.class)
    @TestHTTPResource
    URL reservationResource;

    @TestHTTPEndpoint(ReservationResource.class)
    @TestHTTPResource("availability")
    URL availability;

    @BeforeAll
    public static void setUp() {
        GraphQLInventoryClient mock = Mockito.mock(GraphQLInventoryClient.class);
        Car peugeot = new Car(1L, "ABC 123", "Peugeot", "406");
        Mockito.when(mock.allCars())
                .thenReturn(Uni.createFrom()
                        .item(List.of(peugeot)));
        QuarkusMock.installMockForType(mock,
                GraphQLInventoryClient.class);
    }

    @Test
    public void testReservationIds() {
        Reservation reservation = new Reservation();
        reservation.carId = 12345L;
        reservation.startDay = LocalDate
                .parse("2025-03-20");
        reservation.endDay = LocalDate
                .parse("2025-03-29");
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(reservation)
                .when()
                .post(reservationResource)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @DisabledOnIntegrationTest(forArtifactTypes = DisabledOnIntegrationTest.ArtifactType.NATIVE_BINARY)
    @Test
    public void testMakingAReservationAndCheckAvailability() {
        String startDate = "2022-01-01";
        String endDate = "2022-01-10";
        Car[] cars = RestAssured
                .given()
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when().get(availability)
                .then().statusCode(200)
                .extract().as(Car[].class);
        Car car = cars[0];
        Reservation reservation = new Reservation();
        reservation.carId = car.getId();
        reservation.startDay = LocalDate.parse(startDate);
        reservation.endDay = LocalDate.parse(endDate);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(reservation)
                .when().post(reservationResource)
                .then().statusCode(200)
                .body("carId", is(car.getId().intValue()));
        RestAssured
                .given()
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when().get(availability)
                .then().statusCode(200)
                .body("findAll { car -> car.id == " + car.getId() + "}", hasSize(0));
    }

}
