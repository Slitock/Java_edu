package vehicles;

public class ElectricCar extends Car {
    private double batteryCapacity;

    public ElectricCar(String ownerName, String insuranceNumber, double batteryCapacity) {
        super(ownerName, insuranceNumber, "Electric");
        this.batteryCapacity = batteryCapacity;
        
        this.engineType = "Electric";
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
