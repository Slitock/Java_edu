package app;

import vehicles.Vehicle;
import vehicles.Car;
import vehicles.ElectricCar;

public class TestCar {
    public static void main(String[] args) {
        Vehicle car1 = new Car("Lada Vesta", "A123BC77", "Серый", 2021, "Иван Иванов", "INS-1001", "Бензиновый");
        Vehicle car2 = new ElectricCar("Tesla Model 3", "E777KX99", "Белый", 2023, "Алексей Смирнов", "INS-2002", 75.0);

        System.out.println("=== Исходное состояние (через полиморфные ссылки Vehicle) ===");
        System.out.println(car1.toString());
        System.out.println(car2.toString());

        System.out.println("\n=== Изменение свойств через сеттеры ===");
        car1.setColor("Черный");
        car1.setOwnerName("Петр Васильев");

        car2.setColor("Красный");
        car2.setInsuranceNumber("INS-9999");

        if (car2 instanceof ElectricCar) {
            ((ElectricCar) car2).setBatteryCapacity(82.0);
        }

        System.out.println("\n=== Состояние после изменений ===");
        System.out.println(car1);
        System.out.println(car2);
    }
}
