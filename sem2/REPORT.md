# Отчёт по практической работе №2

**Тема:** объекты и классы: модификаторы доступа, инкапсуляция, наследование и полиморфизм

**Проект:** `seminars/sem2`

**Среда выполнения:** OpenJDK 25.0.4.1, Linux

## 1. Цель работы

Изучить модификаторы доступа Java, организацию классов по пакетам,
инкапсуляцию полей, наследование, вызов родительского конструктора,
переопределение методов и полиморфизм.

## 2. Постановка задачи

### Часть 1

1. Создать пакет `vehicles` с классами `Car` и `ElectricCar` и пакет `app`
   с классом, содержащим `main`. В `Car` объявить приватные поля `ownerName`
   и `insuranceNumber`, защищённое поле `engineType` и методы доступа.
2. Создать `ElectricCar`, наследующий `Car`, с полем `batteryCapacity`.
   В конструкторе задать тип двигателя `"Electric"` через унаследованное
   поле. Продемонстрировать работу инкапсуляции и наследования.

### Часть 2

1. Добавить абстрактный класс `Vehicle` с общими полями (`model`, `license`,
   `color`, `year`, `ownerName`, `insuranceNumber`, `engineType`),
   геттерами, сеттерами, `toString()` и абстрактным методом `vehicleType()`.
   Перестроить `Car` и `ElectricCar` на его основе.
2. В тестовом классе работать с объектами через ссылки типа `Vehicle`,
   менять свойства сеттерами и выводить состояние через `toString()`.

## 3. Структура проекта

| Файл | Назначение |
|---|---|
| `task1/src/vehicles/Car.java` | приватные поля, protected `engineType`, доступ через методы |
| `task1/src/vehicles/ElectricCar.java` | наследник `Car` с ёмкостью батареи |
| `task1/src/app/Main.java` | проверка инкапсуляции и наследования |
| `task2/src/vehicles/Vehicle.java` | абстрактный базовый класс |
| `task2/src/vehicles/Car.java` | реализация `vehicleType()` |
| `task2/src/vehicles/ElectricCar.java` | наследник `Car`, своя `vehicleType()` и `toString()` |
| `task2/src/app/TestCar.java` | полиморфные ссылки `Vehicle` |

## 4. Реализация

### 4.1. Модификаторы доступа

В Java у члена класса четыре уровня доступа:

| Модификатор | Кто видит |
|---|---|
| `private` | только сам класс |
| без модификатора (package-private) | классы того же пакета |
| `protected` | класс, пакет и наследники |
| `public` | все классы |

В `Car` из части 1 это разделение видно явно:

```java
public class Car {
    private String ownerName;
    private String insuranceNumber;
    protected String engineType;
```

`ownerName` и `insuranceNumber` доступны только через геттеры и сеттеры.
`engineType` объявлен `protected`, поэтому наследник `ElectricCar` может
обращаться к нему напрямую:

```java
public ElectricCar(String ownerName, String insuranceNumber, double batteryCapacity) {
    super(ownerName, insuranceNumber, "Electric");
    this.batteryCapacity = batteryCapacity;

    this.engineType = "Electric";
}
```

Присваивание `engineType` после `super(...)` избыточно, потому что значение
уже передано в родительский конструктор, но оно демонстрирует доступ
наследника к защищённому полю.

### 4.2. Пакеты

Классы разложены по каталогам, и каждый каталог соответствует пакету:

```java
package vehicles;
package app;
```

Пакет объявляется первой строкой файла. Классы из другого пакета требуют
импорта:

```java
import vehicles.Car;
import vehicles.ElectricCar;
```

Один каталог — один пакет; это упрощает навигацию и управление доступом
package-private.

### 4.3. Инкапсуляция

Инкапсуляция — это скрытие внутреннего состояния и доступ к нему через
методы. Поля объявлены `private`, а изменение идёт через сеттеры:

```java
public String getOwnerName() {
    return ownerName;
}

public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
}
```

Геттер (accessor) возвращает значение, сеттер (mutator) изменяет. Даже если
сейчас сеттер просто присваивает значение, точка контроля уже существует:
позже в него можно добавить проверку, не меняя вызывающий код.

### 4.4. Абстрактный класс `Vehicle`

Во второй части общие поля вынесены в базовый класс:

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

Абстрактный метод не имеет тела: каждый конкретный тип транспорта обязан
сам сообщить, что он такое. Создать `new Vehicle(...)` нельзя — класс не
завершён.

`toString()` в `Vehicle` использует `vehicleType()`:

