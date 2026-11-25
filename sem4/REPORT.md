# Отчёт по практической работе №4

**Тема:** ООП в Java. Исключения

**Проект:** `seminars/sem4`

**Среда выполнения:** OpenJDK 25.0.4.1, Linux

## 1. Цель работы

Закрепить механизмы объектно-ориентированного программирования Java
(наследование, абстрактные классы, интерфейсы, полиморфизм, сравнение объектов)
и научиться применять исключения для защиты инвариантов объектов.

## 2. Постановка задачи

### Часть A. Иерархия геометрических фигур

1. Реализовать класс `Triangle`, наследующий `GeometricObject`: стороны
   `side1`, `side2`, `side3`, конструкторы, геттеры, площадь по формуле Герона,
   периметр и `toString()`.
2. Создать проверяемое исключение `IllegalTriangleException`; конструктор
   треугольника должен бросать его, если стороны не удовлетворяют неравенству
   треугольника.
3. Сделать `GeometricObject` реализующим `Comparable<GeometricObject>`,
   добавить статический метод `max()` и класс `ComparableCircle`.
4. Создать интерфейс `Colorable` с методом `howToColor()`, класс
   `Square extends GeometricObject implements Colorable` и программу, которая
   хранит фигуры в массиве `GeometricObject[]` и вызывает `howToColor()`
   только у объектов, реализующих интерфейс.

### Часть B. Обработка исключений

1. С помощью массивов `months` и `dom` вывести месяц и количество дней по
   введённому номеру 1–12. Недопустимое число обработать перехватом
   `ArrayIndexOutOfBoundsException`, нецелый ввод — предотвратить до чтения.
2. Для февраля запрашивать год, определять високосность и выводить 28 или
   29 дней.

## 3. Структура проекта

| Файл | Назначение |
|---|---|
| `src/geometry/GeometricObject.java` | абстрактный базовый класс, `Comparable`, `max()` |
| `src/geometry/Circle.java` | круг |
| `src/geometry/Rectangle.java` | прямоугольник |
| `src/geometry/Triangle.java` | треугольник, формула Герона, проверка сторон |
| `src/geometry/IllegalTriangleException.java` | проверяемое исключение |
| `src/geometry/ComparableCircle.java` | круг, реализующий `Comparable` |
| `src/geometry/Colorable.java` | интерфейс раскрашиваемой фигуры |
| `src/geometry/Square.java` | квадрат, реализует `Colorable` |
| `src/app/TestTriangle.java` | задания A1–A2 |
| `src/app/TestMax.java` | задание A3, поиск максимума |
| `src/app/TestComparableCircle.java` | задание A3, `compareTo()` |
| `src/app/TestColorable.java` | задание A4 |
| `src/app/TestMonths.java` | задания B1–B2 |

## 4. Реализация

### 4.1. Базовый класс `GeometricObject`

Класс объявлен абстрактным и содержит общее состояние всех фигур: цвет и
признак заливки.

```java
public abstract class GeometricObject implements Comparable<GeometricObject> {
    private String color = "белый";
    private boolean filled;

    public GeometricObject() {
    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public abstract double getArea();
```

Метод `getArea()` не имеет реализации: формула площади зависит от конкретной
фигуры. Абстрактный метод обязывает каждый конкретный подкласс его
реализовать, иначе код не скомпилируется. Поля закрыты (`private`), доступ
идёт через геттеры и сеттеры — это инкапсуляция.

`toString()` базового класса выводит общую часть, а `Circle`, `Rectangle` и
`Square` дополняют её своими полями через `super.toString()`. `Triangle`
печатает только стороны — так требует задание.

### 4.2. Наследование: `Circle`, `Rectangle`, `Triangle`

Подклассы переопределяют `getArea()` и добавляют собственный `getPerimeter()`.
Конструкторы с цветом и заливкой вызывают `super(color, filled)` — это должна
быть первая инструкция конструктора.

| Класс | Площадь | Периметр |
|---|---|---|
| `Circle` | $\pi r^2$ | $2\pi r$ |
| `Rectangle` | $wh$ | $2(w+h)$ |
| `Triangle` | формула Герона | $a+b+c$ |

Формула Герона в `Triangle`:

