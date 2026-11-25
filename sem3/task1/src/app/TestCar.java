package app;

import vehicles.Vehicle;
import vehicles.Car;
import vehicles.ElectricCar;

public class TestCar {
    public static void main(String[] args) {
        Vehicle car = new Car("Lada Vesta", "A123BC77", "Серый", 2021, "Иван Иванов", "INS-1001");
        Vehicle electricCar = new ElectricCar("Tesla Model 3", "E777KX99", "Белый", 2023, "Алексей Смирнов", "INS-2002", 75.0);

        System.out.println("=== Исходное состояние (через полиморфные ссылки Vehicle) ===");
        System.out.println(car);
        System.out.println(electricCar);

        System.out.println("\n=== Изменение года выпуска и имени владельца ===");
        car.setYear(2022);
        car.setOwnerName("Петр Васильев");

        System.out.println("\n=== Изменение страхового номера у электромобиля ===");
        electricCar.setInsuranceNumber("INS-9999");

        System.out.println("\n=== Состояние после изменений ===");
        System.out.println(car);
        System.out.println(electricCar);

        System.out.println("\n=== Информация о батарее электромобиля ===");
        if (electricCar instanceof ElectricCar) {
            System.out.printf("Емкость батареи: %.1f кВт⋅ч%n", ((ElectricCar) electricCar).getBatteryCapacity());
        }
    }
}
