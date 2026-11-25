# Практическая работа №5: дженерики, стек, клонирование

## Состав

```
src/util/CollectionUtils.java        задание 1: четыре обобщённых метода
src/shapes/Circle.java               Comparable-класс с полем radius
src/stacks/GenericStack.java         задание 2.1: стек на массиве
src/stacks/GenericStackArrayList.java задание 2.2: стек — наследник ArrayList
src/stacks/MyStack.java              задание 3: стек на композиции + clone()
src/stacks/MyStackArrayList.java     задание 3: стек — наследник ArrayList
src/app/TestCollectionUtils.java     проверка методов задания 1
src/app/TestGenericStack.java        проверка стека на массиве
src/app/TestGenericStackArrayList.java ввод пяти строк, вывод в обратном порядке
src/app/TestMyStack.java             композиция, наследование, глубокая копия
```

Компиляция и запуск:

```bash
# bash/zsh
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.TestCollectionUtils
java -cp out app.TestGenericStack
java -cp out app.TestGenericStackArrayList
java -cp out app.TestMyStack
```

```nu
# nushell
javac -encoding UTF-8 -d out ...(glob src/**/*.java)
java -cp out app.TestCollectionUtils
java -cp out app.TestGenericStack
java -cp out app.TestGenericStackArrayList
java -cp out app.TestMyStack
```

## Задание 1. Дженерики и Comparable

В `CollectionUtils` реализованы четыре статических обобщённых метода:

1. `removeDuplicates(ArrayList<E>)` — создаёт новый список и добавляет элемент,
   только если `contains()` ещё его не нашёл. Исходный список не меняется.
2. `linearSearch(E[] array, E target)` — перебирает массив и возвращает индекс
   первого совпадения или `-1`. Сравнение через `Objects.equals`, чтобы
   корректно работали и `null`-элементы.
3. `max(E[] array)` — наибольший элемент одномерного массива. Тип ограничен
   `<E extends Comparable<E>>`, сравнение идёт через `compareTo()`.
4. `max(E[][] array)` — то же для двумерного массива, перебор по строкам.

`Circle` реализует `Comparable<Circle>` и сравнивает круги по площади
(`compareTo` возвращает результат `Double.compare` над `getArea()`).
Для кругов порядок по площади совпадает с порядком по радиусу.

Результат:

```
=== Удаление дубликатов ===
Исходный список: [1, 2, 2, 3, 1, 4, 3, 5, 5]
Без дубликатов: [1, 2, 3, 4, 5]

=== Линейный поиск ===
Массив: [Java, Python, Rust, C++]
Позиция Rust: 2
Позиция Go: -1

=== Максимум в одномерном массиве ===
Круги: [Круг: радиус = 3.00 | площадь = 28.27, Круг: радиус = 7.00 | площадь = 153.94, Круг: радиус = 5.00 | площадь = 78.54]
Наибольший: Круг: радиус = 7.00 | площадь = 153.94

=== Максимум в двумерном массиве ===
Матрица кругов: [[Круг: радиус = 2.00 | площадь = 12.57, Круг: радиус = 9.00 | площадь = 254.47], [Круг: радиус = 4.00 | площадь = 50.27, Круг: радиус = 6.00 | площадь = 113.10]]
Наибольший: Круг: радиус = 9.00 | площадь = 254.47
```

## Задание 2. Стек

### 2.1. GenericStack на массиве

`GenericStack<E>` хранит элементы в `Object[] elements` и отдельно ведёт `size`.
При `push()`:

1. Если `size == elements.length`, создаётся массив вдвое больше.
2. `System.arraycopy` копирует в него старые элементы.
3. Новый элемент добавляется в конец, `size` увеличивается.

`pop()` дополнительно обнуляет освободившуюся ячейку, чтобы сборщик мусора
(GC) мог освободить объект. Пустой стек в `peek()`/`pop()` бросает
`EmptyStackException`.

```
=== Стек после трёх push ===
стек: [Первый, Второй, Третий]
Размер: 3
Вершина: Третий

=== Проверка роста внутреннего массива ===
Размер после 15 push: 15
стек: [Первый, Второй, Третий, Элемент 1, ..., Элемент 12]

=== Извлечение элементов ===
pop: Элемент 12
...
pop: Первый
Стек пуст: true
```