```java
return String.format(
    "Тип: %s | Модель: %s | Номер: %s | Цвет: %s | Год: %d | Двигатель: %s | Владелец: %s | Страховка: %s",
    vehicleType(), model, license, color, year, engineType, ownerName, insuranceNumber
);
```

Базовый класс не знает, какой объект перед ним, но вызов `vehicleType()`
попадёт в реализацию подкласса.

### 4.5. Наследование и полиморфизм

```java
public class Car extends Vehicle {
    public Car(String model, String license, String color, int year,
               String ownerName, String insuranceNumber, String engineType) {
        super(model, license, color, year, ownerName, insuranceNumber, engineType);
    }

    @Override
    public String vehicleType() {
        return "Car";
    }
}
```

`ElectricCar` наследует `Car`, добавляет батарею и переопределяет
`vehicleType()` и `toString()`:

```java
@Override
public String vehicleType() {
    return "Electric Car";
}

@Override
public String toString() {
    return super.toString() + String.format(" | Батарея: %.1f кВт⋅ч", batteryCapacity);
}
```

В `TestCar` объекты создаются как `Vehicle`:

```java
Vehicle car1 = new Car(...);
Vehicle car2 = new ElectricCar(...);
```

Тип переменной — `Vehicle`, реальный объект — `Car` или `ElectricCar`.
Вызов `car2.toString()` выбирает реализацию по реальному классу объекта.
Это динамическая диспетчеризация (dynamic dispatch), то есть полиморфизм.

Для доступа к методу, которого нет в `Vehicle`, применяется проверка типа:

```java
if (car2 instanceof ElectricCar) {
    ((ElectricCar) car2).setBatteryCapacity(82.0);
}
```

`instanceof` проверяет реальный класс, приведение `(ElectricCar)` открывает
доступ к методам наследника.

## 5. Результаты работы

### 5.1. Часть 1: инкапсуляция и наследование

```
=== Обычный автомобиль (Car) ===
Владелец: Евпатий Коловратыч
Номер страховки: G153UT
Тип двигателя: Бензиновый

=== Электромобиль (ElectricCar) ===
Владелец: Акакий Рассулович
Номер страховки: E777KX
Тип двигателя (унаследован): Electric
Емкость батареи: 75.5 кВт⋅ч

=== Изменение данных через сеттеры ===
Новый владелец Car: Шизойд Парфентиевич
Новая емкость батареи Tesla: 82.0 кВт⋅ч
```

Поля не читаются напрямую: доступ идёт через методы. `ElectricCar`
показывает унаследованный `engineType` и собственное поле
`batteryCapacity`.

### 5.2. Часть 2: полиморфизм

```
=== Исходное состояние (через полиморфные ссылки Vehicle) ===
Тип: Car | Модель: Lada Vesta | Номер: A123BC77 | Цвет: Серый | Год: 2021 | Двигатель: Бензиновый | Владелец: Иван Иванов | Страховка: INS-1001
Тип: Electric Car | Модель: Tesla Model 3 | Номер: E777KX99 | Цвет: Белый | Год: 2023 | Двигатель: Electric | Владелец: Алексей Смирнов | Страховка: INS-2002 | Батарея: 75.0 кВт⋅ч

=== Состояние после изменений ===
Тип: Car | Модель: Lada Vesta | Номер: A123BC77 | Цвет: Черный | Год: 2021 | Двигатель: Бензиновый | Владелец: Петр Васильев | Страховка: INS-1001
Тип: Electric Car | Модель: Tesla Model 3 | Номер: E777KX99 | Цвет: Красный | Год: 2023 | Двигатель: Electric | Владелец: Алексей Смирнов | Страховка: INS-9999 | Батарея: 82.0 кВт⋅ч
```

В одном выводе видны разные реализации `vehicleType()` и `toString()` при
одинаковом типе ссылки `Vehicle`. После изменения свойств через сеттеры
объекты сохраняют новое состояние.

## 6. Выводы

- `private`, `protected`, package-private и `public` задают разные границы
  доступа; выбор модификатора — часть проектирования, а не формальность.
- Пакеты организуют классы и работают вместе с package-private доступом.
- Инкапсуляция сводит изменение состояния в контролируемые методы.
- Наследование позволяет вынести общие поля и поведение в базовый класс,
  а `super(...)` инициализирует родительскую часть объекта.
- Абстрактный метод обязывает подклассы реализовать различающееся поведение.
- Полиморфизм даёт единый код для разных типов: `Vehicle` в переменной,
  конкретное поведение — из объекта.

## 7. Запуск

```bash
cd seminars/sem2/task1
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Main

cd ../task2
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.TestCar
```
