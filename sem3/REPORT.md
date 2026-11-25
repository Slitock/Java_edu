# Отчёт по практической работе №3

**Тема:** абстрактные классы, интерфейсы и пакеты

**Проект:** `seminars/sem3`

**Среда выполнения:** OpenJDK 25.0.4.1, Linux

## 1. Цель работы

Освоить абстрактные классы и интерфейсы, научиться разделять общий контракт и
конкретную реализацию, организовывать код по пакетам и использовать
полиморфизм для работы с разными типами через общий интерфейс.

## 2. Постановка задачи

### Часть 1. Транспортные средства

1. Создать пакет `vehicles` для `Vehicle`, `Car`, `ElectricCar` и интерфейса
   `ElectricVehicle`, пакет `app` — для тестового класса.
2. `Vehicle` — абстрактный класс с полями `model`, `license`, `color`,
   `year`, `ownerName`, `insuranceNumber`, `engineType`, геттерами,
   сеттерами, `toString()` и абстрактным методом `vehicleType()`.
3. `Car` наследует `Vehicle`, задаёт тип двигателя `"Combustion"` и
   возвращает `"Car"` из `vehicleType()`.
4. `ElectricVehicle` — интерфейс с методами `getBatteryCapacity()` и
   `setBatteryCapacity()`.
5. `ElectricCar` наследует `Car`, реализует `ElectricVehicle`, хранит
   ёмкость батареи и задаёт тип двигателя `"Electric"`.
6. Проверить создание объектов, изменение года, владельца и страхового
   номера, получить ёмкость батареи и вывести объекты через `toString()`.

### Часть 2. Проект Bike

1. Добавить интерфейс `BikeParts` с неизменяемым названием производителя и
   методом `printDescription()`.
2. Создать интерфейсы `MountainParts` и `RoadParts` с константами и методами
   доступа к специфическим полям.
3. Подключить интерфейсы к классам `Bike`, `MountainBike`, `RoadBike`.
4. Изменить высоту подседельного штыря `bike1` с 22 на 20 и вывести объект.

## 3. Структура проекта

| Файл | Назначение |
|---|---|
| `task1/src/vehicles/Vehicle.java` | абстрактный базовый класс |
| `task1/src/vehicles/Car.java` | обычный автомобиль |
| `task1/src/vehicles/ElectricVehicle.java` | интерфейс электромобиля |
| `task1/src/vehicles/ElectricCar.java` | электромобиль |
| `task1/src/app/TestCar.java` | проверка части 1 |
| `task2/src/bikeproject/BikeParts.java` | общий интерфейс велосипеда |
| `task2/src/bikeproject/MountainParts.java` | интерфейс горного велосипеда |
| `task2/src/bikeproject/RoadParts.java` | интерфейс шоссейного велосипеда |
| `task2/src/bikeproject/Bike.java` | базовый класс |
| `task2/src/bikeproject/MountainBike.java` | горный велосипед |
| `task2/src/bikeproject/RoadBike.java` | шоссейный велосипед |
| `task2/src/bikeproject/BikeDriver.java` | демонстрация проекта |

## 4. Реализация

### 4.1. Абстрактный класс `Vehicle`

```java
public abstract class Vehicle {
    private String model;
    private String license;
    private String color;
    private int year;
    private String ownerName;
    private String insuranceNumber;

    protected String engineType;

    public abstract String vehicleType();
```

Поля закрыты, кроме `engineType`: он объявлен `protected`, чтобы наследник
мог задать тип двигателя напрямую. Абстрактный метод `vehicleType()` не
имеет тела — каждый конкретный класс обязан вернуть своё название.

`toString()` собирает описание из всех полей и вызывает `vehicleType()`:

```java
return String.format(
    "Тип: %s | Модель: %s | Номер: %s | Цвет: %s | Год: %d | Двигатель: %s | Владелец: %s | Страховка: %s",
    vehicleType(), model, license, color, year, engineType, ownerName, insuranceNumber
);
```

