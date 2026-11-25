package stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class MyStack implements Cloneable {
    private ArrayList<Object> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public Object peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    public Object pop() {
        Object element = peek();
        list.remove(list.size() - 1);
        return element;
    }

    public void push(Object o) {
        list.add(o);
    }

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

    @Override
    public String toString() {
        return "стек: " + list;
    }
}