```java
double s = (side1 + side2 + side3) / 2;
return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
```

### 4.3. Проверяемое исключение `IllegalTriangleException`

```java
public class IllegalTriangleException extends Exception {
    public IllegalTriangleException(String message) {
        super(message);
    }
}
```

Класс наследуется от `Exception`, поэтому исключение проверяемое (checked):
компилятор требует либо обработать его, либо объявить в сигнатуре метода.
Конструктор треугольника объявляет `throws` и проверяет неравенство
треугольника:

```java
public Triangle(double side1, double side2, double side3)
        throws IllegalTriangleException {
    if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1) {
        throw new IllegalTriangleException(
                "Сумма любых двух сторон должна быть больше длины третьей стороны");
    }
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
}
```

Вызывающий код в `TestTriangle` обрабатывает исключение через `try-catch`:

```java
try {
    Triangle triangle = new Triangle(side1, side2, side3);
    ...
} catch (IllegalTriangleException e) {
    System.out.println("Ошибка: " + e.getMessage());
}
```

Так объект не может оказаться в недопустимом состоянии: треугольник со
сторонами `1, 2, 5` не будет создан.

### 4.4. Сравнение фигур

`GeometricObject` реализует `Comparable<GeometricObject>` и сравнивает фигуры
по площади:

```java
@Override
public int compareTo(GeometricObject other) {
    return Double.compare(this.getArea(), other.getArea());
}

public static GeometricObject max(GeometricObject first, GeometricObject second) {
    return (first.compareTo(second) >= 0) ? first : second;
}
```

`Double.compare` используется вместо вычитания площадей, чтобы корректно
работать с дробными значениями и избежать потери точности. Метод `max()`
принимает любой подкласс `GeometricObject` и благодаря полиморфизму вызывает
нужную реализацию `getArea()`.

`ComparableCircle extends Circle implements Comparable<GeometricObject>` не
переопределяет `compareTo()`: метод наследуется от `GeometricObject` и
сравнивает круги по площади, что эквивалентно сравнению радиусов.

### 4.5. Интерфейс `Colorable` и класс `Square`

```java
public interface Colorable {
    void howToColor();
}
```

Интерфейс задаёт только контракт. `Square` реализует его и дополнительно
проверяет сторону:

```java
public void setSide(double side) {
    if (side <= 0) {
        throw new IllegalArgumentException("Сторона квадрата должна быть больше 0");
    }
    this.side = side;
}
```

`IllegalArgumentException` — unchecked-исключение (наследник
`RuntimeException`), поэтому `throws` не требуется; это ошибка аргумента.

В `TestColorable` фигуры разных классов хранятся в одном массиве. Проверка
`instanceof` позволяет вызвать `howToColor()` только у тех объектов, которые
реализуют интерфейс:

```java
for (GeometricObject figure : figures) {
    System.out.printf("Площадь: %.2f | %s%n", figure.getArea(), figure);
    if (figure instanceof Colorable) {
        ((Colorable) figure).howToColor();
    }
}
```

Метод `main` объявляет `throws IllegalTriangleException`, потому что внутри
создаётся треугольник со сторонами `3, 4, 5`.

### 4.6. Задания B1–B2: массивы месяцев

```java
String[] months = {"январь", "февраль", ..., "декабрь"};
int[] dom = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
```

Номер месяца читается только после проверки `hasNextInt()`, что предотвращает
исключение `InputMismatchException` при вводе строки или дробного числа.
Доступ к массиву выполняется внутри `try`, а выход за границы (`0`, `13`,
отрицательные значения) перехватывается:

```java
try {
    String name = months[month - 1];
    int days = dom[month - 1];
    ...
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Недопустимое число");
}
```

`ArrayIndexOutOfBoundsException` — unchecked-исключение, но в задании
требуется его явный перехват.

Для февраля дополнительно запрашивается год:

```java
private static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
}
```

Год високосный, если делится на 4 и не делится на 100 либо делится на 400.
Поэтому 2024 и 2000 — високосные, а 1900 — нет.

## 5. Результаты работы

### 5.1. Треугольник и исключение

