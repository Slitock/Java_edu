package stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStackArrayList extends ArrayList<Object> {
    private static final long serialVersionUID = 1L;

    public int getSize() {
        return size();
    }

    public Object peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return get(size() - 1);
    }

    public Object pop() {
        Object element = peek();
        remove(size() - 1);
        return element;
    }

    public void push(Object o) {
        add(o);
    }

    @Override
    public String toString() {
        return "стек: " + super.toString();
    }
}