### 4.2. Наследование: `Car` и `ElectricCar`

```java
public class Car extends Vehicle {
    public Car(String model, String license, String color, int year,
               String ownerName, String insuranceNumber) {
        super(model, license, color, year, ownerName, insuranceNumber, "Combustion");
    }

    @Override
    public String vehicleType() {
        return "Car";
    }
}
```

```java
public class ElectricCar extends Car implements ElectricVehicle {
    private double batteryCapacity;

    public ElectricCar(String model, String license, String color, int year,
                       String ownerName, String insuranceNumber, double batteryCapacity) {
        super(model, license, color, year, ownerName, insuranceNumber);
        this.batteryCapacity = batteryCapacity;
        this.engineType = "Electric";
    }
```

`ElectricCar` получает поля и методы `Car` и `Vehicle`, добавляет батарею и
переопределяет `vehicleType()` на `"Electric Car"`. `toString()` вызывает
родительскую версию и дописывает ёмкость:

```java
@Override
public String toString() {
    return super.toString() + String.format(" | Батарея: %.1f кВт⋅ч", batteryCapacity);
}
```

### 4.3. Интерфейс `ElectricVehicle`

```java
public interface ElectricVehicle {
    double getBatteryCapacity();
    void setBatteryCapacity(double batteryCapacity);
}
```

Интерфейс описывает только контракт: что объект умеет, но не как он это
делает. Класс может наследовать один класс, но реализовывать несколько
интерфейсов — поэтому `ElectricCar` одновременно является `Car` и
`ElectricVehicle`.

### 4.4. Проверка полиморфизма

```java
Vehicle car = new Car(...);
Vehicle electricCar = new ElectricCar(...);
```

Обе переменные имеют тип `Vehicle`, но методы `vehicleType()` и `toString()`
вызываются из реальных классов. Для доступа к методу интерфейса, которого
нет в `Vehicle`, используется приведение:

```java
if (electricCar instanceof ElectricCar) {
    System.out.printf("Емкость батареи: %.1f кВт⋅ч%n",
            ((ElectricCar) electricCar).getBatteryCapacity());
}
```

### 4.5. Интерфейсы проекта Bike

```java
public interface BikeParts {
    String MAKE = "Oracle Cycles";

    void printDescription();
}
```

Поле интерфейса неявно является `public static final`, поэтому `MAKE` — это
константа, общая для всех реализаций, а не поле объекта.

```java
public interface MountainParts {
    String TERRAIN = "off_road";

    String getSuspension();
    void setSuspension(String newValue);
    String getType();
    void setType(String newValue);
}
```

```java
public interface RoadParts {
    String terrain = "track_racing";

    int getTyreWidth();
    void setTyreWidth(String newValue);
    int getPostHeight();
    void setPostHeight(String newValue);
}
```

`Bike` реализует `BikeParts`, `MountainBike` — `MountainParts`, `RoadBike` —
`RoadParts`. Каждый класс обязан реализовать все методы своего интерфейса.
Сеттеры `RoadBike` принимают строку и преобразуют её:

```java
@Override
public void setTyreWidth(String newValue) {
    this.tyreWidth = Integer.parseInt(newValue);
}
```

Это соответствует сигнатуре интерфейса; преобразование типа выполняется
внутри метода.

`MountainBike` и `RoadBike` дополняют вывод через `super.printDescription()`:
сначала печатается общая часть, затем специфическая.

## 5. Результаты работы

### 5.1. Транспортные средства