```
Введите три стороны треугольника: 3 4 5
Введите цвет: синий
Треугольник закрашен? (true/false): true

=== Треугольник ===
Треугольник: сторона1 = 3.0 сторона2 = 4.0 сторона3 = 5.0
Площадь: 6.00
Периметр: 12.00
Цвет: синий
Заливка: true

=== Проверка IllegalTriangleException на сторонах 1, 2, 5 ===
Исключение IllegalTriangleException: Сумма любых двух сторон должна быть больше длины третьей стороны
```

### 5.2. Поиск наибольшей фигуры

```
=== Поиск наибольшего из двух кругов ===
Круг 1: ... | Радиус: 3.00 | Площадь: 28.27 | Периметр: 18.85
Круг 2: ... | Радиус: 5.00 | Площадь: 78.54 | Периметр: 31.42
Наибольший: ... | Радиус: 5.00 | Площадь: 78.54 | Периметр: 31.42

=== Поиск наибольшего из двух прямоугольников ===
Прямоугольник 1: ... | Ширина: 2.00 | Высота: 4.00 | Площадь: 8.00 | Периметр: 12.00
Прямоугольник 2: ... | Ширина: 3.00 | Высота: 3.00 | Площадь: 9.00 | Периметр: 12.00
Наибольший: ... | Ширина: 3.00 | Высота: 3.00 | Площадь: 9.00 | Периметр: 12.00
```

### 5.3. `ComparableCircle`

```
=== Сравнение двух ComparableCircle ===
Круг 1: ... | Радиус: 4.00 | Площадь: 50.27
Круг 2: ... | Радиус: 6.50 | Площадь: 132.73
Наибольший: круг 2

=== Сравнение круга и прямоугольника через compareTo() ===
Круг: ... | Радиус: 3.00 | Площадь: 28.27
Прямоугольник: ... | Ширина: 2.00 | Высота: 5.00 | Площадь: 10.00
Наибольшая фигура: круг
```

### 5.4. Массив фигур и `Colorable`

```
=== Массив геометрических фигур ===
Площадь: 4.00 | ... Сторона: 2.00 | Площадь: 4.00 | Периметр: 8.00
Раскрасьте все четыре стороны.
Площадь: 28.27 | ... Радиус: 3.00 | Площадь: 28.27 | Периметр: 18.85
Площадь: 8.00 | ... Ширина: 4.00 | Высота: 2.00 | Площадь: 8.00 | Периметр: 12.00
Площадь: 25.00 | ... Сторона: 5.00 | Площадь: 25.00 | Периметр: 20.00
Раскрасьте все четыре стороны.
Площадь: 6.00 | Треугольник: сторона1 = 3.0 сторона2 = 4.0 сторона3 = 5.0

=== Проверка конструктора Square со стороной -1 ===
Исключение IllegalArgumentException: Сторона квадрата должна быть больше 0
```

### 5.5. Месяцы и високосный год

| Ввод | Результат |
|---|---|
| `1` | `январь: 31 дн.` |
| `2`, `2024` | `февраль 2024: 29 дн.` |
| `2`, `1900` | `февраль 1900: 28 дн.` |
| `13` | `Недопустимое число` |
| `0` | `Недопустимое число` |
| `abc` | `Ошибка: нужно целое число` |

## 6. Выводы

В ходе работы были реализованы иерархия геометрических фигур на основе
абстрактного класса и интерфейса, сравнение объектов через `Comparable` и
обработка ошибочных ситуаций с помощью исключений.

Основные результаты:

- абстрактный класс задаёт общее состояние и контракт, а конкретное поведение
  определяется в подклассах;
- полиморфизм позволяет единообразно обрабатывать разные фигуры через тип
  `GeometricObject`;
- интерфейс описывает дополнительную возможность объекта, не вводя родства
  классов;
- проверяемые исключения защищают инварианты и требуют явной обработки, а
  непроверяемые сигнализируют об ошибках аргументов;
- `try-catch` и проверка ввода позволяют программе корректно реагировать на
  неверные данные пользователя.

## 7. Запуск

```bash
cd seminars/sem4
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.TestTriangle
java -cp out app.TestMax
java -cp out app.TestComparableCircle
java -cp out app.TestColorable
java -cp out app.TestMonths
```
