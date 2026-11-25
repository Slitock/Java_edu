## Отвты
**1. Пример примитивного типа для хранения полей класса.**
`int` — например, `private int numGears;` в `Bike`, `private int frameSize;`
в `MountainBike`, `private int tyreWidth, postHeight;` в `RoadBike`.
Примитив хранит значение прямо в объекте, а не ссылку на отдельный объект в куче.

**2. Где происходит конкатенация строк.**
В `Bike.printDescription()`: `"\n" + MAKE + "\n" + "This bike has " + handleBars + ...`.
Оператор `+` с участием `String` создаёт новую строку.

**3. Имена созданных объектов.**
`bike1` и `bike2` (`RoadBike`), `bike3` (`MountainBike`), `bike4` (`Bike`).

**4. Сколько конструкторов у каждого класса.**

| Класс | Конструкторы |
|---|---|
| `Bike` | 2 (без аргументов, с пятью аргументами) |
| `MountainBike` | 2 (без аргументов, с восемью аргументами) |
| `RoadBike` | 3 (без аргументов, с `postHeight`, с семью аргументами) |
| `BikeDriver` | явных нет, только по умолчанию |

**5. Супер- и подклассы.**
`Bike` — суперкласс; `MountainBike` и `RoadBike` — его подклассы
(наследуют поля и методы через `extends Bike`).

**6. Значения стандартных велосипедов.**

`RoadBike bike1 = new RoadBike()`:

| Поле | Значение |
|---|---|
| `handleBars` | `drop` |
| `frame` | `racing` |
| `tyres` | `tread less` |
| `seatType` | `razor` |
| `numGears` | 19 |
| `tyreWidth` | 20 |
| `postHeight` | 22 |

`MountainBike bike3 = new MountainBike()`:

| Поле | Значение |
|---|---|
| `handleBars` | `Bull Horn` |
| `frame` | `Hardtail` |
| `tyres` | `Maxxis` |
| `seatType` | `dropper` |
| `numGears` | 27 |
| `suspension` | `RockShox XC32` |
| `type` | `Pro` |
| `frameSize` | 19 |



