package acme.reservation.inventory;

public class Car {

    private Long id;

    private String licensePlateNumber;

    private String manufacturer;

    private String model;

    public Car() {}

    public Car(Long id, String licensePlateNumber, String manufacturer, String model) {
        this.id = id;
        this.licensePlateNumber = licensePlateNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }
}
