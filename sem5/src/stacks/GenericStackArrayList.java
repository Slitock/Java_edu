package stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class GenericStackArrayList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1L;

    public int getSize() {
        return size();
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return get(size() - 1);
    }

    public E pop() {
        E element = peek();
        remove(size() - 1);
        return element;
    }

    public void push(E o) {
        add(o);
    }

    @Override
    public String toString() {
        return "стек: " + super.toString();
    }
}
