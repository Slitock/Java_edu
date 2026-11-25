package stacks;

import java.util.EmptyStackException;

public class GenericStack<E> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public GenericStack() {
        elements = new Object[INITIAL_CAPACITY];
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) elements[size - 1];
    }

    public E pop() {
        E element = peek();
        elements[--size] = null; // чтобы сборщик мусора освободил ссылку
        return element;
    }

    public void push(E o) {
        if (size == elements.length) {
            Object[] bigger = new Object[elements.length * 2];
            System.arraycopy(elements, 0, bigger, 0, elements.length);
            elements = bigger;
        }
        elements[size++] = o;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("стек: [");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(elements[i]);
        }
        return result.append("]").toString();
    }
}
