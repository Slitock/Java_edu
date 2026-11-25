package util;

import java.util.ArrayList;
import java.util.Objects;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    // 1. Возвращает новый список без дубликатов
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> result = new ArrayList<>();
        for (E element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    // 2. Линейный поиск: позиция элемента или -1
    public static <E> int linearSearch(E[] array, E target) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], target)) {
                return i;
            }
        }
        return -1;
    }

    // 3. Наибольший элемент одномерного массива
    public static <E extends Comparable<E>> E max(E[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
        E maximum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(maximum) > 0) {
                maximum = array[i];
            }
        }
        return maximum;
    }

    // 4. Наибольший элемент двумерного массива
    public static <E extends Comparable<E>> E max(E[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
        E maximum = array[0][0];
        for (E[] row : array) {
            for (E element : row) {
                if (element.compareTo(maximum) > 0) {
                    maximum = element;
                }
            }
        }
        return maximum;
    }
}