### 2.2. GenericStack как наследник ArrayList

`GenericStackArrayList<E> extends ArrayList<E>` добавляет только стековые
операции `push()`, `pop()`, `peek()` и `getSize()`. Остальные методы
`ArrayList` (`add`, `get`, `remove`, ...) класс наследует.
Имя `GenericStack` уже занято стеком на массиве из задания 2.1, поэтому
вариант на наследовании называется `GenericStackArrayList`.

```
        ArrayList<E>
   +add(E): boolean
   +get(int): E
   +remove(int): E
   +size(): int
   +isEmpty(): boolean
          ^
          | extends (is-a)
          |
 GenericStackArrayList<E>
   +getSize(): int
   +peek(): E
   +pop(): E
   +push(E o): void
```

Тестовая программа запрашивает пять строк и выводит их в обратном порядке:

```
Введите пять строк:
один
два
три
четыре
пять

=== Строки в обратном порядке ===
пять
четыре
три
два
один
```

## Задание 3. MyStack: композиция, наследование, клонирование

### Композиция

`MyStack` содержит `ArrayList<Object> list` и не наследует `ArrayList`.
Отношение — has-a (композиция): стек использует список внутри, но наружу
показывает только операции стека.

```
 MyStack                         ArrayList<Object>
 -list: ArrayList<Object>  <>---->  (has-a)
 +isEmpty(): boolean
 +getSize(): int
 +peek(): Object
 +pop(): Object
 +push(o: Object): void
```

Преимущество композиции: класс стека не получает лишние методы `ArrayList`
(`add`, `get`, `remove` и т.д.), поэтому пользователь не может нарушить
стековый интерфейс. Наследование моделирует отношение is-a, композиция — has-a.

### Наследование

`MyStackArrayList extends ArrayList<Object>` — вариант «стек является списком».
`isEmpty()` и `size()` берутся у `ArrayList`, добавлены `push()`, `pop()`,
`peek()` и `getSize()`.

```
        ArrayList<Object>
          ^
          | extends (is-a)
          |
 MyStackArrayList
   +getSize(): int
   +peek(): Object
   +pop(): Object
   +push(o: Object): void
```

В одном пакете два класса с именем `MyStack` невозможны, поэтому вариант
на наследовании называется `MyStackArrayList`. Аналогично
`GenericStackArrayList` — это стек из задания 2.2.

### Глубокая копия

`MyStack` реализует `Cloneable` и переопределяет `clone()`:

```java
@Override
public MyStack clone() {
    try {
        MyStack cloned = (MyStack) super.clone();
        // super.clone() копирует только ссылку на list,
        // поэтому сам список нужно скопировать отдельно
        @SuppressWarnings("unchecked")
        ArrayList<Object> clonedList = (ArrayList<Object>) list.clone();
        cloned.list = clonedList;
        return cloned;
    } catch (CloneNotSupportedException e) {
        return null;
    }
}
```

`super.clone()` делает поверхностную копию: поля-примитивы копируются,
а ссылка на `list` остаётся общей. Поэтому список клонируется отдельно —
иначе `push()` в оригинал менял бы и копию.

Результат:

```
=== MyStack на композиции ===
Введите пять строк:
один
два
три
четыре
пять
Строки в обратном порядке:
пять
четыре
три
два
один

=== MyStack, унаследованный от ArrayList ===
Строки в обратном порядке:
пять
четыре
три
два
один

=== Глубокая копия ===
Оригинал: стек: [один, два, три]
Копия: стек: [один, два]
Размер оригинала: 3
Размер копии: 2
Это разные объекты: true
```

После `clone()` в оригинал добавлен элемент «три», но копия его не получила —
значит, список в копии действительно отдельный.

Примечание: `GenericStackArrayList` и `MyStackArrayList` наследуются от
`ArrayList`, который реализует `Serializable`, поэтому в них добавлен
`serialVersionUID`, чтобы компилятор не выдавал предупреждение.