```
=== Исходное состояние (через полиморфные ссылки Vehicle) ===
Тип: Car | Модель: Lada Vesta | Номер: A123BC77 | Цвет: Серый | Год: 2021 | Двигатель: Combustion | Владелец: Иван Иванов | Страховка: INS-1001
Тип: Electric Car | Модель: Tesla Model 3 | Номер: E777KX99 | Цвет: Белый | Год: 2023 | Двигатель: Electric | Владелец: Алексей Смирнов | Страховка: INS-2002 | Батарея: 75.0 кВт⋅ч

=== Состояние после изменений ===
Тип: Car | Модель: Lada Vesta | Номер: A123BC77 | Цвет: Серый | Год: 2022 | Двигатель: Combustion | Владелец: Петр Васильев | Страховка: INS-1001
Тип: Electric Car | Модель: Tesla Model 3 | Номер: E777KX99 | Цвет: Белый | Год: 2023 | Двигатель: Electric | Владелец: Алексей Смирнов | Страховка: INS-9999 | Батарея: 75.0 кВт⋅ч

=== Информация о батарее электромобиля ===
Емкость батареи: 75.0 кВт⋅ч
```

Изменения года, владельца и страхового номера прошли через сеттеры.
`vehicleType()` вернул разные значения для одинакового типа ссылки.

### 5.2. Проект Bike

```
Oracle Cycles
This bike has drop handlebars on a racing frame with 19 gears.
It has a razor seat with tread less tyres.
This Roadbike bike has 20mm tyres and a post height of 22.

Oracle Cycles
This bike has drop handlebars on a tourer frame with 14 gears.
It has a comfort seat with semi-grip tyres.
This Roadbike bike has 25mm tyres and a post height of 18.

Oracle Cycles
This bike has Bull Horn handlebars on a Hardtail frame with 27 gears.
It has a dropper seat with Maxxis tyres.
This mountain bike is a Pro bike and has a RockShox XC32 suspension and a frame size of 19inches.

Oracle Cycles
This bike has null handlebars on a null frame with 0 gears.
It has a null seat with null tyres.

=== Post height of bike1 changed to 20 ===
...
This Roadbike bike has 20mm tyres and a post height of 20.
```

У `bike4` создан объект через конструктор по умолчанию `Bike()`, который не
задаёт поля, поэтому в выводе видны `null` и `0`. Это поведение исходного
проекта: конструктор без аргументов оставлен пустым.

### 5.3. Ответы на вопросы к проекту Bike

| Вопрос | Ответ |
|---|---|
| Пример примитива в поле класса | `private int numGears` в `Bike`; примитив хранит значение в объекте |
| Где происходит конкатенация строк | в `Bike.printDescription()` при сборке `"\n" + MAKE + ...` |
| Имена созданных объектов | `bike1`, `bike2` (`RoadBike`), `bike3` (`MountainBike`), `bike4` (`Bike`) |
| Сколько конструкторов | `Bike` — 2, `MountainBike` — 2, `RoadBike` — 3, `BikeDriver` — конструктор по умолчанию |
| Супер- и подклассы | `Bike` — суперкласс; `MountainBike` и `RoadBike` — подклассы |
| Значения стандартного `RoadBike` | `drop`, `racing`, `tread less`, `razor`, 19 передач, ширина 20, штырь 22 |
| Значения стандартного `MountainBike` | `Bull Horn`, `Hardtail`, `Maxxis`, `dropper`, 27, `RockShox XC32`, `Pro`, размер 19 |

## 6. Выводы

- Абстрактный класс задаёт общее состояние и обязательный контракт, но не
  может быть создан напрямую.
- Интерфейс описывает возможность объекта и позволяет реализовать несколько
  контрактов одновременно.
- `protected` открывает поле наследникам, не делая его публичным.
- Полиморфизм позволяет работать с `Car` и `ElectricCar` через `Vehicle`,
  а конкретное поведение определяется реальным классом.
- `super.toString()` и `super.printDescription()` позволяют переиспользовать
  родительский вывод и дополнять его.
- Константы интерфейса (`MAKE`, `TERRAIN`, `terrain`) неявно являются
  `public static final` и общими для всех реализаций.

## 7. Запуск

```bash
cd seminars/sem3/task1
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.TestCar

cd ../task2
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out bikeproject.BikeDriver
```
