package app;

import vehicles.Car;
import vehicles.ElectricCar;

public class Main {
    public static void main(String[] args) {
        Car gasCar = new Car("Евпатий Коловратыч", "G153UT", "Бензиновый");
        ElectricCar tesla = new ElectricCar("Акакий Рассулович", "E777KX", 75.5);

        System.out.println("=== Обычный автомобиль (Car) ===");
        System.out.println("Владелец: " + gasCar.getOwnerName());
        System.out.println("Номер страховки: " + gasCar.getInsuranceNumber());
        System.out.println("Тип двигателя: " + gasCar.getEngineType());

        System.out.println("\n=== Электромобиль (ElectricCar) ===");
        System.out.println("Владелец: " + tesla.getOwnerName());
        System.out.println("Номер страховки: " + tesla.getInsuranceNumber());
        System.out.println("Тип двигателя (унаследован): " + tesla.getEngineType());
        System.out.println("Емкость батареи: " + tesla.getBatteryCapacity() + " кВт⋅ч");

        System.out.println("\n=== Изменение данных через сеттеры ===");
        gasCar.setOwnerName("Иван Петров");
        tesla.setBatteryCapacity(82.0);

        System.out.println("Новый владелец Car: " + gasCar.getOwnerName());
        System.out.println("Новая емкость батареи Tesla: " + tesla.getBatteryCapacity() + " кВт⋅ч");

    }
}
